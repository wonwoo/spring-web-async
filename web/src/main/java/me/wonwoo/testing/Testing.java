package me.wonwoo.testing;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Testing implements AutoCloseable {

  private final ExecutorService executorService;
  private final int actionCount;
  private final StopWatch stopWatch = new StopWatch();
  private long realCount = 0;

  public Testing(int actionCount) {
    this(actionCount, actionCount, Executors.defaultThreadFactory());
  }

  public Testing(int actionCount, int threadCount) {
    this(actionCount, threadCount, Executors.defaultThreadFactory());
  }

  public Testing(int actionCount, int threadCount, ThreadFactory threadFactory) {
    this(actionCount, Executors.newFixedThreadPool(threadCount, threadFactory));
  }

  public Testing(int actionCount, ExecutorService executorService) {
    this.actionCount = actionCount;
    this.executorService = executorService;
  }

  public int totalActionCount() {
    return actionCount;
  }

  public void run(final Runnable action) throws InterruptedException {
    run(360000, action);
  }

  public void run(long timeoutMs, final Runnable action) throws InterruptedException, TimeoutException {
    this.stopWatch.start();
    CountDownLatch countDownLatch = spawnThreads(action);
    if (!countDownLatch.await(timeoutMs, MILLISECONDS)) {
      throw new TimeoutException(timeoutMs, "timed out exception");
    }
    this.realCount = countDownLatch.getCount();
    this.stopWatch.stop();
  }

  public long getTotalTimeSeconds() {
    return stopWatch.getTotalTimeSeconds();
  }

  public long getTotalTimeMillis() {
    return stopWatch.getTotalTimeMillis();
  }

  private CountDownLatch spawnThreads(final Runnable action) {
    final CountDownLatch finished = new CountDownLatch(actionCount);
    CyclicBarrier barrier = new CyclicBarrier(actionCount);
    for (int i = 0; i < actionCount; i++) {
      executorService.submit(() -> {
        try {
          barrier.await();
          action.run();
        } finally {
          finished.countDown();
        }
        return null;
      });
    }
    return finished;
  }

  public void shutdown() {
    executorService.shutdown();
  }

  public long getRealCount() {
    return realCount;
  }

  @Override
  public void close(){
    this.shutdown();
  }
}
