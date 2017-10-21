package me.wonwoo.testing;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;


public class StopWatchTests {

  @Test
  public void stopWatch() throws InterruptedException {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    TimeUnit.SECONDS.sleep(1);
    stopWatch.stop();
    assertThat(stopWatch.getTotalTimeMillis()).isLessThan(1200);
  }

  @Test
  public void isRunning() throws InterruptedException {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    assertThat(stopWatch.isRunning()).isTrue();
    TimeUnit.SECONDS.sleep(1);
    stopWatch.stop();
    assertThat(stopWatch.isRunning()).isFalse();
    assertThat(stopWatch.getTotalTimeMillis()).isLessThan(1200);
  }

  @Test
  public void reset() throws InterruptedException {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    TimeUnit.SECONDS.sleep(1);
    stopWatch.stop();
    stopWatch.reset();
    assertThat(stopWatch.getTotalTimeMillis()).isEqualTo(0);
    assertThat(stopWatch.isRunning()).isFalse();
  }

}