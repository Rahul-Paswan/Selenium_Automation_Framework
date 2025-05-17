package com.rahul.utility;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
    public static final ThreadLocal<ExtentTest> ThreadExtentTest=new ThreadLocal<>();
    public static final ThreadLocal<String> TTestName =new ThreadLocal<>();

    public static ExtentTest get(){
        return ThreadExtentTest.get();
    }

    public static void remove(){
        ThreadExtentTest.remove();
    }

    public static void set(ExtentTest test){
        ThreadExtentTest.set(test);
    }

    public static String getTestName(){
        return TTestName.get();
    }
}
