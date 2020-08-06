package june.project.book.domain;

import java.io.Serializable;

public class BookBasket implements Serializable {

  private static final long serialVersionUID = 1L;

  private int no;
  private String bookTitle;
  private String author;
  private String publisher;
  private String categories;
  private String publishedDate;
  private String memo;

  public static BookBasket valueOf(String csv) {
    String[] data = csv.split(",");

    BookBasket bookBasket = new BookBasket();
    bookBasket.setNo(Integer.parseInt(data[0]));
    bookBasket.setBookTitle(data[1]);
    bookBasket.setAuthor(data[2]);
    bookBasket.setPublisher(data[3]);
    bookBasket.setCategories(data[4]);
    bookBasket.setPublishedDate(data[5]);
    bookBasket.setMemo(data[6]);

    return bookBasket;
  }

  public String toStringCsv() {
    return String.format("%d,%s,%s,%s,%s,%s,%s", //
        this.getNo(), this.getBookTitle(), this.getAuthor(), this.getPublisher(),
        this.getCategories(), this.getPublishedDate(), this.getMemo());
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

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((author == null) ? 0 : author.hashCode());
    result = prime * result + ((bookTitle == null) ? 0 : bookTitle.hashCode());
    result = prime * result + ((categories == null) ? 0 : categories.hashCode());
    result = prime * result + ((memo == null) ? 0 : memo.hashCode());
    result = prime * result + no;
    result = prime * result + ((publishedDate == null) ? 0 : publishedDate.hashCode());
    result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
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
    BookBasket other = (BookBasket) obj;
    if (author == null) {
      if (other.author != null)
        return false;
    } else if (!author.equals(other.author))
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
    if (memo == null) {
      if (other.memo != null)
        return false;
    } else if (!memo.equals(other.memo))
      return false;
    if (no != other.no)
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
    return true;
  }


}
