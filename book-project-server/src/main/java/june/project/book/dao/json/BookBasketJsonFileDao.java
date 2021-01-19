package june.project.book.dao.json;

import java.util.List;
import june.project.book.dao.BookBasketDao;
import june.project.book.domain.BookBasket;

public class BookBasketJsonFileDao extends AbstractJsonFileDao<BookBasket>
    implements BookBasketDao {

  public BookBasketJsonFileDao(String filename) {
    super(filename);
  }

  @Override
  public List<BookBasket> findAll() throws Exception {
    return list;
  }

  @Override
  public int insert(BookBasket bookBasket) throws Exception {

    if (indexOf(bookBasket.getNo()) > -1) {
      return 0;
    }

    list.add(bookBasket);
    saveData();
    return 1;
  }

  @Override
  public BookBasket findByNo(int no) throws Exception {

    int index = indexOf(no);

    if (index == -1) {
      return null;
    }

    return list.get(index);
  }

  @Override
  public int update(BookBasket bookBasket) throws Exception {

    int index = indexOf(bookBasket.getNo());

    if (index == -1) {
      return 0;
    }

    list.set(index, bookBasket);
    saveData();
    return 1;
  }


  @Override
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
