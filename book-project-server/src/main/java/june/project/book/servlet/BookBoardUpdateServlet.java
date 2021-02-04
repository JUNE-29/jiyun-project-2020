package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.dao.BookBoardDao;
import june.project.book.domain.BookBoard;

public class BookBoardUpdateServlet implements Servlet {

  BookBoardDao bookBoardDao;

  public BookBoardUpdateServlet(BookBoardDao bookBoardDao) {
    this.bookBoardDao = bookBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호? ");
    out.println("!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    BookBoard old = bookBoardDao.findByNo(no);

    if (old == null) {
      out.println("해당 번호의 게시물이 없습니다.");
      return;
    }

    BookBoard bookBoard = new BookBoard();

    bookBoard.setNo(no);

    out.printf("도서명(%s)? \n", old.getBookTitle());
    out.println("!{}!");
    out.flush();
    bookBoard.setBookTitle(in.nextLine());

    out.printf("지은이(%s)? \n", old.getAuthor());
    out.println("!{}!");
    out.flush();
    bookBoard.setAuthor(in.nextLine());

    out.printf("출판사(%s)? \n", old.getPublisher());
    out.println("!{}!");
    out.flush();
    bookBoard.setPublisher(in.nextLine());

    out.printf("카테고리(%s)? \n", old.getCategories());
    out.println("!{}!");
    out.flush();
    bookBoard.setCategories(in.nextLine());

    out.printf("출판 연도(%s)? \n", old.getPublishedDate());
    out.println("!{}!");
    out.flush();
    bookBoard.setPublishedDate(in.nextLine());

    out.printf("내용(%s)? \n", old.getContent());
    out.println("!{}!");
    out.flush();
    bookBoard.setContent(in.nextLine());

    out.printf("이미지(%s)? \n", old.getPhoto());
    out.println("!{}!");
    out.flush();
    bookBoard.setPhoto(in.nextLine());

    out.printf("진행 상태 (1: 읽음 / 2: 읽는 중 / 3: 읽을 예정) (%d)? \n", old.getBookStatus());
    out.println("!{}!");
    out.flush();
    bookBoard.setBookStatus(Integer.parseInt(in.nextLine()));

    out.printf("평가(%d점)?  \n", old.getScore());
    out.println("!{}!");
    out.flush();
    bookBoard.setScore(Integer.parseInt(in.nextLine()));

    if (bookBoardDao.update(bookBoard) > 0) {
      out.println("변경했습니다.");

    } else {
      out.println("해당 번호의 게시글이 없습니다.");
    }

  }
}
