package june.project.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.domain.Member;
import june.project.book.service.MemberService;
import june.project.util.RequestMapping;

@Component
public class MemberDetailController {

  @Autowired
  MemberService memberService;

  @RequestMapping("/member/detail")
  public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));

    Member member = memberService.get(no);
    request.setAttribute("member", member);
    return "/member/detail.jsp";
  }
}
