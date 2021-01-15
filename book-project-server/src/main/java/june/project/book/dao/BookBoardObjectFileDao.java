package june.project.book.dao;

import java.util.List;
import june.project.book.domain.BookBoard;

public class BookBoardObjectFileDao extends AbstractObjectFileDao<BookBoard> {


  public BookBoardObjectFileDao(String filename) {
    super(filename);
  }

  // 서블릿 객체들이 데이터를 다룰 때 사용할 메서드를 정의한다.
  public int insert(BookBoard BookBoard) throws Exception {

    if (indexOf(BookBoard.getNo()) > -1) {
      return 0;
    }

    list.add(BookBoard);
    saveData();
    return 1;
  }

  public List<BookBoard> findAll() throws Exception {
    return list;
  }

  public BookBoard findByNo(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return null;
    }
    return list.get(index);
  }

  public int update(BookBoard bookBoard) throws Exception {
    int index = indexOf(bookBoard.getNo());

    if (index == -1) {
      return 0;
    }

    list.set(index, bookBoard);
    saveData();
    return 1;
  }

  public int delete(int no) throws Exception {

    int index = indexOf(no);
    if (index == -1) {
      return 0;
    }

    list.remove(index);
    saveData();
    return 1;
  }


  @Override
  protected <K> int indexOf(K key) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == (int) key) {
        return i;
      }
    }
    return -1;
  }
}
