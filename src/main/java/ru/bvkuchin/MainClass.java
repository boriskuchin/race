package ru.bvkuchin;

import java.util.concurrent.*;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static void main(String[] args) {


        CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT);
        CountDownLatch cdlStartRace = new CountDownLatch(CARS_COUNT);
        CountDownLatch cdlEndRace = new CountDownLatch(CARS_COUNT);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(CARS_COUNT/2), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 10 + (int) (Math.random() * 10), cyclicBarrier, cdlStartRace, cdlEndRace);

        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            cdlStartRace.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            cdlEndRace.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
