package june.project.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.service.MemberService;
import june.project.util.RequestMapping;

@Component
public class LogoutController {

  @Autowired
  MemberService memberService;

  @RequestMapping("/auth/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.getSession().invalidate();
    return "redirect:../../index.html";
  }
}
