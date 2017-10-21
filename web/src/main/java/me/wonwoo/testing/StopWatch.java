package me.wonwoo.testing;

public class StopWatch {

  private boolean running;
  private long startTimeMillis;
  private long totalTimeMillis;

  public void start() {
    if (this.running) {
      throw new IllegalStateException("already running");
    }
    this.startTimeMillis = System.currentTimeMillis();
    this.running = true;
  }

  public void stop() {
    if (!this.running) {
      throw new IllegalStateException("not running");
    }
    long lastTime = System.currentTimeMillis() - this.startTimeMillis;
    this.totalTimeMillis += lastTime;
    this.running = false;
  }

  public void reset() {
    running = false;
    startTimeMillis = 0;
    totalTimeMillis = 0;
  }

  public long getTotalTimeSeconds() {
    return this.totalTimeMillis / 1000;
  }

  public long getTotalTimeMillis() {
    return this.totalTimeMillis;
  }

  public boolean isRunning() {
    return this.running;
  }

  public void print() {
    System.out.println("------------------------------------");
    System.out.println("totalTimeMillis : [" + getTotalTimeMillis() + "]");
    System.out.println("------------------------------------");
  }
}
