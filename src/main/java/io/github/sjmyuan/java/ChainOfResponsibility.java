package io.github.sjmyuan.java;

enum LogLevel {
    DEBUG, INFO, ERROR
}


abstract class Logger {
    protected Logger nextLogger;

    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(LogLevel level, String message) {
        if (isDefinedAt(level)) {
            writeMessage(message);
        }

        if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    protected abstract boolean isDefinedAt(LogLevel level);

    protected abstract void writeMessage(String message);
}


class DebugLogger extends Logger {
    public boolean isDefinedAt(LogLevel level) {
        return LogLevel.DEBUG == level;
    }

    public void writeMessage(String message) {
        System.out.println("[DEBUG] - " + message);
    }
}


class InfoLogger extends Logger {
    public boolean isDefinedAt(LogLevel level) {
        return LogLevel.INFO == level;
    }

    public void writeMessage(String message) {
        System.out.println("[INFO] - " + message);
    }
}


class ErrorLogger extends Logger {
    public boolean isDefinedAt(LogLevel level) {
        return LogLevel.ERROR == level;
    }

    public void writeMessage(String message) {
        System.out.println("[ERROR] - " + message);
    }
}


class ChainOfResponsibility {
    public static void main(String[] args) {
        Logger debugLogger = new DebugLogger();
        Logger infoLogger = new InfoLogger();
        Logger errorLogger = new ErrorLogger();

        infoLogger.setNextLogger(errorLogger);
        debugLogger.setNextLogger(infoLogger);

        Logger logger = debugLogger;

        logger.logMessage(LogLevel.DEBUG, "This is a debug message.");
        logger.logMessage(LogLevel.INFO, "This is an information.");
        logger.logMessage(LogLevel.ERROR, "This is a error message.");
    }
}
