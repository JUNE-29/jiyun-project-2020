package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.dao.PhotoBoardDao;
import june.project.book.domain.PhotoBoard;
import june.project.book.domain.PhotoFile;
import june.project.util.Prompt;

public class PhotoBoardDetailServlet implements Servlet {

  PhotoBoardDao photoBoardDao;

  public PhotoBoardDetailServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "사진 게시글 번호? ");

    PhotoBoard photoBoard = photoBoardDao.findByNo(no);

    if (photoBoard != null) {
      out.printf("번호: %d\n", photoBoard.getNo());
      out.printf("제목: %s\n", photoBoard.getTitle());
      out.printf("등록일: %s\n", photoBoard.getCreadtedDate());
      out.printf("조회수: %s\n", photoBoard.getViewCount());
      out.printf("책: %s\n", photoBoard.getBookmark().getBookTitle());
      out.println("사진 파일: ");

      for (PhotoFile photoFile : photoBoard.getFiles()) {
        out.printf("> %s\n", photoFile.getFilePath());
      }

    } else {
      out.println("해당 번호의 사진 게시글이 없습니다.");
    }
  }
}
