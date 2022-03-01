package home_works.lesson_5_Multithreading;

import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static boolean hasWinner = false;
    private static final Object mon = new Object();

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier stageController;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier stageController) {
        this.race = race;
        this.speed = speed;
        this.stageController = stageController;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            stageController.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if (!hasWinner) {
            synchronized (mon) {
                System.out.println("Победитель гонки: " + this.name);
                hasWinner = true;
            }
        }
        try {
            stageController.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
