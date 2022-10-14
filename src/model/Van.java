package model;

public class Van extends Vehicle{
    public Van() {
    }

    public Van(String vehicleNo, String vehicleType, int weight, int noOfPassengers) {
        super(vehicleNo, vehicleType, weight, noOfPassengers);
    }

    @Override
    public String getVehicleNo() {
        return super.getVehicleNo();
    }

    @Override
    public void setVehicleNo(String vehicleNo) {
        super.setVehicleNo(vehicleNo);
    }

    @Override
    public String getVehicleType() {
        return super.getVehicleType();
    }

    @Override
    public void setVehicleType(String vehicleType) {
        super.setVehicleType(vehicleType);
    }

    @Override
    public int getWeight() {
        return super.getWeight();
    }

    @Override
    public void setWeight(int weight) {
        super.setWeight(weight);
    }

    @Override
    public int getNoOfPassengers() {
        return super.getNoOfPassengers();
    }

    @Override
    public void setNoOfPassengers(int noOfPassengers) {
        super.setNoOfPassengers(noOfPassengers);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
