package june.project.book;

import java.util.Map;
import june.project.book.context.ApplicationContextListener;
import june.project.book.dao.json.BookBasketJsonFileDao;
import june.project.book.dao.json.BookBoardJsonFileDao;
import june.project.book.dao.json.BookmarkJsonFileDao;
import june.project.book.dao.json.MemberJsonFileDao;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다.");

    // 호출한 쪽에서 DAO 객체를 사용할 수 있도록 Map 객체에 담아둔다.
    // 객체 주소를 공유하는 것이다.
    context.put("bookBoardDao", new BookBoardJsonFileDao("./bookBoard.json"));
    context.put("bookmarkDao", new BookmarkJsonFileDao("./bookmark.json"));
    context.put("bookBasketDao", new BookBasketJsonFileDao("./bookBasket.json"));
    context.put("memberDao", new MemberJsonFileDao("./member.json"));
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장합니다.");
  }
}
