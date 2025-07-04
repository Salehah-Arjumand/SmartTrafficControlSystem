class Unprocessed {
    String plateNumber;
    String date;
    String violationCode;
    String location;
    int dayType;
    
    public Unprocessed(String plateNumber, String date, String violationCode, String location, int dayType) {
        this.plateNumber = plateNumber;
        this.date = date;
        this.violationCode = violationCode;
        this.location = location;
        this.dayType = dayType;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getViolationCode() {
        return violationCode;
    }

    public void setViolationCode(String violationCode) {
        this.violationCode = violationCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDayType() {
        return dayType;
    }

    public void setDayType(int dayType) {
        this.dayType = dayType;
    }

    public String toString() {
        return "Unprocessed [plateNumber=" + plateNumber + ", date=" + date + ", violationCode=" + violationCode
                + ", location=" + location + ", dayType=" + dayType + "]";
    }

    
}