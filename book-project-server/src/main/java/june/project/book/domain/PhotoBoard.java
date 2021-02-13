package june.project.book.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class PhotoBoard implements Serializable {

  private static final long serialVersionUID = 1L;

  int no;
  String title;
  Date creadtedDate;
  int viewCount;
  Bookmark bookmark;
  List<PhotoFile> files;

  @Override
  public String toString() {
    return "PhotoBoard [no=" + no + ", title=" + title + ", creadtedDate=" + creadtedDate
        + ", viewCount=" + viewCount + ", bookmark=" + bookmark + ", files=" + files + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getCreadtedDate() {
    return creadtedDate;
  }

  public void setCreadtedDate(Date creadtedDate) {
    this.creadtedDate = creadtedDate;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public Bookmark getBookmark() {
    return bookmark;
  }

  public void setBookmark(Bookmark bookmark) {
    this.bookmark = bookmark;
  }

  public List<PhotoFile> getFiles() {
    return files;
  }

  public void setFiles(List<PhotoFile> files) {
    this.files = files;
  }
}
