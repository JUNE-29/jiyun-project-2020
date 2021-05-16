package june.project.book.web;

import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.domain.Member;
import june.project.book.service.MemberService;
import june.project.util.RequestMapping;

@Component
public class MemberController {

  @Autowired
  MemberService memberService;

  @RequestMapping("/member/form")
  public String form() {
    return "/member/form.jsp";
  }

  @RequestMapping("/member/add")
  public String add(HttpServletRequest request, Member member, Part photoFile) throws Exception {

    if (photoFile.getSize() > 0) {
      String dirPath = request.getServletContext().getRealPath("/upload/member");
      String filename = UUID.randomUUID().toString();
      photoFile.write(dirPath + "/" + filename);
      member.setPhoto(filename);
    }

    if (memberService.add(member) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("회원을 추가할 수 없습니다.");
    }
  }

  @RequestMapping("/member/list")
  public String list(Map<String, Object> model) throws Exception {
    model.put("list", memberService.list());
    return "/member/list.jsp";
  }

  @RequestMapping("/member/detail")
  public String detail(int no, Map<String, Object> model) throws Exception {
    model.put("member", memberService.get(no));
    return "/member/detail.jsp";
  }

  @RequestMapping("/member/update")
  public String update(HttpServletRequest request, Member member, Part photoFile) throws Exception {

    if (photoFile.getSize() > 0) {
      String dirPath = request.getServletContext().getRealPath("/upload/member");
      String filename = UUID.randomUUID().toString();
      photoFile.write(dirPath + "/" + filename);
      member.setPhoto(filename);
    }

    if (memberService.update(member) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("번호가 유효하지 않습니다.");
    }
  }

  @RequestMapping("/member/delete")
  public String delete(int no) throws Exception {
    if (memberService.delete(no) > 0) {
      return "redirect:list";
    } else {
      throw new Exception("번호가 유효하지 않습니다.");
    }
  }

  @RequestMapping("/member/search")
  public String search(String keyword, Map<String, Object> model) throws Exception {
    model.put("list", memberService.search(keyword));
    return "/member/search.jsp";
  }
}
