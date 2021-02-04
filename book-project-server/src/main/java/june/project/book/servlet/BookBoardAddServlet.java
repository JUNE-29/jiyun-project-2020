package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.dao.BookBoardDao;
import june.project.book.domain.BookBoard;

public class BookBoardAddServlet implements Servlet {

  BookBoardDao bookBoardDao;

  public BookBoardAddServlet(BookBoardDao bookBoardDao) {
    this.bookBoardDao = bookBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {


    BookBoard bookBoard = new BookBoard();

    out.println("도서명?");
    out.println("!{}!");
    out.flush();
    bookBoard.setBookTitle(in.nextLine());

    out.println("지은이?");
    out.println("!{}!");
    out.flush();
    bookBoard.setAuthor(in.nextLine());

    out.println("출판사?");
    out.println("!{}!");
    out.flush();
    bookBoard.setPublisher(in.nextLine());

    out.println("카테고리?");
    out.println("!{}!");
    out.flush();
    bookBoard.setCategories(in.nextLine());

    out.println("출판 연도?");
    out.println("!{}!");
    out.flush();
    bookBoard.setPublishedDate(in.nextLine());

    out.println("내용(메모)?");
    out.println("!{}!");
    out.flush();
    bookBoard.setContent(in.nextLine());

    out.println("이미지?");
    out.println("!{}!");
    out.flush();
    bookBoard.setPhoto(in.nextLine());

    out.println("진행 상태 (1: 읽음 / 2: 읽는 중 / 3: 읽을 예정)?");
    out.println("!{}!");
    out.flush();
    bookBoard.setBookStatus(Integer.parseInt(in.nextLine()));

    out.println("책에 대한 점수(5점만점)?");
    out.println("!{}!");
    out.flush();
    bookBoard.setScore(Integer.parseInt(in.nextLine()));

    if (bookBoardDao.insert(bookBoard) > 0) {
      out.println("저장하였습니다.");

    } else {
      out.println("게시글 등록에 실패했습니다.");
    }
  }
}
