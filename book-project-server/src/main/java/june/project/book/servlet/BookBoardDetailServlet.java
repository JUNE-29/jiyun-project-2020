package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.dao.BookBoardDao;
import june.project.book.domain.BookBoard;
import june.project.util.Prompt;

public class BookBoardDetailServlet implements Servlet {

  BookBoardDao bookBoardDao;

  public BookBoardDetailServlet(BookBoardDao bookBoardDao) {
    this.bookBoardDao = bookBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    BookBoard bookBoard = bookBoardDao.findByNo(no);

    if (bookBoard != null) {
      out.printf("번호: %d\n", bookBoard.getNo());
      out.printf("도서명: %s\n", bookBoard.getBookTitle());
      out.printf("지은이: %s\n", bookBoard.getAuthor());
      out.printf("출판사: %s\n", bookBoard.getPublisher());
      out.printf("출판 연도: %s\n", bookBoard.getPublishedDate());
      out.printf("카테고리: %s\n", bookBoard.getCategories());
      out.printf("내용: %s\n", bookBoard.getContent());
      out.printf("이미지: %s\n", bookBoard.getPhoto());
      out.printf("평가: %d점\n", bookBoard.getScore());
      out.printf("진행 상태 (1: 읽음 / 2: 읽는 중 / 3: 읽을 예정): %d\n", bookBoard.getBookStatus());
      out.printf("등록일: %s\n", bookBoard.getDate());

    } else {
      out.println("해당 번호의 게시물이 없습니다.");
    }
  }
}
