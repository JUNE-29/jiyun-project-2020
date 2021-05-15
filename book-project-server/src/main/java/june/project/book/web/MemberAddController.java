package june.project.book.web;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.domain.Member;
import june.project.book.service.MemberService;
import june.project.util.RequestMapping;

@Component
public class MemberAddController {

  @Autowired
  MemberService memberService;

  @RequestMapping("/member/add")
  public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
      return "/member/form.jsp";
    }

    Member member = new Member();
    member.setName(request.getParameter("name"));
    member.setEmail(request.getParameter("email"));
    member.setPassword(request.getParameter("password"));

    Part photoPart = request.getPart("photo");
    if (photoPart.getSize() > 0) {
      String dirPath = request.getServletContext().getRealPath("/upload/member");
      String filename = UUID.randomUUID().toString();
      photoPart.write(dirPath + "/" + filename);
      member.setPhoto(filename);
    }

    if (memberService.add(member) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("회원을 추가할 수 없습니다.");
    }
  }
}
