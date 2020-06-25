package lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> container;

    public Box() {
        this.container = new ArrayList<>();
    }
    public Box(T... fruit) {
        this.container = new ArrayList<>(Arrays.asList(fruit));
    }

    public float getWight(){
        float w=0.0f;
        for (T fruit:container) {
            w+=fruit.getWeight();
        }
        return w;
    }

    public boolean sameAvg(Box<?> another){
        return Math.abs(this.getWight()-another.getWight())<0.01;
    }

    public void transfer(Box<T> another){
        if (another==this){
            return;
        }
        another.container.addAll(this.container);
        this.container.clear();
    }

    public void add(T fruit){
        container.add(fruit);
    }

}
