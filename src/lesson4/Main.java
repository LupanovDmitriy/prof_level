package lesson4;
/*
1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
Используйте wait/notify/notifyAll.
 */

public class Main {
    static Object mon = new Object();
    static volatile int counter;
    static final int STEP=5;
    static final int COUNTSYMBOL=3;

    public static void main(String[] args) {
        counter=1;
        new Thread(()->{
            for (int i = 0; i <STEP ; i++) {
                synchronized (mon){
                    while (counter!=1){
                        try {
                            mon.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("A");
                    changeCounter();
                    mon.notifyAll();
                }
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i <STEP ; i++) {
                synchronized (mon){
                    while (counter!=2){
                        try {
                            mon.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("B");
                    changeCounter();
                    mon.notifyAll();
                }
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i <STEP ; i++) {
                synchronized (mon){
                    while (counter!=3){
                        try {
                            mon.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("C");
                    changeCounter();
                    mon.notifyAll();
                }
            }
        }).start();
    }

    public static void changeCounter() {
        if (counter==COUNTSYMBOL){
            counter=1;
        }else{
            counter++;
        }

    }

}
