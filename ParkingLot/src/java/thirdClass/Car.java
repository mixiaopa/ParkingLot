package thirdClass;

public class Car {

    private int idOfCar;
    private String status;
    private int parkingId;

    public Car(int idOfCar, String status) {
        this.status = status;
        this.idOfCar = idOfCar;
    }

    public int getIdOfCar() {
        return idOfCar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }
}
