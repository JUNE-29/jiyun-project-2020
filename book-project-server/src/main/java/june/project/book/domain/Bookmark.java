package june.project.book.domain;

import java.io.Serializable;
import java.sql.Date;

public class Bookmark implements Serializable {

  private static final long serialVersionUID = 1L;

  private int no;
  private String title;
  private String content;
  private String photoFilePath;
  private Date date;
  private int ReadBookNo;
  private int BasketBookNo;
  private int memberNo;

  Member member;
  Bookcase bookcase;
  BookBasket bookBasket;

  @Override
  public String toString() {
    return "Bookmark [no=" + no + ", title=" + title + ", content=" + content + ", photoFilePath="
        + photoFilePath + ", date=" + date + ", ReadBookNo=" + ReadBookNo + ", BasketBookNo="
        + BasketBookNo + ", memberNo=" + memberNo + ", member=" + member + ", bookcase=" + bookcase
        + ", bookBasket=" + bookBasket + "]";
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

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getPhotoFilePath() {
    return photoFilePath;
  }

  public void setPhotoFilePath(String photoFilePath) {
    this.photoFilePath = photoFilePath;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public int getReadBookNo() {
    return ReadBookNo;
  }

  public void setReadBookNo(int readBookNo) {
    ReadBookNo = readBookNo;
  }

  public int getBasketBookNo() {
    return BasketBookNo;
  }

  public void setBasketBookNo(int basketBookNo) {
    BasketBookNo = basketBookNo;
  }

  public int getMemberNo() {
    return memberNo;
  }

  public void setMemberNo(int memberNo) {
    this.memberNo = memberNo;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public Bookcase getBookcase() {
    return bookcase;
  }

  public void setBookcase(Bookcase bookcase) {
    this.bookcase = bookcase;
  }

  public BookBasket getBookBasket() {
    return bookBasket;
  }

  public void setBookBasket(BookBasket bookBasket) {
    this.bookBasket = bookBasket;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + BasketBookNo;
    result = prime * result + ReadBookNo;
    result = prime * result + ((bookBasket == null) ? 0 : bookBasket.hashCode());
    result = prime * result + ((bookcase == null) ? 0 : bookcase.hashCode());
    result = prime * result + ((content == null) ? 0 : content.hashCode());
    result = prime * result + ((date == null) ? 0 : date.hashCode());
    result = prime * result + ((member == null) ? 0 : member.hashCode());
    result = prime * result + memberNo;
    result = prime * result + no;
    result = prime * result + ((photoFilePath == null) ? 0 : photoFilePath.hashCode());
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
    Bookmark other = (Bookmark) obj;
    if (BasketBookNo != other.BasketBookNo)
      return false;
    if (ReadBookNo != other.ReadBookNo)
      return false;
    if (bookBasket == null) {
      if (other.bookBasket != null)
        return false;
    } else if (!bookBasket.equals(other.bookBasket))
      return false;
    if (bookcase == null) {
      if (other.bookcase != null)
        return false;
    } else if (!bookcase.equals(other.bookcase))
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
    if (member == null) {
      if (other.member != null)
        return false;
    } else if (!member.equals(other.member))
      return false;
    if (memberNo != other.memberNo)
      return false;
    if (no != other.no)
      return false;
    if (photoFilePath == null) {
      if (other.photoFilePath != null)
        return false;
    } else if (!photoFilePath.equals(other.photoFilePath))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    return true;
  }
}
