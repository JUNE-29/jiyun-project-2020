package june.project.book.web;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.domain.Member;
import june.project.book.service.MemberService;
import june.project.util.RequestMapping;

@Component
public class AuthController {

  @Autowired
  MemberService memberService;

  @RequestMapping("/auth/form")
  public String form(HttpServletRequest request, Map<String, Object> model) {
    String email = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().contentEquals("email")) {
          email = cookie.getValue();
          break;
        }
      }
    }
    request.setAttribute("email", email);
    return "/auth/form.jsp";
  }


  @RequestMapping("/auth/login")
  public String login(HttpServletRequest request, String email, String password, String saveEmail)
      throws Exception {

    Cookie cookie = new Cookie("email", email);
    if (saveEmail != null) {
      cookie.setMaxAge(60 * 60 * 24 * 30);
    } else {
      cookie.setMaxAge(0);
    }

    // 프론트 컨트롤러가 쿠키를 응답헤더에 담을 수 있도록
    // 쿠키 바구니에 저장한다.
    @SuppressWarnings("unchecked")
    ArrayList<Cookie> cookies = (ArrayList<Cookie>) request.getAttribute("cookies");
    cookies.add(cookie);

    Member member = memberService.get(email, password);
    if (member != null) {
      request.getSession().setAttribute("loginUser", member);
      request.setAttribute("refreshUrl", "2;url=../../index.html");
    } else {
      request.getSession().invalidate();
      request.setAttribute("refreshUrl", "2;url=login");
    }
    return "/auth/login.jsp";
  }

  @RequestMapping("/auth/logout")
  public String logout(HttpServletRequest request) throws Exception {
    request.getSession().invalidate();
    return "redirect:../../index.html";
  }
}
