package com.learningmate.screenplay.tests.GenericsLab;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class GenericsBoundedLabTest {

    // Upper Bounded Wildcard: Reads Numbers from any List of Number or its subclasses
    public static double sumOfList(List<? extends Number> list) {
        double sum = 0.0;
        for (Number n : list) {
            sum += n.doubleValue(); // Safe to read as Number
        }
        return sum;
    }

    @Test
    public void testBoundedWildcards() {
        List<Integer> intList = Arrays.asList(10, 20, 30);
        List<Double> doubleList = Arrays.asList(1.5, 2.5, 3.0);

        // both List<Integer> and List<Double> are accepted because of ? extends Number
        double intSum = sumOfList(intList);
        double doubleSum = sumOfList(doubleList);

        Assert.assertEquals(intSum, 60.0);
        Assert.assertEquals(doubleSum, 7.0);

        System.out.println("Integer Sum: " + intSum);
        System.out.println("Double Sum: " + doubleSum);
    }
}