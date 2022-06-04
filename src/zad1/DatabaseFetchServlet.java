package zad1;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DatabaseFetchServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response){

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try {

            if(TestServlet.dbConnection == null){
                TestServlet.connectDb();
            }

            // alternatywna forma: servlet bazy danych jest uruchamiany przez dispatch.include
            // ale nie zwraca żadnego html'a, lecz umieszcza liste w statycznym polu, dostepnym z tego servleta
            // ktora czytamy i budujemy tabelke
            // (w tym podejsciu, to tamten servlet buduje tabelke, a my ja jedynie wyswietlamy)
            // alternatywne podejscie 2: "Restowe", nasz servlet wysyla POST'a do innego servleta,
            // a tamten odpowiada nam JSONem, ktorego parsujemy


            response.getWriter().write("<html><head><link rel='stylesheet' href='css/styles.css'></head><body>");
            response.getWriter().write("<h1>Car List</h1>");

            String carType = request.getParameter("Car type");

            response.getWriter().write("<p> Listing cars of chosen type: "+ carType + "</p>");
            response.getWriter().write(TestServlet.getCarsTable(carType));

            response.getWriter().write("<a href='/tpo5'>Return</a>");
            response.getWriter().write("</body></html>");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    
}
