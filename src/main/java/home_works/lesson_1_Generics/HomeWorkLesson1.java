package home_works.lesson_1_Generics;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeWorkLesson1 {
    public static void main(String[] args) {
        String[] testArray = new String[]{"Первый", "Второй","Третий"};

        var arrayList=getArrayList(testArray);                     //Задача 2
        System.out.println(arrayList);


        changePositions(testArray, 0,2 );                           //Задача 1
        System.out.println(Arrays.toString(testArray) + "\n");


                                                                                    //БольшаяЗадача
        Box<Orange> orangeBox=new Box<>("Первая коробка апельсинов");
        orangeBox.addFruit(new Orange(), new Orange());

        Box<Apple> appleBox=new Box<>("Коробка яблок");
        appleBox.addFruit(new Apple(), new Apple(), new Apple());

        Box<Orange> orangeBox2=new Box<>("Вторая коробка апельсинов");
        orangeBox2.addFruit(new Orange());


        System.out.println(orangeBox.compare(appleBox)?"Вес одинаковый":"Вес разный");   //2 апельсина = 3 яблока
        System.out.println(orangeBox.compare(orangeBox2)?"Вес одинаковый":"Вес разный"); //2 апельсина != 1 апельсин


        orangeBox.moveFruitsTo(orangeBox2);
        System.out.println(orangeBox);
        System.out.println(orangeBox2);
    }

    private static <T> void changePositions(T[] arr, int index1, int index2) {
        if (index1 >= arr.length || index2 >= arr.length || index1 < 0 || index2 < 0) {
            System.out.println("Индекс элемента за пределами массива");
            return;
        }
        T tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    private static <T> ArrayList<T> getArrayList(T[] arr){
        ArrayList<T> result= new ArrayList<>();
        for (T t : arr) {
            result.add(t);
        }
        return result;
    }
}
