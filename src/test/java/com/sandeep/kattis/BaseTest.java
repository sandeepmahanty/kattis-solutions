package com.sandeep.kattis;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
@Test
public class BaseTest {
    class TestModel {
        private String className;
        private HashMap<String, Float> methodToTimeMap;

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public HashMap<String, Float> getMethodToTimeMap() {
            return methodToTimeMap;
        }

        public void setMethodToTimeMap(HashMap<String, Float> methodToTimeMap) {
            this.methodToTimeMap = methodToTimeMap;
        }

        @Override
        public String toString() {
            return "TestModel [className=" + className + ", methodToTimeMap=" + methodToTimeMap + "]";
        }
    }

    HashMap<String, TestModel> results;
    private long startTime;

    @BeforeSuite
    public void init() {
        results = new HashMap<>();
    }

    @BeforeMethod
    public void beforeMethod(Method m, ITestContext ct) {
        startTime = System.currentTimeMillis();
    }

    @AfterMethod
    public void afterMethod(Method m, ITestContext ct) {
        double diffTime = (System.currentTimeMillis() - startTime) / 1000;
    }

    @AfterSuite
    public void tearDown() {
        System.out.println(results);
    }
}
