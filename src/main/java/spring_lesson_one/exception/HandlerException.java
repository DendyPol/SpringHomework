package spring_lesson_one.exception;

import java.util.NoSuchElementException;

public class HandlerException extends NoSuchElementException {
  public HandlerException(String str) {
    super(str);
  }
}
