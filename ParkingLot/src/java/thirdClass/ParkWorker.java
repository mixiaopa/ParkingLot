package thirdClass;

import java.util.ArrayList;

public class ParkWorker {
    private String workerStatus;

    public ParkWorker(String status) {
        this.workerStatus = status;
    }

    public String getWorkerStatus() {
        return workerStatus;
    }

    public void setWorkerStatus(String status) {
        this.workerStatus = status;
    }

    public void parkCarByWorker(ArrayList<Car> cars, ArrayList<ParkingLot> parkingLots) {
        int countCar = 0;
        int countLot = 0;
        for (; countCar < cars.size(); countCar++) {
            Car car = cars.get(countCar);
            if (getWorkerStatus().equals(CarConstant.ST_WK_FREE)) {
                setWorkerStatus(CarConstant.ST_WK_BUSY);
                ParkingLot parkingLot = parkingLots.get(countLot);
                parkingLot.parkCar(car);
                if (parkingLot.getLeftPlaceNumberNow() == 0) {
                    countLot++;
                }
                setWorkerStatus(CarConstant.ST_WK_FREE);
            }
        }
    }
}
