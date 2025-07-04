class Processed {
    String driverID;
    String date;
    boolean isPSUer;
    String plateNumber;
    String violationCode;
    double doublePercentage;
    double fineAmount;
    int hours;
    boolean isCompleted;
    
    public Processed(String driverID, String date, boolean isPSUer, String plateNumber, String violationCode, double percentage, double fineAmount, int hours, boolean isCompleted) {
        this.driverID = driverID;
        this.date = date;
        this.isPSUer = isPSUer;
        this.plateNumber = plateNumber;
        this.violationCode = violationCode;
        this.doublePercentage = percentage;
        this.fineAmount = fineAmount;
        this.hours = hours;
        this.isCompleted = isCompleted;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isPSUer() {
        return isPSUer;
    }

    public void setPSUer(boolean isPSUer) {
        this.isPSUer = isPSUer;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getViolationCode() {
        return violationCode;
    }

    public void setViolationCode(String violationCode) {
        this.violationCode = violationCode;
    }

    public double getDoublePercentage() {
        return doublePercentage;
    }

    public void setDoublePercentage(double doublePercentage) {
        this.doublePercentage = doublePercentage;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String toString() {
        return "Processed [driverID=" + driverID + ", date=" + date + ", isPSUer=" + isPSUer + ", plateNumber="
                + plateNumber + ", violationCode=" + violationCode + ", doublePercentage=" + doublePercentage
                + ", fineAmount=" + fineAmount + ", hours=" + hours + ", isCompleted=" + isCompleted + "]";
    }

    
}