package mypack;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet(urlPatterns = {"/handleUpdate2"})
public class handleUpdate2 extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("uname") == null) {
            res.sendRedirect("/crud");
        } else {

            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            int id = Integer.parseInt(req.getParameter("empid"));
            out.println("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css\" integrity=\"sha512-8bHTC73gkZ7rZ7vpqUQThUDhqcNFyYi2xgDgPDHc+GXVGHXq+xPjynxIopALmOPqzo9JZj0k6OqqewdGO3EsrQ==\" crossorigin=\"anonymous\" />");

            String driver = "com.mysql.jdbc.Driver", dburl = "jdbc:mysql://localhost:3306/crud",
                    username = "root", password = "";
            Connection con = null;

            try {
                Class.forName(driver);
                con = DriverManager.getConnection(dburl, username, password);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from emp where empid =" + id);

                if (rs.next() == true) {
                    int empid = rs.getInt("empid");
                    String name = rs.getString("name");
                    double salary = rs.getDouble("salary");
                    out.println("<form class='ui form'method='post' action='handleUpdate3'/> ");
                    out.println("<input type='hidden' value='" + empid + "' name='empid'/> ");
                    out.println("<div class='field'><label>Update Name: </label><input type='text' name='name' value='" + name + "'/> </div>   ");
                    out.println("<div class='field'><label>Update Salary:</label> <input type='text' name='salary' value='" + salary + "'/> </div>  ");
                    out.println("<input class='ui button' type='submit' value='update record'/> ");
                }

            } catch (ClassNotFoundException | SQLException e) {
                out.println(e.toString());
            }
        }
    }
}
