package june.project.book.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import june.project.book.domain.BookBoard;
import june.project.book.service.BookBoardService;
import june.project.util.RequestMapping;

@Component
public class BookBoardAddServlet {

  BookBoardService bookBoardService;

  public BookBoardAddServlet(BookBoardService bookBoardService) {
    this.bookBoardService = bookBoardService;
  }

  @RequestMapping("/book/add")
  public void service(Map<String, String> params, PrintStream out) throws Exception {

    BookBoard bookBoard = new BookBoard();
    bookBoard.setBookTitle(params.get("bookTitle"));
    bookBoard.setAuthor(params.get("author"));
    bookBoard.setPublisher(params.get("publisher"));
    bookBoard.setCategories(params.get("Categories"));
    bookBoard.setPublishedDate(params.get("publishedDate"));
    bookBoard.setContent(params.get("content"));
    bookBoard.setPhoto(params.get("photo"));
    bookBoard.setBookStatus(Integer.parseInt(params.get("bookStatus")));
    bookBoard.setScore(Integer.parseInt(params.get("score")));

    bookBoardService.add(bookBoard);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/book/list'>");
    out.println("<title>Book 게시글 입력</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게시글 입력 결과</h1>");
    out.println("<p>새 게시글을 등록했습니다.</p>");
    out.println("</body>");
    out.println("</html>");
  }
}
