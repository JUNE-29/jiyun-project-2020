package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.dao.PhotoBoardDao;
import june.project.book.dao.PhotoFileDao;
import june.project.util.Prompt;

public class PhotoBoardDeleteServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardDeleteServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    photoFileDao.deleteAll(no);

    try {

      if (photoBoardDao.delete(no) == 0) {
        throw new Exception("해당 번호의 사진 게시글이 없습니다.");
      }
      out.println("사진 게시글을 삭제했습니다.");

    } catch (Exception e) {
      out.println(e.getMessage());

    }
  }
}
