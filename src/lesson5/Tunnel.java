package lesson5;

import java.util.concurrent.ArrayBlockingQueue;

public class Tunnel extends Stage {
    public ArrayBlockingQueue<Car> abq;
    public Tunnel(ArrayBlockingQueue<Car> abq) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.abq=abq;
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                abq.put(c);
                System.out.println(c.getName() + " начал этап: " + description);
                System.out.println("В тунеле "+abq);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                abq.take();
                System.out.println("В тунеле "+abq);
                System.out.println(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
