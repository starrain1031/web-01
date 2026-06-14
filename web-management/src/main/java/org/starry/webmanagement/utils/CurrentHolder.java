package org.starry.webmanagement.utils;

/**
 * Thread-local holder for the current authenticated employee id.
 */
public class CurrentHolder {

    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();

    /**
     * Stores the current employee id in the current request thread.
     */
    public static void setCurrentId(Integer employeeId) {
        CURRENT_LOCAL.set(employeeId);
    }

    /**
     * Returns the employee id stored in the current request thread.
     */
    public static Integer getCurrentId() {
        return CURRENT_LOCAL.get();
    }

    /**
     * Clears the employee id from the current request thread.
     */
    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}