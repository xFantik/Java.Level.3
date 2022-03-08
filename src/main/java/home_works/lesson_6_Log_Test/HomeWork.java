package home_works.lesson_6_Log_Test;

import java.util.Arrays;

public class HomeWork {

    public static int[] getAfterLastFourSubArray(int[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == 4) {
                return Arrays.copyOfRange(array, i + 1, array.length);
            }
        }
        throw new RuntimeException("В массиве нет четвёрки");
    }

    public static boolean checkArray(int[] array) {
        boolean has1 = false;
        boolean has4 = false;
        for (int i : array) {
            if (i == 1)
                has1 = true;
            else if (i == 4)
                has4 = true;
            else
                return false;
        }
        return has1 && has4;
    }
}
