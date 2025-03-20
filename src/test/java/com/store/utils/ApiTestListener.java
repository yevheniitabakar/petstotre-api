package com.store.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class ApiTestListener implements ITestListener {
    private static final Logger LOG = LoggerFactory.getLogger(ApiTestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        String groups = Arrays.toString(result.getMethod().getGroups());
        LOG.info("Starting test: {} | Description: {} | Groups: {}",
                result.getMethod().getMethodName(),
                result.getMethod().getDescription(),
                groups);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LOG.info("Test passed: {} | Execution Time: {} ms",
                result.getMethod().getMethodName(),
                result.getEndMillis() - result.getStartMillis());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOG.error("Test failed: {} | Execution Time: {} ms | Failure Reason: {}",
                result.getMethod().getMethodName(),
                result.getEndMillis() - result.getStartMillis(),
                result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LOG.warn("Test skipped: {} | Reason: {}",
                result.getMethod().getMethodName(),
                result.getThrowable() != null ? result.getThrowable().getMessage() : "Unknown");
    }

    @Override
    public void onStart(ITestContext context) {
        LOG.info("Starting test suite: {}", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LOG.info("Finished test suite: {} | Total Tests: {} | Passed: {} | Failed: {} | Skipped: {}",
                context.getName(),
                context.getAllTestMethods().length,
                context.getPassedTests().size(),
                context.getFailedTests().size(),
                context.getSkippedTests().size());
    }
}
