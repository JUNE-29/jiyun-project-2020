package june.project.book.domain;

import java.io.Serializable;
import java.sql.Date;

public class BookBoard implements Serializable {

  private static final long serialVersionUID = 1L;

  private int no;
  private String bookTitle;
  private String author;
  private String publisher;
  private String categories;
  private String publishedDate;
  private String content;
  private String photo;
  private int score;
  private String bookStatus;
  private Date date;
  private int viewCount;

  public static BookBoard valueOf(String csv) {
    String[] data = csv.split(",");

    BookBoard bookBoard = new BookBoard();
    bookBoard.setNo(Integer.parseInt(data[0]));
    bookBoard.setBookTitle(data[1]);
    bookBoard.setAuthor(data[2]);
    bookBoard.setPublisher(data[3]);
    bookBoard.setCategories(data[4]);
    bookBoard.setPublishedDate(data[5]);
    bookBoard.setContent(data[6]);
    bookBoard.setPhoto(data[7]);
    bookBoard.setBookStatus(data[8]);
    bookBoard.setScore(Integer.parseInt(data[9]));
    bookBoard.setDate(Date.valueOf(data[10]));
    bookBoard.setViewCount(Integer.parseInt(data[11]));

    return bookBoard;
  }

  public String toStringCsv() {
    return String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%d,%s,%d", //
        this.getNo(), this.getBookTitle(), this.getAuthor(), this.getPublisher(),
        this.getCategories(), this.getPublishedDate(), this.getContent(), this.getPhoto(),
        this.getBookStatus(), this.getScore(), this.getDate(), this.getViewCount());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((author == null) ? 0 : author.hashCode());
    result = prime * result + ((bookStatus == null) ? 0 : bookStatus.hashCode());
    result = prime * result + ((bookTitle == null) ? 0 : bookTitle.hashCode());
    result = prime * result + ((categories == null) ? 0 : categories.hashCode());
    result = prime * result + ((content == null) ? 0 : content.hashCode());
    result = prime * result + ((date == null) ? 0 : date.hashCode());
    result = prime * result + no;
    result = prime * result + ((photo == null) ? 0 : photo.hashCode());
    result = prime * result + ((publishedDate == null) ? 0 : publishedDate.hashCode());
    result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
    result = prime * result + score;
    result = prime * result + viewCount;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    BookBoard other = (BookBoard) obj;
    if (author == null) {
      if (other.author != null)
        return false;
    } else if (!author.equals(other.author))
      return false;
    if (bookStatus == null) {
      if (other.bookStatus != null)
        return false;
    } else if (!bookStatus.equals(other.bookStatus))
      return false;
    if (bookTitle == null) {
      if (other.bookTitle != null)
        return false;
    } else if (!bookTitle.equals(other.bookTitle))
      return false;
    if (categories == null) {
      if (other.categories != null)
        return false;
    } else if (!categories.equals(other.categories))
      return false;
    if (content == null) {
      if (other.content != null)
        return false;
    } else if (!content.equals(other.content))
      return false;
    if (date == null) {
      if (other.date != null)
        return false;
    } else if (!date.equals(other.date))
      return false;
    if (no != other.no)
      return false;
    if (photo == null) {
      if (other.photo != null)
        return false;
    } else if (!photo.equals(other.photo))
      return false;
    if (publishedDate == null) {
      if (other.publishedDate != null)
        return false;
    } else if (!publishedDate.equals(other.publishedDate))
      return false;
    if (publisher == null) {
      if (other.publisher != null)
        return false;
    } else if (!publisher.equals(other.publisher))
      return false;
    if (score != other.score)
      return false;
    if (viewCount != other.viewCount)
      return false;
    return true;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getBookTitle() {
    return bookTitle;
  }

  public void setBookTitle(String bookTitle) {
    this.bookTitle = bookTitle;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String getCategories() {
    return categories;
  }

  public void setCategories(String categories) {
    this.categories = categories;
  }

  public String getPublishedDate() {
    return publishedDate;
  }

  public void setPublishedDate(String publishedDate) {
    this.publishedDate = publishedDate;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public String getBookStatus() {
    return bookStatus;
  }

  public void setBookStatus(String bookStatus) {
    this.bookStatus = bookStatus;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

}
