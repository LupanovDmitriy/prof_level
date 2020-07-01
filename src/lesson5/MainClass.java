package lesson5;
/*
Организуем гонки:
Все участники должны стартовать одновременно, несмотря на то, что на подготовку у каждого их них уходит разное время.
В тоннель не может заехать одновременно больше половины участников (условность).
Попробуйте все это синхронизировать.
Только после того, как все завершат гонку, нужно выдать объявление об окончании.
Можете корректировать классы (в т.ч. конструктор машин) и добавлять объекты классов из пакета util.concurrent.
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        ArrayBlockingQueue<Car> abq= new ArrayBlockingQueue<>(CARS_COUNT/2);
        Race race = new Race(new Road(60), new Tunnel(abq), new Road(40));
        CountDownLatch cwl = new CountDownLatch(CARS_COUNT);
        CyclicBarrier cb = new CyclicBarrier(CARS_COUNT);

        Car[] cars = new Car[CARS_COUNT];
        /*
        !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        ВОПРОС 1. cwl и cb по сути общие объекты для всего класса, но я их передаю как параметры для конструктора.
        понимаю что видимо нужно их как то передать ранее при создании массива машин, но как это сделать не совсем понимаю.
        и что это за конструкция ниже. static {*} в какой момент времени это вызывается?

        public class Car implements Runnable {
        private static int CARS_COUNT;
        static {
            CARS_COUNT = 0;
        }
         */
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10),cwl,cb);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        /*
        !!!!!!!!!!!
        ВОПРОС 2. cb проверяет готовность всех машин к старту внутри класса Car. и как все готовы, гонка начинается.
        но сообщение "Гонка началась" выводится на экран ранее чем фактически она началась. Можно ли в классе MAIN проверить   cb.await()?
        или еще делать одну защелку такую как сделал при завершении cwl2.await()?
         */
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        try {
            cwl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}





