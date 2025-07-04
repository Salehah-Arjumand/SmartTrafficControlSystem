class FineBase {
    String violationCode;
    String violationDescription;
    String violationDegree;
    double fineBaseAmount;
    double doublePercentage;
    int hours;
    boolean isHighDegree;
    
    public FineBase(String code, String description, String degree, double baseAmount, double percentage, int hours, boolean isHigh) {
        this.violationCode = code;
        this.violationDescription = description;
        this.violationDegree = degree;
        this.fineBaseAmount = baseAmount;
        this.doublePercentage = percentage;
        this.hours = hours;
        this.isHighDegree = isHigh;
    }

    public String getViolationCode() {
        return violationCode;
    }

    public void setViolationCode(String violationCode) {
        this.violationCode = violationCode;
    }

    public String getViolationDescription() {
        return violationDescription;
    }

    public void setViolationDescription(String violationDescription) {
        this.violationDescription = violationDescription;
    }

    public String getViolationDegree() {
        return violationDegree;
    }

    public void setViolationDegree(String violationDegree) {
        this.violationDegree = violationDegree;
    }

    public double getFineBaseAmount() {
        return fineBaseAmount;
    }

    public void setFineBaseAmount(double fineBaseAmount) {
        this.fineBaseAmount = fineBaseAmount;
    }

    public double getDoublePercentage() {
        return doublePercentage;
    }

    public void setDoublePercentage(double doublePercentage) {
        this.doublePercentage = doublePercentage;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public boolean isHighDegree() {
        return isHighDegree;
    }

    public void setHighDegree(boolean isHighDegree) {
        this.isHighDegree = isHighDegree;
    }

    public String toString() {
        return "FineBase [violationCode=" + violationCode + ", violationDescription=" + violationDescription
                + ", violationDegree=" + violationDegree + ", fineBaseAmount=" + fineBaseAmount + ", doublePercentage="
                + doublePercentage + ", hours=" + hours + ", isHighDegree=" + isHighDegree + "]";
    }
}