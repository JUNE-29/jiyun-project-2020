package june.project.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.service.MemberService;
import june.project.util.RequestMapping;

@Component
public class MemberDeleteController {

  @Autowired
  MemberService memberService;

  @RequestMapping("/member/delete")
  public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    if (memberService.delete(no) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("번호가 유효하지 않습니다.");
    }
  }
}
