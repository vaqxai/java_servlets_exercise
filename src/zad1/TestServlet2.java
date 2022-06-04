package zad1;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TestServlet2 extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write("<html><body>");
            response.getWriter().write("<h1>Hello Worlds2!</h1>");
            response.getWriter().write("<a href='/tpo5'>Return</a>");
            response.getWriter().write("</br><a> Status of the connection: " + (TestServlet.dbConnection != null) + "</a>");
            response.getWriter().write("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
