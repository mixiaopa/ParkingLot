package thirdClass;

import java.util.ArrayList;

public class ParkingLot {
    private final int maxNumberOfCar;
    private int carNumberNow;
    private int leftPlaceNumberNow;

    public ParkingLot(int maxNumber) {
        this.maxNumberOfCar = maxNumber;
    }

    public int getMaxNumberOfCar() {
        return maxNumberOfCar;
    }

    public int getCarNumberNow() {
        return carNumberNow;
    }

    public int getLeftPlaceNumberNow() {
        return maxNumberOfCar - carNumberNow;
    }

    public void parkCar(Car car) {
        if (this.carNumberNow < maxNumberOfCar) {
            this.carNumberNow += 1;
            car.setStatus(CarConstant.ST_PARKED);
            car.setParkingId(carNumberNow);
        } else {
            car.setStatus(CarConstant.ST_WAIT);
        }
    }

    public void carLeave(Car car) {
        this.carNumberNow -= 1;
        car.setStatus(CarConstant.ST_LEFT);
    }
}
