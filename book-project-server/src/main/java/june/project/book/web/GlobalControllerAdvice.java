package june.project.book.web;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class GlobalControllerAdvice {

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {

      @Override
      public void setAsText(String text) throws IllegalArgumentException {
        setValue(java.sql.Date.valueOf(text));
      }
    });
  }
}
