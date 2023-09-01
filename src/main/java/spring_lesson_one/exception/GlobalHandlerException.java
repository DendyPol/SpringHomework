package spring_lesson_one.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalHandlerException {
  @ExceptionHandler(HandlerException.class)
  public void NotFoundException(Exception e) {
    log.error(e.getMessage());
  }
}
