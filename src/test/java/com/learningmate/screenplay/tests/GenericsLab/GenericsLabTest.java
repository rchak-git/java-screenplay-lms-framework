package com.learningmate.screenplay.tests.GenericsLab;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GenericsLabTest {

    @Test
    public void assertContents() {
        DataHolder<String> objString = new DataHolder<>("Hello");
        DataHolder<Integer> objInteger = new DataHolder<>(1234);

        Assert.assertTrue(objString.hasData());
        Assert.assertNotNull(objString.getContent());
        System.out.println("The returned string content is : " + objString.getContent());

        Assert.assertTrue(objInteger.hasData());
        Assert.assertNotNull(objInteger.getContent());
        System.out.println("The returned integer content is : " + objInteger.getContent());
    }
}