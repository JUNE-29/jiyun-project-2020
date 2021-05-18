package june.project.book.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import june.project.book.domain.Member;
import june.project.book.service.MemberService;

@Controller
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  MemberService memberService;

  @GetMapping("form")
  public void form() {}

  @PostMapping("login")
  public String login(String email, String password, String saveEmail, HttpServletResponse response,
      HttpSession session, Model model) throws Exception {

    Cookie cookie = new Cookie("email", email);
    if (saveEmail != null) {
      cookie.setMaxAge(60 * 60 * 24 * 30);
    } else {
      cookie.setMaxAge(0);
    }

    response.addCookie(cookie);

    Member member = memberService.get(email, password);
    if (member != null) {
      session.setAttribute("loginUser", member);
      model.addAttribute("refreshUrl", "2;url=../../index.html");
    } else {
      session.invalidate();
      model.addAttribute("refreshUrl", "2;url=login");
    }
    return "auth/login";
  }

  @GetMapping("logout")
  public String logout(HttpSession session) throws Exception {
    session.invalidate();
    return "redirect:../../index.html";
  }
}
