package june.project.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import june.project.book.domain.PhotoBoard;
import june.project.book.service.PhotoBoardService;
import june.project.util.RequestMapping;

@Component
public class PhotoBoardDetailController {

  @Autowired
  PhotoBoardService photoBoardService;

  @RequestMapping("/photoboard/detail")
  public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    PhotoBoard photoBoard = photoBoardService.get(no);
    request.setAttribute("photoBoard", photoBoard);
    return "/photoboard/detail.jsp";
  }
}
