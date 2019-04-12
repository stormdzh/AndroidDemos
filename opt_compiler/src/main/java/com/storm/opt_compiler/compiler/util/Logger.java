package com.storm.opt_compiler.compiler.util;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

/**
 * Simplify the message print.
 *
 * @author Alex <a href="mailto:zhilong.liu@aliyun.com">Contact me.</a>
 * @version 1.0
 * @since 16/8/22 上午11:48
 */
public class Logger {
    private Messager msg;

    public Logger(Messager messager) {
        msg = messager;
    }

    /**
     * Print info log.
     */
    public void info(CharSequence info) {
//        if (StringUtils.isNotEmpty(info)) {
        msg.printMessage(Diagnostic.Kind.NOTE, "test:" + info);
//        }
    }

    public void error(CharSequence error) {
//        if (StringUtils.isNotEmpty(error)) {
        msg.printMessage(Diagnostic.Kind.ERROR, "test:" + "An exception is encountered, [" + error + "]");
//        }
    }

    public void error(Throwable error) {
        if (null != error) {
            msg.printMessage(Diagnostic.Kind.ERROR, "test:" + "An exception is encountered, [" + error.getMessage() + "]" + "\n" + formatStackTrace(error.getStackTrace()));
        }
    }

    public void warning(CharSequence warning) {
//        if (StringUtils.isNotEmpty(warning)) {
        msg.printMessage(Diagnostic.Kind.WARNING, "test:" + warning);
//        }
    }

    private String formatStackTrace(StackTraceElement[] stackTrace) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : stackTrace) {
            sb.append("    at ").append(element.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
