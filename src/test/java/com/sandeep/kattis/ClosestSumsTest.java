package com.sandeep.kattis;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ClosestSumsTest {

    @DataProvider(name = "dataProvider")
    public Object[][] dataProvider() {
        return new Object[][] { { new int[] { 1, 2, 3, 8, 4, 2 }, new int[] { 3, 3 } },
                { new int[] { 1, 2, 3, 8, 4, 2 }, new int[] { 4, 4 } },
                { new int[] { 1, 2, 3, 8, 4, 2 }, new int[] { 13, 12 } },
                { new int[] { 1, 2, 3, 8, 4, 2 }, new int[] { -1, 3 } },
                { new int[] { 1, 2, 3, 8, 4, 2 }, new int[] { 100, 12 } } };
    }

    @Test(dataProvider = "dataProvider")
    public void test(int[] arr, int[] res) {
        // Have to add it here as in the actual code it is place outside(optimal
        // position)
        Arrays.sort(arr);
        int k = res[0];
        int expected = res[1];
        int actual = ClosestSums.closest(arr, k);
        Assert.assertEquals(actual, expected);
    }
}