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
public class MemberUpdateController {

  @Autowired
  MemberService memberService;

  @RequestMapping("/member/update")
  public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {

    Member member = new Member();
    member.setNo(Integer.parseInt(request.getParameter("no")));
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

    if (memberService.update(member) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("번호가 유효하지 않습니다.");
    }
  }
}
