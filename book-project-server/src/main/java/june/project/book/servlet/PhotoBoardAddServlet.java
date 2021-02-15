package june.project.book.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import june.project.book.dao.BookmarkDao;
import june.project.book.dao.PhotoBoardDao;
import june.project.book.dao.PhotoFileDao;
import june.project.book.domain.Bookmark;
import june.project.book.domain.PhotoBoard;
import june.project.book.domain.PhotoFile;
import june.project.sql.PlatformTransactionManager;
import june.project.sql.TransactionCallback;
import june.project.sql.TransactionTemplate;
import june.project.util.Prompt;

public class PhotoBoardAddServlet implements Servlet {

  TransactionTemplate transactionTemplate;
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
  BookmarkDao bookmarkDao;


  public PhotoBoardAddServlet(PlatformTransactionManager txManager, //
      PhotoBoardDao photoBoardDao, //
      BookmarkDao bookmarkDao, PhotoFileDao photoFileDao) {

    // 트랜잭션 관리자를 사용하여
    // 트랜잭션을 처리할 도우미 객체를 준비한다.
    // 따라서 트랜잭션 관리자는 TransactionTemplate이 사용할 것이기 때문에
    // 생성자에 넘겨준다.
    this.transactionTemplate = new TransactionTemplate(txManager);

    this.photoBoardDao = photoBoardDao;
    this.bookmarkDao = bookmarkDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(Prompt.getString(in, out, "제목? "));

    int bookmarkNo = Prompt.getInt(in, out, "북마크 번호? ");

    Bookmark bookmark = bookmarkDao.findByNo(bookmarkNo);
    if (bookmark == null) {
      out.println("북마크 번호가 유효하지 않습니다.");
      return;
    }

    photoBoard.setBookmark(bookmark);

    List<PhotoFile> photoFiles = inputPhotoFiles(in, out);
    photoBoard.setFiles(photoFiles);

    // 도우미 객체를 이용하여 트랜잭션 작업을 처리해보자.
    // => 트랜잭션으로 묶어서 처리할 작업은 TransactionCallback 규칙에 따라
    // 객체를 만들어 파라미터로 넘겨주면 된다.

    transactionTemplate.execute(new TransactionCallback() {

      @Override
      public Object doInTransaction() throws Exception {
        // 이 메서드는 TransactionTemplate의 execute()에서
        // 트랜잭션 시작을 준비한 후에 호출할 것이다.
        // 따라서 이 메서드 안에는 트랜잭션으로 한 단위 묶어,
        // 실행할 코드를 두면 된다.

        if (photoBoardDao.insert(photoBoard) == 0) {
          throw new Exception("사진 게시글 등록에 실패했습니다.");
        }

        photoFileDao.insert(photoBoard);
        out.println("새 게시글을 등록했습니다.");

        return null;
      }
    });
  }


  private List<PhotoFile> inputPhotoFiles(Scanner in, PrintStream out) {

    // 정상적으로 등록 되었으면 첨부 파일을 입력 받는다.
    out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
    out.println("파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.");

    ArrayList<PhotoFile> photoFiles = new ArrayList<>();

    while (true) {
      String filepath = Prompt.getString(in, out, "사진 파일? ");

      if (filepath.length() == 0) {
        if (photoFiles.size() > 0) {
          break;
        } else {
          out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
          continue;
        }
      }
      photoFiles.add(new PhotoFile().setFilePath(filepath));
    }
    return photoFiles;
  }
}
