package ru.bvkuchin;

import java.util.concurrent.*;



public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;

    private CyclicBarrier cyclicBarrier;

    private CountDownLatch cdlStartRace;

    private CountDownLatch cdlEndRace;


    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CyclicBarrier cyclicBarrier, CountDownLatch cdlStartRace, CountDownLatch cdlEndRace) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cyclicBarrier = cyclicBarrier;
        this.cdlStartRace = cdlStartRace;
        this.cdlEndRace = cdlEndRace;

    }
    @Override
    public void run() {
        try {
            cyclicBarrier.await();
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            cyclicBarrier.await();
            System.out.println(this.name + " готов");
            cdlStartRace.countDown();

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
            if (i == race.getStages().size()-1) {
                if (!race.isHasWinner()) {
                    race.setHasWinner();
                    System.out.println(this.name + " WIN");
                }
                cdlEndRace.countDown();

            }

        }
    }
}