package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.util.Component;

@Component("/hello")
public class HelloServlet implements Servlet {

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("안녕하세요!");
  }
}
