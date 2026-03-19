class LogMessage {
    private LogLevel logLevel; // Log Level of the log
    private String message; // Actual message
    private String TAG; // TAG is basically class Name
    private LocalDateTime time;

    LogMessage(LogLevel logLevel, String message, String TAG) {
        this.logLevel = logLevel;
        this.message = message;
        this.TAG = TAG;
        this.time = LocalDateTime.now();
    }

    public LocalDateTime getTime() {
        return time;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public String getMessage() {
        return message;
    }

    public String getTAG() {
        return TAG;
    }
}