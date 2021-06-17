package mypack;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.*;
import java.io.*;
import java.sql.*;

@WebServlet(urlPatterns = {"/handleDelete"})
public class handleDelete extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("uname") == null) {
            res.sendRedirect("/crud");
        } else {
            PrintWriter out = res.getWriter();
            out.println("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css\" integrity=\"sha512-8bHTC73gkZ7rZ7vpqUQThUDhqcNFyYi2xgDgPDHc+GXVGHXq+xPjynxIopALmOPqzo9JZj0k6OqqewdGO3EsrQ==\" crossorigin=\"anonymous\" />");
            res.setContentType("text/html");
            String driver = "com.mysql.jdbc.Driver", username = "root",
                    password = "", dburl = "jdbc:mysql://localhost:3306/crud";
            Connection con = null;
            out.println("<form action='handleDelete2' method='post'>");

            try {
                Class.forName(driver);
                con = DriverManager.getConnection(dburl, username, password);
                Statement stmnt = con.createStatement();
                ResultSet rs = stmnt.executeQuery("Select * from emp;");
                out.println("<div class='ui form'> <div class='field'>");
                String str = "<select  class=\"ui dropdown\" name='empid'> ";

                while (rs.next()) {
                    String name = rs.getString("name");
                    int empid = rs.getInt("empid");
                    str += "<option value='" + empid + "'>" + name + "</option>";
                }
                str += "</select><br>";
                out.println(str);
                out.println("<input class='ui button'type='submit' value='Delete selected record'>");
                out.println("</div></div></form>");
            } catch (ClassNotFoundException | SQLException e) {
                out.println(e.toString());
            }
        }
    }
}
