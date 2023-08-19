package dev.sarj.sarjdev.core.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

    /**
     * Retrieves the stack trace of the provided exception as a string.
     *
     * @param exception The exception for which the stack trace is to be retrieved.
     * @return A string representation of the stack trace of the provided exception.
     */
    public static String getStackTrace(Exception exception) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        // Write the exception's stack trace to the PrintWriter.
        exception.printStackTrace(pw);

        // Convert the stack trace written to the StringWriter into a string.
        return sw.toString();
    }
}