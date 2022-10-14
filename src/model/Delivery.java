package model;

public class Delivery {
    private String vclNo;
    private String vclType;
    private String driverName;
    private String leftTime;

    public Delivery() {
    }

    public Delivery(String vclNo, String vclType, String driverName, String leftTime) {
        this.vclNo = vclNo;
        this.vclType = vclType;
        this.driverName = driverName;
        this.leftTime = leftTime;
    }

    public String getVclNo() {
        return vclNo;
    }

    public void setVclNo(String vclNo) {
        this.vclNo = vclNo;
    }

    public String getVclType() {
        return vclType;
    }

    public void setVclType(String vclType) {
        this.vclType = vclType;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(String leftTime) {
        this.leftTime = leftTime;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "vclNo='" + vclNo + '\'' +
                ", vclType='" + vclType + '\'' +
                ", driverName='" + driverName + '\'' +
                ", leftTime='" + leftTime + '\'' +
                '}';
    }
}
