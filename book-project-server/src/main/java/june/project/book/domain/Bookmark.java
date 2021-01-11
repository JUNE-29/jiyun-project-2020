package june.project.book.domain;

import java.io.Serializable;
import java.sql.Date;

public class TranscriptionBoard implements Serializable {

  private static final long serialVersionUID = 1L;

  private int no;
  private String title;
  private String bookTitle;
  private String author;
  private String publisher;
  private String content;
  private String photo;
  private Date date;

  public static TranscriptionBoard valueOf(String csv) {
    String[] data = csv.split(",");

    TranscriptionBoard transcription = new TranscriptionBoard();
    transcription.setNo(Integer.parseInt(data[0]));
    transcription.setTitle(data[1]);
    transcription.setBookTitle(data[2]);
    transcription.setAuthor(data[3]);
    transcription.setPublisher(data[4]);
    transcription.setContent(data[5]);
    transcription.setPhoto(data[6]);
    transcription.setDate(Date.valueOf(data[7]));
    return transcription;
  }

  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%s,%s,%s", this.getNo(), this.getTitle(),
        this.getBookTitle(), this.getAuthor(), this.getPublisher(), this.getContent(),
        this.getPhoto(), this.getDate());
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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((author == null) ? 0 : author.hashCode());
    result = prime * result + ((bookTitle == null) ? 0 : bookTitle.hashCode());
    result = prime * result + ((content == null) ? 0 : content.hashCode());
    result = prime * result + ((date == null) ? 0 : date.hashCode());
    result = prime * result + no;
    result = prime * result + ((photo == null) ? 0 : photo.hashCode());
    result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
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
    TranscriptionBoard other = (TranscriptionBoard) obj;
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
    if (publisher == null) {
      if (other.publisher != null)
        return false;
    } else if (!publisher.equals(other.publisher))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    return true;
  }

}
