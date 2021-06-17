package mypack;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet(urlPatterns = {"/handleUpdate3"})
public class handleUpdate3 extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("uname") == null) {
            res.sendRedirect("/crud");
        } else {
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            int empid = Integer.parseInt(req.getParameter("empid"));
            String name = req.getParameter("name");
            double salary = Double.parseDouble(req.getParameter("salary"));
//        out.println(empid);

            String driver = "com.mysql.jdbc.Driver", username = "root",
                    dburl = "jdbc:mysql://localhost:3306/crud", password = "";
            Connection con = null;
            String query = "update emp set name='" + name + "',salary='" + salary + "' where empid='" + empid + "'  ";
//        String query = "udpate emp set name='test' where id=1";

            out.println("Query performed : " + query + "<br>");
            try {
                Class.forName(driver);
                con = DriverManager.getConnection(dburl, username, password);
                Statement stmnt = con.createStatement();
                stmnt.executeUpdate(query);
                out.println("Record Updated Successfully!");

            } catch (Exception e) {
                out.println(e.toString());
            }

        }
    }
}
