package zad1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CarRequestServlet extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try {

            response.getWriter().write("<html><body>");
            response.getWriter().write("<h1>Car Request Form</h1>");

            RequestDispatcher rd = request.getRequestDispatcher("/get_categories");

            response.getWriter().write("<form action='/tpo5/fetch_cardata' method='post'>");

            rd.include(request, response);

            response.getWriter().write("<input type='submit' value='Fetch data'>");
            response.getWriter().write("</form>");

            response.getWriter().write("<a href='/tpo5'>Return</a>");
            response.getWriter().write("</body></html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
