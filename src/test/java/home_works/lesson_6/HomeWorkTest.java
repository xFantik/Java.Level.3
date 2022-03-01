package home_works.lesson_6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

class HomeWorkTest {

    @Test
    void subArrayNormalTest() {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        int[] result = {5, 6, 7};
        Assertions.assertArrayEquals(result, HomeWork.getAfterLastFourSubArray(array));
    }

    @Test
    void subArrayEmptyResultTest() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 4};
        int[] result = new int[]{};
        Assertions.assertArrayEquals(result, HomeWork.getAfterLastFourSubArray(array));
    }

    @Test
    void subArrayErrorTest() {
        int[] array = {1, 2, 3, 1, 5, 6, 2};
        Assertions.assertThrows(RuntimeException.class, () -> HomeWork.getAfterLastFourSubArray(array));
    }

    @Test
    void subArrayEmptyDataTest() {
        int[] array = new int[]{};
        Assertions.assertThrows(RuntimeException.class, () -> HomeWork.getAfterLastFourSubArray(array));
    }

    @MethodSource("generateData")
    @ParameterizedTest
    void massCheckArrayTest(boolean result, int... arr) {
        if (result)
            Assertions.assertTrue(HomeWork.checkArray(arr));
        else
            Assertions.assertFalse(HomeWork.checkArray(arr));
    }

    private static Stream<Arguments> generateData() {
        List<Arguments> args = new LinkedList<>();

        args.add(Arguments.arguments(true, new int[]{1, 4, 1}));
        args.add(Arguments.arguments(true, new int[]{1, 4}));
        args.add(Arguments.arguments(true, new int[]{1, 4, 4, 1, 1, 4, 4, 1, 1, 4, 1}));
        args.add(Arguments.arguments(false, new int[]{1, 1, 1, 1, 1, 1, 1}));
        args.add(Arguments.arguments(false, new int[]{1, 1, 1, 1, 1, 4, 1, 4, 2, 1}));
        args.add(Arguments.arguments(false, new int[]{4, 4, 4}));
        args.add(Arguments.arguments(false, new int[]{1, 2, 3, 4}));

        return args.stream();
    }
}
