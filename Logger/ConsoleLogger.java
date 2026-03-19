class ConsoleLogger implements LogSink {
    @Override
    public void log(LogMessage message) {
        System.out.println(message.getTime() + " " + message.getLogLevel() + " " + message.getTAG() + " " + message.getMessage());
    }
}