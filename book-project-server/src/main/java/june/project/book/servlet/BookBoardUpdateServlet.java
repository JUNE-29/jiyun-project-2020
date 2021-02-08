package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.dao.BookBoardDao;
import june.project.book.domain.BookBoard;
import june.project.util.Prompt;

public class BookBoardUpdateServlet implements Servlet {

  BookBoardDao bookBoardDao;

  public BookBoardUpdateServlet(BookBoardDao bookBoardDao) {
    this.bookBoardDao = bookBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    BookBoard old = bookBoardDao.findByNo(no);

    if (old == null) {
      out.println("해당 번호의 게시물이 없습니다.");
      return;
    }

    BookBoard bookBoard = new BookBoard();

    bookBoard.setNo(no);

    bookBoard.setBookTitle(Prompt.getString(in, out, //
        String.format("도서명(%s)? \n", old.getBookTitle()), old.getBookTitle()));

    bookBoard.setAuthor(Prompt.getString(in, out, //
        String.format("지은이(%s)? \n", old.getAuthor()), old.getAuthor()));

    bookBoard.setPublisher(Prompt.getString(in, out, //
        String.format("출판사(%s)? \n", old.getPublisher()), old.getPublisher()));

    bookBoard.setCategories(Prompt.getString(in, out, //
        String.format("카테고리(%s)? \n", old.getCategories()), old.getCategories()));

    bookBoard.setPublishedDate(Prompt.getString(in, out, //
        String.format("출판 연도(%s)? \n", old.getPublishedDate()), old.getPublishedDate()));

    bookBoard.setContent(Prompt.getString(in, out, //
        String.format("내용(%s)? \n", old.getContent()), old.getContent()));

    bookBoard.setPhoto(Prompt.getString(in, out, //
        String.format("이미지(%s)? \n", old.getPhoto()), old.getPhoto()));

    bookBoard.setBookStatus(Prompt.getInt(in, out, //
        String.format("진행 상태 (1: 읽음 / 2: 읽는 중 / 3: 읽을 예정) (%d)? \n", old.getBookStatus()),
        String.valueOf(old.getBookStatus())));

    bookBoard.setScore(Prompt.getInt(in, out, //
        String.format("평가(%d점)? \n", old.getScore()), //
        String.valueOf(old.getScore())));

    if (bookBoardDao.update(bookBoard) > 0) {
      out.println("변경했습니다.");

    } else {
      out.println("해당 번호의 게시글이 없습니다.");
    }

  }
}
