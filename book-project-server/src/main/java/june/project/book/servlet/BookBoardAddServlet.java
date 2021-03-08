package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import june.project.book.domain.BookBoard;
import june.project.book.service.BookBoardService;
import june.project.util.Prompt;
import june.project.util.RequestMapping;

@Component
public class BookBoardAddServlet {

  BookBoardService bookBoardService;

  public BookBoardAddServlet(BookBoardService bookBoardService) {
    this.bookBoardService = bookBoardService;
  }

  @RequestMapping("/book/add")
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

    bookBoardService.add(bookBoard);
    out.println("저장하였습니다.");
  }
}
