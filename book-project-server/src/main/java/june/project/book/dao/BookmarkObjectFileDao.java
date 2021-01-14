package june.project.book.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import june.project.book.domain.Bookmark;

public class BookmarkObjectFileDao {

  String filename;
  List<Bookmark> list;

  public BookmarkObjectFileDao(String filename) {
    this.filename = filename;
    list = new ArrayList<>();
    loadData();
  }

  @SuppressWarnings("unchecked")
  private void loadData() {
    File file = new File(filename);

    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {

      list = (List<Bookmark>) in.readObject();

      System.out.printf("총 %d 개의 북마크 데이터를 로딩했습니다.\n", list.size());
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! -" + e.getMessage());
    }
  }

  private void saveData() {
    File file = new File(filename);
    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {

      out.reset();
      out.writeObject(list);

      System.out.printf("총 %d 개의 북마크 데이터를 저장했습니다.\n", list.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! -" + e.getMessage());
    }
  }

  public List<Bookmark> findAll() throws Exception {
    return list;
  }

  public int insert(Bookmark bookmark) throws Exception {

    if (indexOf(bookmark.getNo()) > -1) {
      return 0;
    }

    list.add(bookmark);
    saveData();
    return 1;
  }

  public Bookmark findByNo(int no) throws Exception {

    int index = indexOf(no);
    if (index == -1) {
      return null;
    }

    return list.get(index);
  }

  public int update(Bookmark bookmark) throws Exception {

    int index = indexOf(bookmark.getNo());

    if (index == -1) {
      return 0;
    }

    list.set(index, bookmark);
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

  private int indexOf(int no) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
