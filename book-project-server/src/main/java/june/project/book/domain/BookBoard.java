package june.project.book.domain;

import java.io.Serializable;

public class BookBoard implements Serializable {

  private static final long serialVersionUID = 1L;

  private int no; // 번호
  private String title; // 책 게시판 제목
  private int count; // 수량
  private int bookNo;
  private int memberNo;

  Books books;
  Member member;

  @Override
  public String toString() {
    return "BookBoard [no=" + no + ", title=" + title + ", count=" + count + ", bookNo=" + bookNo
        + ", memberNo=" + memberNo + ", books=" + books + ", member=" + member + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + bookNo;
    result = prime * result + ((books == null) ? 0 : books.hashCode());
    result = prime * result + count;
    result = prime * result + ((member == null) ? 0 : member.hashCode());
    result = prime * result + memberNo;
    result = prime * result + no;
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
    BookBoard other = (BookBoard) obj;
    if (bookNo != other.bookNo)
      return false;
    if (books == null) {
      if (other.books != null)
        return false;
    } else if (!books.equals(other.books))
      return false;
    if (count != other.count)
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
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    return true;
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

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public int getBookNo() {
    return bookNo;
  }

  public void setBookNo(int bookNo) {
    this.bookNo = bookNo;
  }

  public int getMemberNo() {
    return memberNo;
  }

  public void setMemberNo(int memberNo) {
    this.memberNo = memberNo;
  }

  public Books getBooks() {
    return books;
  }

  public void setBooks(Books books) {
    this.books = books;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }


}
