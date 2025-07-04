import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class TrafficFineSystem {
    // Define data structures for FineInfo, ProcessedFine, and UnprocessedFine
    static ArrayList<FineBase> baseFineList = new ArrayList<FineBase>();
    static ArrayList<Processed> processedFines = new ArrayList<Processed>();
    static ArrayList<Unprocessed> unprocessedFines = new ArrayList<Unprocessed>();

    public static void main(String[] args) throws NumberFormatException, IOException {

        // Create a clock instance
        Clock clock = Clock.systemDefaultZone();

        // Read data from input files
        Instant startReading = clock.instant();
        readBaseFineFile("src/main/resources/FineBase.txt");
        readProcessedFineFile("src/main/resources/Processed.txt");
        readUnprocessedFineFile("src/main/resources/Unprocessed.txt");

        Instant endReading = clock.instant();

        Duration readingDuration = Duration.between(startReading, endReading);
        long readingMillis = readingDuration.toMillis();
        System.out.println("Reading from input file took " + readingMillis + " milliseconds");

        // Step 2: Process unprocessed fines and update processed fines
        Instant startProcessing = clock.instant();
        for (Unprocessed unprocessedFine : unprocessedFines) {
            Processed processedFine = processFine(unprocessedFine);
            processedFines.add(processedFine);
        }
        Instant endProcessing = clock.instant();

        Duration processingDuration = Duration.between(startProcessing, endProcessing);
        long processingMillis = processingDuration.toMillis();
        System.out.println("Processing violations took " + processingMillis + " milliseconds");

        // Step 3: Write the updated processed fines to the output file
        Instant startWriting = clock.instant();
        writeProcessedFinesToFile("src/main/resources/Processed.txt", processedFines);
        Instant endWriting = clock.instant();

        Duration writingDuration = Duration.between(startWriting, endWriting);
        long writingMillis = writingDuration.toMillis();
        System.out.println("Writing to file took " + writingMillis + " milliseconds");

    }

    // Define methods for reading, processing, and writing files

    private static void readBaseFineFile(String filename) throws NumberFormatException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 7) {
                    String code = parts[0];
                    String description = parts[1];
                    String degree = parts[2];
                    double baseAmount = Double.parseDouble(parts[3]);
                    double percentage = Double.parseDouble(parts[4]);
                    int hours = Integer.parseInt(parts[5]);
                    boolean isHigh = parts[6].equals("yes");
                    FineBase fineInfo = new FineBase(code, description, degree, baseAmount, percentage, hours, isHigh);
                    baseFineList.add(fineInfo);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void readProcessedFineFile(String filename) throws NumberFormatException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 9) {
                    String driverID = parts[0];
                    String date = parts[1];
                    boolean isPSUer = parts[2].equals("yes");
                    String plateNumber = parts[3];
                    String violationCode = parts[4];
                    double percentage = Double.parseDouble(parts[5]);
                    double fineAmount = Double.parseDouble(parts[6]);
                    int hours = Integer.parseInt(parts[7]);
                    boolean isCompleted = parts[8].equals("yes");
                    Processed processedFine = new Processed(driverID, date, isPSUer, plateNumber, violationCode,
                            percentage, fineAmount, hours, isCompleted);
                    processedFines.add(processedFine);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void readUnprocessedFineFile(String filename) throws NumberFormatException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    String plateNumber = parts[0];
                    String date = parts[1];
                    String violationCode = parts[2];
                    String location = parts[3];
                    int dayType = Integer.parseInt(parts[4]);
                    Unprocessed unprocessedFine = new Unprocessed(plateNumber, date, violationCode, location, dayType);
                    unprocessedFines.add(unprocessedFine);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Processed processFine(Unprocessed unprocessedFine) {
        // Find matching FineInfo based on violation code
        FineBase matchingFineInfo = null;
        for (FineBase fineInfo : baseFineList) {
            if (fineInfo.violationCode.equals(unprocessedFine.violationCode)) {
                matchingFineInfo = fineInfo;
                break;
            }
        }

        // Find matching Processed fine based on violation code
        Processed matchingProcessedFine = null;
        for (Processed processed : processedFines) {
            if (processed.plateNumber.equals(unprocessedFine.plateNumber)) {
                matchingProcessedFine = processed;
                break;
            }
        }

        double fineAmount = 0;
        int hours = 0;

        if (matchingFineInfo != null) {
            // Apply specific rules based on day type
            switch (unprocessedFine.dayType) {
                case 1: // Final exams or major events
                    fineAmount = matchingFineInfo.fineBaseAmount * (1 + matchingFineInfo.doublePercentage);
                    hours = matchingFineInfo.hours * 2; // Double hours
                    break;
                case 2: // PSU teaching days
                    fineAmount = matchingFineInfo.fineBaseAmount * (1 + matchingFineInfo.doublePercentage);
                    hours = matchingFineInfo.hours;
                    break;
                case 3: // Non-PSU teaching days
                    if (!matchingFineInfo.violationDegree.equals("High")) {
                        fineAmount = matchingFineInfo.fineBaseAmount;
                    }
                    break;
                case 4: // Non-PSU business days
                    if (matchingFineInfo.violationDegree.equals("High")) {
                        hours = 0; // Zero hours for high-degree violations
                    }
                    break;
                default:
                    break;
            }

            // Apply 10% increase for violators with a history of violations
            if (fineAmount > 0) {
                fineAmount *= 1.10;
                hours = (int) Math.round(hours * 1.10);
            }

            // Apply VAT (15% of the final fine amount)
            fineAmount += (0.15 * fineAmount);
        }

        // Assume the fine is completed
        return new Processed(matchingProcessedFine.driverID, unprocessedFine.date, true, unprocessedFine.plateNumber,
                unprocessedFine.violationCode, matchingFineInfo.doublePercentage, fineAmount, hours, true);
    }

    private static void writeProcessedFinesToFile(String filename, List<Processed> processedFines)
            throws FileNotFoundException {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.print(""); // Write an empty string to the file
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
            for (Processed processedFine : processedFines) {
                // Write processed fines to the output file
                writer.println(processedFine.driverID + ";" + processedFine.date + ";" +
                        (processedFine.isPSUer ? "yes" : "no") + ";" + processedFine.plateNumber + ";" +
                        processedFine.violationCode + ";" + processedFine.doublePercentage + ";" +
                        decimalFormat.format(processedFine.fineAmount) + ";" + processedFine.hours + ";" +
                        (processedFine.isCompleted ? "yes" : "no") + ";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}