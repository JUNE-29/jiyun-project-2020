package june.project.book.dao;

import java.util.List;
import june.project.book.domain.BookBasket;

public class BookBasketObjectFileDao extends AbstractObjectFileDao<BookBasket> {


  public BookBasketObjectFileDao(String filename) {
    super(filename);
  }


  public List<BookBasket> findAll() throws Exception {
    return list;
  }

  public int insert(BookBasket bookBasket) throws Exception {

    if (indexOf(bookBasket.getNo()) > -1) {
      return 0;
    }

    list.add(bookBasket);
    saveData();
    return 1;
  }

  public BookBasket findByNo(int no) throws Exception {

    int index = indexOf(no);

    if (index == -1) {
      return null;
    }

    return list.get(index);
  }

  public int update(BookBasket bookBasket) throws Exception {

    int index = indexOf(bookBasket.getNo());

    if (index == -1) {
      return 0;
    }

    list.set(index, bookBasket);
    saveData();
    return 1;
  }


  public int delete(int no) throws Exception {

    int index = indexOf(no);

    if (index == -1) {
      return 0;
    }

    list.remove(index);
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