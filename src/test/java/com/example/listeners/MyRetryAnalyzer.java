package com.example.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.example.utils.Log;

public class MyRetryAnalyzer implements IRetryAnalyzer {
   int counter = 0;
   int retryLimit = 1;

   @Override
   public boolean retry(ITestResult result) {
      if (counter < retryLimit) {
         counter++;
         Log.warn("El test fallo: " + result.getMethod().getMethodName() + " Reintento numero: " + counter + " de 1");
         return true;
      }
      return false;
   }
}
