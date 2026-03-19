class FileLogger implements LogSink {
    private File file;

    FileLogger(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public void log(LogMessage message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){
            writer.write(message.getTime() + " " + message.getLogLevel() + " " + message.getTAG() + " " + message.getMessage());
            writer.newLine();
            writer.flush(); // flush after each write so data isn't stuck in buffer
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}