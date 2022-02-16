package home_works.lesson_1_Generics;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit>  {
    ArrayList<T> fruitsList;
    private final String name;

    public Box(String fruits) {
        fruitsList =new ArrayList<T>();
        this.name=fruits;
    }

    public final void addFruit(T... fruit){
        fruitsList.addAll(Arrays.asList(fruit));
    }

    public float getWeight(){
        float result = 0f;
        for (T fruit : fruitsList) {
            result+=fruit.getWeight();
        }
        return result;
    }

    public boolean compare(Box<? extends Fruit> o) {
        return this.getWeight()==o.getWeight();
    }

    public void moveFruitsTo(Box<T> o){
        System.out.printf("Пересыпаем из \"%s\" во \"%s\"\n", this, o);
        o.fruitsList.addAll(this.fruitsList);
        fruitsList.clear();
    }

    @Override
    public String toString() {
        return String.format("%s (%s шт)", name, fruitsList.size());
    }
}

