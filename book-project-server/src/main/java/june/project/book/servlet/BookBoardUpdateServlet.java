package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import june.project.book.domain.BookBoard;
import june.project.book.service.BookBoardService;
import june.project.util.RequestMapping;

@Component
public class BookBoardUpdateServlet {

  BookBoardService bookBoardService;

  public BookBoardUpdateServlet(BookBoardService bookBoardService) {
    this.bookBoardService = bookBoardService;
  }

  @RequestMapping("/book/update")
  public void service(Map<String, String> params, PrintStream out) throws Exception {

    BookBoard bookBoard = new BookBoard();

    bookBoard.setNo(Integer.parseInt(params.get("no")));
    bookBoard.setBookTitle(params.get("bookTitle"));
    bookBoard.setAuthor(params.get("author"));
    bookBoard.setPublisher(params.get("publisher"));
    bookBoard.setCategories(params.get("categories"));
    bookBoard.setPublishedDate(params.get("publishedDate"));
    bookBoard.setContent(params.get("content"));
    bookBoard.setPhoto(params.get("photo"));
    bookBoard.setScore(Integer.parseInt(params.get("score")));
    bookBoard.setBookStatus(Integer.parseInt(params.get("bookStatus")));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/book/list'>");
    out.println("<title>Book 게시글 변경</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Book 게시글 변경 결과</h1>");

    if (bookBoardService.update(bookBoard) > 0) {
      out.println("<p>Book 게시글을 변경했습니다.</p>");

    } else {
      out.println("<p>Book 게시글 변경에 실패했습니다.</p>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
