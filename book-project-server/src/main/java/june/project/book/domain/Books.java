package june.project.book.domain;

import java.io.Serializable;

public class Books implements Serializable {

  private static final long serialVersionUID = 1L;

  private int no;
  private String bookTitle;
  private String author;
  private String publisher;
  private String isbn;
  private String publishedDate;
  private String content;
  private String thumbnail;
  private int score;
  private int expectedScore;

  private int bookboardNo;
  private int memberNo;

  Member member;

  @Override
  public String toString() {
    return "Books [no=" + no + ", bookTitle=" + bookTitle + ", author=" + author + ", publisher="
        + publisher + ", isbn=" + isbn + ", publishedDate=" + publishedDate + ", content=" + content
        + ", thumbnail=" + thumbnail + ", score=" + score + ", expectedScore=" + expectedScore
        + ", bookboardNo=" + bookboardNo + ", memberNo=" + memberNo + "]";
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

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
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

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getExpectedScore() {
    return expectedScore;
  }


  public void setExpectedScore(int expectedScore) {
    this.expectedScore = expectedScore;
  }

  public int getBookboardNo() {
    return bookboardNo;
  }

  public void setBookboardNo(int bookboardNo) {
    this.bookboardNo = bookboardNo;
  }

  public int getMemberNo() {
    return memberNo;
  }

  public void setMemberNo(int memberNo) {
    this.memberNo = memberNo;
  }



  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((author == null) ? 0 : author.hashCode());
    result = prime * result + ((bookTitle == null) ? 0 : bookTitle.hashCode());
    result = prime * result + bookboardNo;
    result = prime * result + ((content == null) ? 0 : content.hashCode());
    result = prime * result + expectedScore;
    result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
    result = prime * result + memberNo;
    result = prime * result + no;
    result = prime * result + ((publishedDate == null) ? 0 : publishedDate.hashCode());
    result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
    result = prime * result + score;
    result = prime * result + ((thumbnail == null) ? 0 : thumbnail.hashCode());
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
    Books other = (Books) obj;
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
    if (bookboardNo != other.bookboardNo)
      return false;
    if (content == null) {
      if (other.content != null)
        return false;
    } else if (!content.equals(other.content))
      return false;
    if (expectedScore != other.expectedScore)
      return false;
    if (isbn == null) {
      if (other.isbn != null)
        return false;
    } else if (!isbn.equals(other.isbn))
      return false;
    if (memberNo != other.memberNo)
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
    if (score != other.score)
      return false;
    if (thumbnail == null) {
      if (other.thumbnail != null)
        return false;
    } else if (!thumbnail.equals(other.thumbnail))
      return false;
    return true;
  }


}


