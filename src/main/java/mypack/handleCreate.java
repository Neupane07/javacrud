package mypack;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet(urlPatterns = {"/handleCreate"})
public class handleCreate extends HttpServlet {

    /**
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css\" integrity=\"sha512-8bHTC73gkZ7rZ7vpqUQThUDhqcNFyYi2xgDgPDHc+GXVGHXq+xPjynxIopALmOPqzo9JZj0k6OqqewdGO3EsrQ==\" crossorigin=\"anonymous\" />");

        HttpSession session = req.getSession();
        if (session.getAttribute("uname") == null) {
            res.sendRedirect("/crud");
        } else {
            String name = req.getParameter("name");
            double salary = Double.parseDouble(req.getParameter("salary"));
            String driver = "com.mysql.jdbc.Driver", username = "root", password = "",
                    dburl = "jdbc:mysql://localhost:3306/crud";
            String query = "insert into emp(name, salary) values('" + name + "','" + salary + "');";
            Connection con = null;

            try {
                Class.forName(driver);
                con = DriverManager.getConnection(dburl, username, password);
                Statement stmnt = con.createStatement();
                stmnt.executeUpdate(query);
                out.println("Data inserted");
                out.println("<a href='home'>Back to home</a>");

            } catch (ClassNotFoundException | SQLException e) {
                out.println(e.toString());
            }
        }
    }
}
