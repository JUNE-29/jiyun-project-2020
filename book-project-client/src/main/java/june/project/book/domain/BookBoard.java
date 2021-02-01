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
  private int bookStatus;
  private Date date;

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
    bookBoard.setBookStatus(Integer.parseInt(data[8]));
    bookBoard.setScore(Integer.parseInt(data[9]));
    bookBoard.setDate(Date.valueOf(data[10]));

    return bookBoard;
  }


  public String toStringCsv() {
    return String.format("%d,%s,%s,%s,%s,%s,%s,%s,%d,%d,%s", //
        this.getNo(), this.getBookTitle(), this.getAuthor(), this.getPublisher(),
        this.getCategories(), this.getPublishedDate(), this.getContent(), this.getPhoto(),
        this.getBookStatus(), this.getScore(), this.getDate());
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

  public int getBookStatus() {
    return bookStatus;
  }

  public void setBookStatus(int bookStatus) {
    this.bookStatus = bookStatus;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

}
