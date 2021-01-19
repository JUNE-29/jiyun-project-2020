package june.project.book.dao.json;

import java.util.List;
import june.project.book.dao.BookmarkDao;
import june.project.book.domain.Bookmark;

public class BookmarkJsonFileDao extends AbstractJsonFileDao<Bookmark> implements BookmarkDao {


  public BookmarkJsonFileDao(String filename) {
    super(filename);
  }

  @Override
  public List<Bookmark> findAll() throws Exception {
    return list;
  }

  @Override
  public int insert(Bookmark bookmark) throws Exception {

    if (indexOf(bookmark.getNo()) > -1) {
      return 0;
    }

    list.add(bookmark);
    saveData();
    return 1;
  }

  @Override
  public Bookmark findByNo(int no) throws Exception {

    int index = indexOf(no);
    if (index == -1) {
      return null;
    }

    return list.get(index);
  }

  @Override
  public int update(Bookmark bookmark) throws Exception {

    int index = indexOf(bookmark.getNo());

    if (index == -1) {
      return 0;
    }

    list.set(index, bookmark);
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
