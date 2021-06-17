package mypack;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet(urlPatterns = {"/handleDelete2"})
public class handleDelete2 extends HttpServlet {

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
            String driver = "com.mysql.jdbc.Driver", dburl = "jdbc:mysql://localhost:3306/crud",
                    username = "root", password = "";
            Connection con = null;
            String query = "delete from emp where empid='" + id + "' ";
            try {
                con = DriverManager.getConnection(dburl, username, password);
                Statement stmnt = con.createStatement();
                stmnt.executeUpdate(query);
                out.println("record has been deleted!");
                con.close();

            } catch (Exception e) {
                out.println(e.toString());
            }

        }
    }
}
