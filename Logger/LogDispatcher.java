class LogDispatcher {
    private LinkedBlockingQueue<LogMessage> queue;
    private ExecutorService service;
    List<LogSink> list;

    LogDispatcher(Logger logger, ExecutorService service, List<LogSink> list) {
        this.queue = logger.getQueue();
        this.service = service;
        service.submit(() -> consumer());
        this.list = list;
    }

    public void consumer() {
        while(true) {
            try {
                LogMessage message = queue.take();
                for(LogSink consumers: list) {
                    try {
                        consumers.log(message);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (Exception e) {
                service.shutdown();
                throw new RuntimeException(e);
            }

        }
    }
}