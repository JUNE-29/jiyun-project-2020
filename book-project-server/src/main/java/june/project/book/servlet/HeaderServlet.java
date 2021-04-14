package june.project.book.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import june.project.book.domain.Member;

@WebServlet("/header")
public class HeaderServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>책책책</title>");
    out.println(
        "<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6' crossorigin='anonymous'>");

    out.println("<style>");
    out.println("body {");
    out.println("background-color: white;");
    out.println("}");
    out.println("div.container {");
    out.println("background-color: white;");
    // out.println(" border: 1px solid gray;");
    out.println(" width: 500px;");
    out.println("}");

    out.println(".navbar {");
    out.println("  background-color: #fcfcce;");
    out.println("}");

    out.println(".navbar .navbar-brand {");
    out.println("  color: #48a0ed;");
    out.println("}");

    out.println(".navbar .navbar-brand:hover,");
    out.println(".navbar .navbar-brand:focus {");
    out.println("  color: #acd7fe;");
    out.println("}");

    out.println(".navbar .navbar-nav .nav-link {");
    out.println("  color: #48a0ed;");
    out.println("  border-radius: .25rem;");
    out.println("  margin: 0 0.25em;");
    out.println("}");
    out.println(".navbar .navbar-nav .nav-link:not(.disabled):hover,");
    out.println(".navbar .navbar-nav .nav-link:not(.disabled):focus {");
    out.println("  color: #acd7fe;");
    out.println("}");
    out.println(".navbar .navbar-nav .nav-item.active .nav-link,");
    out.println(".navbar .navbar-nav .nav-item.active .nav-link:hover,");
    out.println(".navbar .navbar-nav .nav-item.active .nav-link:focus,");
    out.println(".navbar .navbar-nav .nav-item.show .nav-link,");
    out.println(".navbar .navbar-nav .nav-item.show .nav-link:hover,");
    out.println(".navbar .navbar-nav .nav-item.show .nav-link:focus {");
    out.println("  color: #acd7fe;");
    out.println("  background-color: #baf7ff;");
    out.println("}");

    out.println("</style>");

    out.println("</head>");
    out.println("<body>");
    out.println("<nav class='navbar navbar-expand-lg navbar-light'>");
    out.println("<a class='navbar-brand' href='../'>책책책</a>");
    out.println(
        "<button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbarNav' aria-controls='navbarNav' aria-expanded='false' aria-label='Toggle navigation'>");
    out.println("  <span class='navbar-toggler-icon'></span>");
    out.println("</button>");
    out.println("<div class='collapse navbar-collapse' id='navbarNav'>");
    out.println("  <ul class='navbar-nav'>");
    out.println("    <li class='nav-item'>");
    out.println("      <a class='nav-link' href='../book/list'>읽고 싶은 책</span></a>");
    out.println("    </li>");
    out.println("    <li class='nav-item'>");
    out.println("      <a class='nav-link' href='../bookmark/list'>간직 하고 싶은 말</a>");
    out.println("    </li>");
    out.println("    <li class='nav-item'>");
    out.println("      <a class='nav-link' href='../member/list'>회원</a>");
    out.println("    </li>");
    out.println("  </ul>");
    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser != null) {
      out.printf("<span class= 'navbar-text'>%s</span>\n", loginUser.getName());
      out.println("<a href='../auth/logout' class='btn btn-primary btn-sm'> 로그아웃 </a>");
    } else {
      out.println("<a href='../auth/login' class='btn btn-primary btn-sm'> 로그인 </a>");
    }
    out.println("</div>");
    out.println("</nav>");
    out.println("<div class='container'>");
  }
}
