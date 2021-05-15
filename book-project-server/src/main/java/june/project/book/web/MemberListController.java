package june.project.book.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.domain.Member;
import june.project.book.service.MemberService;
import june.project.util.RequestMapping;

@Component
public class MemberListController {

  @Autowired
  MemberService memberService;

  @RequestMapping("/member/list")
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {

    List<Member> member = memberService.list();
    request.setAttribute("list", member);
    return "/member/list.jsp";
  }
}
