package june.project.book;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import june.project.book.context.ApplicationContextListener;
import june.project.book.domain.BookBasket;
import june.project.book.domain.BookBoard;
import june.project.book.domain.Bookmark;
import june.project.book.domain.Member;

public class DataLoaderListener implements ApplicationContextListener {

  List<BookBoard> bookBoardList = new ArrayList<>();
  List<Bookmark> bookmark = new ArrayList<>();
  List<BookBasket> bookBasketList = new ArrayList<>();
  List<Member> memberList = new LinkedList<>();

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다.");
    loadMemberData();
    loadBookBoardData();
    loadBookBasketData();
    loadBookmarkData();

    context.put("memberList", memberList);
    context.put("bookBasketList", bookBasketList);
    context.put("bookmarkList", bookmark);
    context.put("bookBoardList", bookBoardList);
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("데이터를 저장합니다.");
    saveMemberData();
    saveBookBoardData();
    saveBookBasketData();
    saveBookmarkData();
  }



  @SuppressWarnings("unchecked")
  private void loadMemberData() {
    File file = new File("./member.ser2");

    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      memberList = (List<Member>) in.readObject();
      System.out.printf("총 %d 개의 회원 데이터를 로딩했습니다.\n", memberList.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private void saveMemberData() {
    File file = new File("./member.ser2");

    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(memberList);
      System.out.printf("총 %d 개의 회원 데이터를 저장했습니다.\n", memberList.size());

    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! -" + e.getMessage());
    }
  }


  @SuppressWarnings("unchecked")
  private void loadBookBoardData() {
    File file = new File("./bookBoard.ser2");

    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      bookBoardList = (List<BookBoard>) in.readObject();

      System.out.printf("총 %d 개의 도서 데이터를 로딩했습니다.\n", bookBoardList.size());

    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! - " + e.getMessage());
    }
  }

  private void saveBookBoardData() {
    File file = new File("./bookBoard.ser2");

    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(bookBoardList);

      System.out.printf("총 %d 개의 도서 데이터를 저장했습니다.\n", bookBoardList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }


  @SuppressWarnings("unchecked")
  private void loadBookBasketData() {
    File file = new File("./bookBasket.ser2");

    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      bookBasketList = (List<BookBasket>) in.readObject();

      System.out.printf("총 %d 개의 즐겨찾는 도서의 데이터를 로딩했습니다.\n", bookBasketList.size());
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! -" + e.getMessage());
    }
  }

  private void saveBookBasketData() {
    File file = new File("./bookBasket.ser2");

    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(bookBasketList);
      System.out.printf("총 %d 개의 즐겨찾는 도서의 데이터를 저장했습니다.\n", bookBasketList.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! - " + e.getMessage());
    }
  }


  @SuppressWarnings("unchecked")
  private void loadBookmarkData() {
    File file = new File("./bookmark.ser2");

    try (ObjectInputStream in =
        new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      bookmark = (List<Bookmark>) in.readObject();

      System.out.printf("총 %d 개의 북마크 데이터를 로딩했습니다.\n", bookmark.size());
    } catch (Exception e) {
      System.out.println("파일 읽기 중 오류 발생! -" + e.getMessage());
    }
  }

  private void saveBookmarkData() {
    File file = new File("./bookmark.ser2");
    try (ObjectOutputStream out =
        new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      out.writeObject(bookmark);

      System.out.printf("총 %d 개의 북마크 데이터를 저장했습니다.\n", bookmark.size());
    } catch (IOException e) {
      System.out.println("파일 쓰기 중 오류 발생! -" + e.getMessage());
    }
  }

}
