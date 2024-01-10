package com.example.listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.example.base.BaseTest;
import com.example.utils.ExtentReportGenerator;
import com.example.utils.Log;

public class MyListeners extends BaseTest implements ITestListener {

    ExtentReports report = ExtentReportGenerator.getExtentReport();
    private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    ExtentTest eTest;

    @Override
    public void onTestStart(ITestResult result) {
        String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");
        String testName = result.getMethod().getMethodName();
        String divice = System.getProperty("os.name") + "-" + System.getProperty("os.version") + "-"
                + System.getProperty("os.arch");

        eTest = report.createTest(testName);
        extentTest.set(eTest);
        extentTest.get().assignCategory(result.getMethod().getGroups());
        extentTest.get().info(result.getMethod().getDescription());

        boolean flag = false;
        for (String group : result.getMethod().getGroups()) {
            if (group.equals("API")) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            extentTest.get().assignDevice(divice + "-" + browser);
        } else {
            browser = "API";
            extentTest.get().assignDevice(divice + "-" + browser);
        }
        Log.infoPurple("--- Inicio el test: " + testName + " brw:" + browser + " ---");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        extentTest.get().log(Status.PASS, testName + " got successfully executed");
        Log.infoGreen("--- Test exitoso: " + testName + " ---");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        extentTest.get().log(Status.FAIL, testName + "test fail");
        extentTest.get().fail(result.getThrowable());
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        String testNameScreen = result.getMethod().getMethodName() +
                result.getTestContext().getCurrentXmlTest().getParameter("browser") + result.getEndMillis();
        try {
            extentTest.get().addScreenCaptureFromPath(BaseTest.takesScreenshot(testNameScreen, driver), testName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.error("test fallido: " + testName + "\n" + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        report.flush();
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        extentTest.get().fail(result.getThrowable());
        Log.error("test fallido por tiempo: " + result.getMethod().getMethodName() + "\n" + result.getThrowable());
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        String testName = result.getMethod().getMethodName();
        String testNameScreen = result.getMethod().getMethodName() +
                result.getTestContext().getCurrentXmlTest().getParameter("browser") + result.getEndMillis();
        try {
            extentTest.get().addScreenCaptureFromPath(BaseTest.takesScreenshot(testNameScreen, driver), testName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        extentTest.get().log(Status.SKIP, testName + "test skipped");
        extentTest.get().skip(result.getThrowable());
        Log.infoYellow("Se salto el test: " + testName + "\n" + result.getThrowable());
    }
}
