package tm;

public class VehicleTM   {
    private String vehicleNo;
    private String vehicleType;
    private int weight;
    private int noOfPassengers;

    public VehicleTM() {
    }

    public VehicleTM(String vehicleNo, String vehicleType, int weight, int noOfPassengers) {
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.weight = weight;
        this.noOfPassengers = noOfPassengers;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getNoOfPassengers() {
        return noOfPassengers;
    }

    public void setNoOfPassengers(int noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    @Override
    public String toString() {
        return "VehicleTM{" +
                "vehicleNo='" + vehicleNo + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", weight=" + weight +
                ", noOfPassengers=" + noOfPassengers +
                '}';
    }
}