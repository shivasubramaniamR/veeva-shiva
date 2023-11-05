package com.veeva.automation.framework.utils;

public class StepLogger {
    // ThreadLocal for tests running in parallel
    private static ThreadLocal<String> stepMessage = new ThreadLocal<>();

    /**
     * 
     * @param message - Custom message to add to the scenario
     */
    public static void setStepMessage(String message) {
        stepMessage.set(message);
    }

    public static String getStepMessage() {
        return stepMessage.get();
    }

    public static void clearStepMessage() {
        stepMessage.remove();
    }
}
