package mypack;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;
import javax.servlet.http.HttpSession.*;

@WebServlet(urlPatterns={"/authenticate"})
class authenticate extends HttpServlet{
    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        String driver = "com.mysql.jdbc.Driver",dburl="jdbc:mysql://localhost:3306/crud"
                ,dbusername = "root", dbpassword = "";
        String query = "select * from usermaster where username = '"+username+"'and password = '"+password+"' ;";
        boolean found = false;
        
        try{
            Class.forName(driver);
            Connection con = DriverManager.getConnection(dburl, dbusername, dbpassword);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                found = true;
            }
            con.close();
            
        }catch(ClassNotFoundException | SQLException e){
            out.println(e.toString());
        }
        if(found){
            HttpSession session = req.getSession();
            session.setAttribute("uname",username);
            res.sendRedirect("home");
        }else{
            out.println("incorrect username or password");
        }
    }
}