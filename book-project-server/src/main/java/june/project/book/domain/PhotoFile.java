package june.project.book.domain;

import java.io.Serializable;

public class PhotoFile implements Serializable {

  private static final long serialVersionUID = 1L;

  int no;
  String filePath;
  int boardNo;

  public PhotoFile() {}

  public PhotoFile(String filePath, int boardNo) {
    this.filePath = filePath;
    this.boardNo = boardNo;
  }

  public PhotoFile(int no, String filePath, int boardNo) {
    this(filePath, boardNo);
    this.no = no;
  }

  @Override
  public String toString() {
    return "PhotoFile [no=" + no + ", filepath=" + filePath + ", boardNo=" + boardNo + "]";
  }

  public int getNo() {
    return no;
  }

  public PhotoFile setNo(int no) {
    this.no = no;
    return this;
  }

  public String getFilePath() {
    return filePath;
  }

  public PhotoFile setFilePath(String filePath) {
    this.filePath = filePath;
    return this;
  }

  public int getBoardNo() {
    return boardNo;
  }

  public PhotoFile setBoardNo(int boardNo) {
    this.boardNo = boardNo;
    return this;
  }
}
