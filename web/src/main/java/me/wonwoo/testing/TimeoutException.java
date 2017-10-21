package me.wonwoo.testing;

public class TimeoutException extends RuntimeException {

  private final long time;
  private final String message;

  public TimeoutException(long time, String message) {
    this.time = time;
    this.message = message;
  }

  public long getTime() {
    return time;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
