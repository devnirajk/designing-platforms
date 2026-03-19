enum LogLevel {
    DEBUG(5),
    INFO(4),
    WARN(3),
    ERROR(2),
    FATAL(1);

    public final int level;

    LogLevel(int level) {
        this.level = level;
    }
}