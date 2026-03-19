class Logger {
      /**
       * This class will be used for adding logs
       * */
    private static LogLevel logLevelFilter;
    private static LinkedBlockingQueue<LogMessage> queue;
    private Logger() {}
    private static Logger INSTANCE;

    public static Logger instance(LogLevel logLevel, LinkedBlockingQueue<LogMessage> queue) {
        if(INSTANCE == null) {
            synchronized (Logger.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Logger();
                    Logger.queue = queue;
                    Logger.logLevelFilter = logLevel;
                }
            }
        }
        return INSTANCE;
    }

    private static void addToMessageQueue(LogMessage logMessage) {
        queue.add(logMessage);
    }

    private static boolean filter(LogLevel logLevel) {
        if(logLevelFilter.level >= logLevel.level) return true;
        return false;
    }

    public static void d(String message, String TAG) {
         if(filter(LogLevel.DEBUG)) {
             addToMessageQueue(new LogMessage(LogLevel.DEBUG, message, TAG));
         }
    }

    public static void i(String message, String TAG) {
        if(filter(LogLevel.INFO)) {
            addToMessageQueue(new LogMessage(LogLevel.INFO, message, TAG));
        }
    }

    public static void w(String message, String TAG) {
        if(filter(LogLevel.WARN)) {
            addToMessageQueue(new LogMessage(LogLevel.WARN, message, TAG));
        }
    }

    public static void e(String message, String TAG) {
        if(filter(LogLevel.ERROR)) {
            addToMessageQueue(new LogMessage(LogLevel.ERROR, message, TAG));
        }
    }

    public static void f(String message, String TAG) {
        if(filter(LogLevel.FATAL)) {
            addToMessageQueue(new LogMessage(LogLevel.FATAL, message, TAG));
        }
    }

    public static LinkedBlockingQueue<LogMessage> getQueue() {
        return queue;
    }
}

/**
 * 1. We need to have a logging framework which we can directly use in our project for adding logs.
 * 2. This logger will be used to add the message and with the message we need to add Date and time of the event.
 * 3. We need to add the type of log like error log or debug log etc to understand the kind of log.
 * 4. We need to have a parameter which can tell from which class this has come up with.
 * 5. All the logs will be stored in a file and will print on console as well. At the time of logger initialization we will create a file in given directory.
 * 6. As you mentioned about the " multiple threads are logging simultaneously" Yes I can understand that we can add logs on mainthread because it can create a bottle and there will be multiple locations from where these logs can be added and maybe at multiple locations multiple threads are adding logs so we need to have mutex to write on file at a time.
 * 7. The thread calling method which will add these logs will be different thread and the caller thread responsibilty will be to just call that log method rest of the things like adding in the file or console should be done by the other thread.
 * 8. At the time of initialization we will have one flag which will have the level name like DEBUG and it should have some number which will be used to filter those logs which is higher priority or higher level
 * 9. Queue will be used for adding the logs by the caller thread and picking up the logs by writer thread.
 * 10. there will be interface which need to be implemented by different destination.
 * 11. we need to maintain singleton
 * **/