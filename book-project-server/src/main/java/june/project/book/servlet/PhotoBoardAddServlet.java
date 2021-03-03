package june.project.book.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import june.project.book.domain.Bookmark;
import june.project.book.domain.PhotoBoard;
import june.project.book.domain.PhotoFile;
import june.project.book.service.BookmarkService;
import june.project.book.service.PhotoBoardService;
import june.project.util.Component;
import june.project.util.Prompt;
import june.project.util.RequestMapping;

@Component
public class PhotoBoardAddServlet {

  PhotoBoardService photoBoardService;
  BookmarkService bookmarkService;


  public PhotoBoardAddServlet(PhotoBoardService photoBoardService,
      BookmarkService bookmarkService) {
    this.photoBoardService = photoBoardService;
    this.bookmarkService = bookmarkService;
  }

  @RequestMapping("/photoboard/add")
  public void service(Scanner in, PrintStream out) throws Exception {

    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(Prompt.getString(in, out, "제목? "));

    int bookmarkNo = Prompt.getInt(in, out, "북마크 번호? ");

    Bookmark bookmark = bookmarkService.get(bookmarkNo);
    if (bookmark == null) {
      out.println("북마크 번호가 유효하지 않습니다.");
      return;
    }

    photoBoard.setBookmark(bookmark);

    List<PhotoFile> photoFiles = inputPhotoFiles(in, out);
    photoBoard.setFiles(photoFiles);

    photoBoardService.add(photoBoard);
    out.println("새 사진 게시글을 등록했습니다.");

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
