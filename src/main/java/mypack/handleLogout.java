package mypack;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(urlPatterns={"/handleLogout"})
public class handleLogout extends HttpServlet{
    @Override 
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
        PrintWriter out = res.getWriter();
        HttpSession session = req.getSession();
        session.invalidate();
        res.sendRedirect("/crud");
    }
}
