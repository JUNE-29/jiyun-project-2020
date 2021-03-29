package june.project.book.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import june.project.book.AppConfig;

@WebServlet(value = "/AppInitServlet", loadOnStartup = 1)
// 클라이언트가 요청하지 않고 객체를 생성할 수 있도록 loadOnStartup 설정
public class AppInitServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  static Logger logger = LogManager.getLogger(AppInitServlet.class);

  @Override
  public void init() throws ServletException {
    try {
      ApplicationContext iocContainer = new AnnotationConfigApplicationContext( //
          AppConfig.class //
      );
      printBeans(iocContainer);

      ServletContext servletContext = getServletContext();
      servletContext.setAttribute("iocContainer", iocContainer);
      logger.debug("----------------------------------");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  private void printBeans(ApplicationContext appCtx) {
    logger.debug("Spring IoC 컨테이너에 들어있는 객체들:");
    String[] beanNames = appCtx.getBeanDefinitionNames();
    for (String beanName : beanNames) {
      logger.debug(String.format("%s ========> %s", beanName,
          appCtx.getBean(beanName).getClass().getName()));
    }
  }
}
