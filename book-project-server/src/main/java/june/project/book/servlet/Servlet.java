package june.project.book.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public interface Servlet {
  default void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {}

  default void service(Scanner in, PrintStream out) throws Exception {}

  // default 로 선언함으로서 둘 중 하나를 구현할 수 있다.
  // 아니면 둘다 오버라이딩 안할 수 있다.
}
