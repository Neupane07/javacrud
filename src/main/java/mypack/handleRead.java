package mypack;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet(urlPatterns = {"/handleRead"})
public class handleRead extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession();
        if (session.getAttribute("uname") == null) {
            res.sendRedirect("/crud");
        } else {
            out.println("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css\" integrity=\"sha512-8bHTC73gkZ7rZ7vpqUQThUDhqcNFyYi2xgDgPDHc+GXVGHXq+xPjynxIopALmOPqzo9JZj0k6OqqewdGO3EsrQ==\" crossorigin=\"anonymous\" />");

            String driver = "com.mysql.jdbc.Driver",
                    username = "root", password = "",
                    dburl = "jdbc:mysql://localhost:3306/crud";
            Connection con = null;
            out.println("<div class='ui segment'>");
            String table = "<table border='2px solid' class='ui celled table'>"
                    + "<tr><th>Emp Id</th><th>Name</th><th>Salary</th></tr>";
            try {
                Class.forName(driver);
                con = DriverManager.getConnection(dburl, username, password);

                Statement stmnt = con.createStatement();
                ResultSet rs = stmnt.executeQuery("Select * from emp");
                while (rs.next()) {
                    table += "<tr>";
                    table += "<td>";
                    table += rs.getInt("empid");
                    table += "</td>";

                    table += "<td>";
                    table += rs.getString("name");
                    table += "</td>";

                    table += "<td>";
                    table += rs.getDouble("salary");
                    table += "</td>";

                    table += "</tr>";
                }
            } catch (ClassNotFoundException | SQLException e) {
                out.println(e.toString());
            }
            table += "</table></div>";
            out.println(table);

        }
    }

}
