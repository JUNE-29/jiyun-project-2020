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
public class MemberSearchController {

  @Autowired
  MemberService memberService;

  @RequestMapping("/member/search")
  public String search(HttpServletRequest request, HttpServletResponse response) throws Exception {

    String keyword = request.getParameter("keyword");
    List<Member> members = memberService.search(keyword);
    request.setAttribute("list", members);
    return "/member/search.jsp";
  }
}
