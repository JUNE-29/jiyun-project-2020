package june.project.book.servlet;

import java.io.PrintStream;
import java.sql.Connection;
import java.util.Scanner;
import june.project.book.dao.PhotoBoardDao;
import june.project.book.dao.PhotoFileDao;
import june.project.util.ConnectionFactory;
import june.project.util.Prompt;

public class PhotoBoardDeleteServlet implements Servlet {

  ConnectionFactory conFactory;
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardDeleteServlet(ConnectionFactory conFactory, PhotoBoardDao photoBoardDao,
      PhotoFileDao photoFileDao) {
    this.conFactory = conFactory;
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");
    photoFileDao.deleteAll(no);

    // 트랜잭션 시작
    Connection con = conFactory.getConnection();
    con.setAutoCommit(false);

    try {
      if (photoBoardDao.delete(no) == 0) {
        throw new Exception("해당 번호의 사진 게시글이 없습니다.");
      }
      con.commit();
      out.println("사진 게시글을 삭제했습니다.");

    } catch (Exception e) {
      con.rollback();
      out.println(e.getMessage());

    } finally {
      con.setAutoCommit(true);
    }
  }
}
