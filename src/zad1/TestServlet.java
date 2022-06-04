package zad1;

import jakarta.servlet.http.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class TestServlet extends HttpServlet {

    public static Connection dbConnection = null;
    public static Set<String> carTypes = new HashSet<>();

    public static String connectDb(){
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:zad1.db";
            conn = DriverManager.getConnection(url);
            dbConnection = conn;
            return "Connected to the database";
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
            return e.getMessage();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return e.getMessage();
        }
    }

    public static void getCarTypes(){
        if(dbConnection == null){ connectDb(); }
        try {
            String sql = "SELECT * FROM cars";
            java.sql.Statement stmt = dbConnection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                carTypes.add(rs.getString("category"));
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void closeDbConn(){
        try {
            if (dbConnection != null) {
                dbConnection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getCarsTable(String type){
        if (dbConnection == null) {
            connectDb();
        }

        String result = "<table><tr><th>ID</th><th>Name</th><th>Year</th><th>Fuel Consumption (l/100km)</th></tr>";

        try {
            String sql = "SELECT * FROM cars WHERE category = '" + type + "'";
            java.sql.Statement stmt = dbConnection.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                result += "<tr><td>" + rs.getString("id") + "</td><td>" + rs.getString("name") + "</td><td>" + rs.getString("prod_year") + "</td><td>" + rs.getString("fuel_usage") + "</td></tr>";
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        result += "</table>";

        return result;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write("<html><head><title>Hello Worlds!</title><link rel='stylesheet' href='css/styles.css'/></head><body>");
            response.getWriter().write("<body>");
            response.getWriter().write("<h1>Hello Worlds!</h1>");
            response.getWriter().write("<a href='/tpo5'>Return</a>");

            // db

            String connectionResult = "";

            if (dbConnection == null) {
                connectionResult = connectDb();
            } else {
                connectionResult = "Already connected to the database";
            }

            getCarTypes();

            response.getWriter().write("<h2>DB</h2>");
            response.getWriter().write("<ul>");
            response.getWriter().write("<li>Connection status: " + connectionResult + "</li>");
            response.getWriter().write("<li>Car types: " + carTypes.toString() + "</li>");
            response.getWriter().write("</ul>");

            // display table

            response.getWriter().write("<h2>Table</h2>");
            response.getWriter().write("<table><tr><th>ID</th><th>Name</th><th>Category</th><th>Production Year</th><th>Fuel Consumption (l/100km)</th></tr>");
            
            // get data from db

            if(dbConnection != null) {
                try {
                    java.sql.Statement stmt = dbConnection.createStatement();
                    java.sql.ResultSet rs = stmt.executeQuery("SELECT * FROM cars");
                    while (rs.next()) {
                        response.getWriter().write("<tr><td>" + rs.getString("id") + "</td><td>" + rs.getString("name") + "</td><td>" + rs.getString("category") + "</td><td>" + rs.getString("prod_year") + "</td><td>" + rs.getString("fuel_usage") + "</td></tr>");
                    }
                    rs.close();
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

            response.getWriter().write("</table>");
            

            // end body

            response.getWriter().write("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
