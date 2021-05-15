package june.project.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.service.PhotoBoardService;
import june.project.util.RequestMapping;

@Component
public class PhotoBoardDeleteController {

  @Autowired
  PhotoBoardService photoBoardService;

  @RequestMapping("/photoboard/delete")
  public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int bookmarkNo = Integer.parseInt(request.getParameter("bookmarkNo"));
    int no = Integer.parseInt(request.getParameter("no"));
    photoBoardService.delete(no);
    return "redirect:list?bookmarkNo=" + bookmarkNo;
  }
}
