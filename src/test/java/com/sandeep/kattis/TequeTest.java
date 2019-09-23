package com.sandeep.kattis;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TequeTest {

    TequeDriver driver = new TequeDriver();

    @DataProvider
    public Object[][] addFirstProvider() {
        return new Object[][]{
                new Object[]{1},
                new Object[]{2},
                new Object[]{3},
                new Object[]{4}
        };
    }


    @DataProvider
    public Object[][] addLastProvider() {
        return new Object[][]{
                new Object[]{5},
                new Object[]{6},
                new Object[]{7},
                new Object[]{8}
        };
    }

    @Test(dataProvider = "addFirstProvider")
    public void testAddFirst(int expected) {
        driver.addFirst(expected);
        int actual = driver.get(0);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "addLastProvider")
    public void testAddLast(int expected) {
        driver.addLast(expected);
        int actual = driver.get(driver.size() - 1);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testAddMiddleEven() {
        driver.addMiddle(12);
        int actual = driver.get(driver.size() / 2);
        Assert.assertEquals(actual, 12);
    }

    @Test
    public void testAddMiddleOdd() {
        driver.addLast(13);
        driver.addMiddle(14);
        int actual = driver.get(driver.size() / 2);
        Assert.assertEquals(actual, 14);
    }

}
