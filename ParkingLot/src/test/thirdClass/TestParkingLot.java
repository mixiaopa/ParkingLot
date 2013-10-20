package thirdClass;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestParkingLot {
    private ParkingLot parkingLot;

    @Before
    public void setUp() throws Exception {
        parkingLot = new ParkingLot(1000);
    }

    @Test
    public void shouldChangeStatusOfParkingLotWhenNewCarParked() throws Exception {
        Car car = new Car(001, CarConstant.ST_WAIT);

        parkingLot.parkCar(car);

        assertThat(parkingLot.getCarNumberNow(), is(1));
        assertThat(parkingLot.getLeftPlaceNumberNow(), is(999));
        assertThat(car.getStatus(), is(CarConstant.ST_PARKED));
        assertThat(car.getParkingId(), is(1));
    }

    @Test
    public void shouldSetStatusTwiceWhenTwoCarsParked() throws Exception {
        Car car = new Car(001, CarConstant.ST_WAIT);
        Car carTwo = new Car(002, CarConstant.ST_WAIT);

        parkingLot.parkCar(car);
        parkingLot.parkCar(carTwo);

        assertThat(parkingLot.getCarNumberNow(), is(2));
        assertThat(parkingLot.getLeftPlaceNumberNow(), is(998));
        assertThat(car.getStatus(), is(CarConstant.ST_PARKED));
        assertThat(car.getParkingId(), is(1));
        assertThat(carTwo.getStatus(), is(CarConstant.ST_PARKED));
        assertThat(carTwo.getParkingId(), is(2));
    }

    @Test
    public void shouldSetStatusToLeaveWhenCarLeaveParkingLot() throws Exception {
        Car car = new Car(001, CarConstant.ST_WAIT);

        parkingLot.parkCar(car);
        parkingLot.carLeave(car);

        assertThat(parkingLot.getCarNumberNow(), is(0));
        assertThat(parkingLot.getLeftPlaceNumberNow(), is(1000));
        assertThat(car.getStatus(), is(CarConstant.ST_LEFT));
    }

    @Test
    public void shouldNotParkMoreCarWhenParkingLotIsFull() throws Exception {
        Car car = new Car(001, CarConstant.ST_WAIT);
        Car carTwo = new Car(002, CarConstant.ST_WAIT);
        ParkingLot parkingLotSmall = new ParkingLot(1);

        parkingLotSmall.parkCar(car);
        parkingLotSmall.parkCar(carTwo);

        assertThat(parkingLotSmall.getCarNumberNow(), is(1));
        assertThat(parkingLotSmall.getLeftPlaceNumberNow(), is(0));
        assertThat(car.getStatus(), is(CarConstant.ST_PARKED));
        assertThat(carTwo.getStatus(), is(CarConstant.ST_WAIT));
    }

    @Test
    public void shouldParkCarByWorker() throws Exception {
        Car car = new Car(001, CarConstant.ST_WAIT);
        Car carTwo = new Car(002, CarConstant.ST_WAIT);
        ArrayList<Car> cars = new ArrayList<Car>();
        cars.add(car);
        cars.add(carTwo);
        ArrayList<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(parkingLot);
        ParkWorker parkWorker = new ParkWorker(CarConstant.ST_WK_FREE);

        parkWorker.parkCarByWorker(cars, parkingLots);

        assertThat(parkingLot.getCarNumberNow(), is(2));
        assertThat(parkingLot.getLeftPlaceNumberNow(), is(998));
        assertThat(car.getStatus(), is(CarConstant.ST_PARKED));
        assertThat(carTwo.getStatus(), is(CarConstant.ST_PARKED));
    }

    @Test
    public void shouldWaitIfWorkerIsBusy() throws Exception {
        Car car = new Car(001, CarConstant.ST_WAIT);
        Car carTwo = new Car(002, CarConstant.ST_WAIT);
        ArrayList<Car> cars = new ArrayList<Car>();
        cars.add(car);
        cars.add(carTwo);
        ArrayList<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(parkingLot);
        ParkWorker parkWorker = new ParkWorker(CarConstant.ST_WK_BUSY);

        parkWorker.parkCarByWorker(cars, parkingLots);

        assertThat(parkingLot.getCarNumberNow(), is(0));
        assertThat(parkingLot.getLeftPlaceNumberNow(), is(1000));
        assertThat(car.getStatus(), is(CarConstant.ST_WAIT));
        assertThat(carTwo.getStatus(), is(CarConstant.ST_WAIT));
    }

    @Test
    public void shouldParkToParkingLotTwoIfLotOneIsFull() throws Exception {
        Car car = new Car(001, CarConstant.ST_WAIT);
        Car carTwo = new Car(002, CarConstant.ST_WAIT);
        Car carThree = new Car(003, CarConstant.ST_WAIT);
        ArrayList<Car> cars = new ArrayList<Car>();
        cars.add(car);
        cars.add(carTwo);
        cars.add(carThree);
        ArrayList<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        ParkingLot parkingLotSmall = new ParkingLot(1);
        parkingLots.add(parkingLotSmall);
        parkingLots.add(parkingLot);
        ParkWorker parkWorker = new ParkWorker(CarConstant.ST_WK_FREE);

        parkWorker.parkCarByWorker(cars, parkingLots);

        assertThat(parkingLotSmall.getCarNumberNow(), is(1));
        assertThat(parkingLot.getCarNumberNow(), is(2));
        assertThat(parkingLotSmall.getLeftPlaceNumberNow(), is(0));
        assertThat(parkingLot.getLeftPlaceNumberNow(), is(998));
        assertThat(car.getStatus(), is(CarConstant.ST_PARKED));
        assertThat(carTwo.getStatus(), is(CarConstant.ST_PARKED));
        assertThat(carThree.getStatus(), is(CarConstant.ST_PARKED));
    }
}
