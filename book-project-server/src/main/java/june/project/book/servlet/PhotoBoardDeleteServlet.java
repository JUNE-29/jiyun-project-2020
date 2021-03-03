package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.service.PhotoBoardService;
import june.project.util.Component;
import june.project.util.Prompt;
import june.project.util.RequestMapping;

@Component
public class PhotoBoardDeleteServlet {

  PhotoBoardService photoBoardService;

  public PhotoBoardDeleteServlet(PhotoBoardService photoBoardService) {
    this.photoBoardService = photoBoardService;
  }

  @RequestMapping("/photoboard/delete")
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    photoBoardService.delete(no);
    out.println("사진 게시글을 삭제했습니다.");
  }
}
