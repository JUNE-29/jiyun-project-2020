package june.project.book.web;

import java.util.ArrayList;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.domain.Member;
import june.project.book.service.MemberService;
import june.project.util.RequestMapping;

@Component
public class LoginController {

  @Autowired
  MemberService memberService;

  @RequestMapping("/auth/login")
  public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
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

    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String saveEmail = request.getParameter("saveEmail");

    Cookie cookie = new Cookie("email", email);
    if (saveEmail != null) {
      cookie.setMaxAge(60 * 60 * 24 * 30);
    } else {
      cookie.setMaxAge(0);
    }
    response.addCookie(cookie);

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
}
