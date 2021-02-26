package june.project.util;

import java.io.File;
import org.apache.ibatis.io.Resources;

public class ApplicationContext {

  // 역할:
  // - 클래스를 찾아 객체를 생성한다.
  // - 객체가 일을 하는데 필요로하는 의존 객체를 주입한다.
  // - 객체를 생성과 소멸을 관리한다.

  public ApplicationContext(String packageName) throws Exception {
    File path = Resources.getResourceAsFile(packageName.replace('.', '/'));

    // 해당 결로를 뒤져서 모든 파일의 이름을 알아낸다.
    findFiles(path);
  }

  private void findFiles(File path) {
    File[] files = path.listFiles();
    for (File f : files) {
      if (f.isFile()) {
        System.out.println("ApplicationContext: " + f.getName());
      } else {
        findFiles(f);
      }
    }
  }
}
