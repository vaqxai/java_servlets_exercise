package zad1;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetCarCategoriesServlet extends HttpServlet {
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        if (TestServlet.dbConnection == null) {
            TestServlet.connectDb();
        }

        if (TestServlet.carTypes.size() == 0) {
            TestServlet.getCarTypes();
        }

        response.setCharacterEncoding("UTF-8");
        
        try{
            response.getWriter().write("<select id='car_type' name='Car type'>");
            for(String category : TestServlet.carTypes){
                response.getWriter().write("<option value='" + category + "'>" + category + "</option>");
            }
            response.getWriter().write("</select>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
