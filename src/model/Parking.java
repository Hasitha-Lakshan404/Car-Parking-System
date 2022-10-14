package model;

public class Parking {
    private String vhiNo;
    private String vhiType;
    private String parkingSlot;
    private String parkedTime;

    public Parking() {
    }

    public Parking(String vhiNo, String vhiType, String parkingSlot, String parkedTime) {
        this.vhiNo = vhiNo;
        this.vhiType = vhiType;
        this.parkingSlot = parkingSlot;
        this.parkedTime = parkedTime;
    }

    public String getVhiNo() {
        return vhiNo;
    }

    public void setVhiNo(String vhiNo) {
        this.vhiNo = vhiNo;
    }

    public String getVhiType() {
        return vhiType;
    }

    public void setVhiType(String vhiType) {
        this.vhiType = vhiType;
    }

    public String getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(String parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    public String getParkedTime() {
        return parkedTime;
    }

    public void setParkedTime(String parkedTime) {
        this.parkedTime = parkedTime;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "vhiNo='" + vhiNo + '\'' +
                ", vhiType='" + vhiType + '\'' +
                ", parkingSlot='" + parkingSlot + '\'' +
                ", parkedTime='" + parkedTime + '\'' +
                '}';
    }
}
