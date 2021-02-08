package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import june.project.book.dao.BookBoardDao;
import june.project.book.domain.BookBoard;
import june.project.util.Prompt;

public class BookBoardAddServlet implements Servlet {

  BookBoardDao bookBoardDao;

  public BookBoardAddServlet(BookBoardDao bookBoardDao) {
    this.bookBoardDao = bookBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {


    BookBoard bookBoard = new BookBoard();

    bookBoard.setBookTitle(Prompt.getString(in, out, "도서명?"));

    bookBoard.setAuthor(Prompt.getString(in, out, "지은이?"));

    bookBoard.setPublisher(Prompt.getString(in, out, "출판사?"));

    bookBoard.setCategories(Prompt.getString(in, out, "카테고리?"));

    bookBoard.setPublishedDate(Prompt.getString(in, out, "출판 연도?"));

    bookBoard.setContent(Prompt.getString(in, out, "내용(메모)?"));

    bookBoard.setPhoto(Prompt.getString(in, out, "이미지?"));

    bookBoard.setBookStatus(Prompt.getInt(in, out, "진행 상태 (1: 읽음 / 2: 읽는 중 / 3: 읽을 예정)?"));

    bookBoard.setScore(Prompt.getInt(in, out, "책에 대한 점수(5점만점)?"));

    if (bookBoardDao.insert(bookBoard) > 0) {
      out.println("저장하였습니다.");

    } else {
      out.println("게시글 등록에 실패했습니다.");
    }
  }
}
