/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypack;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.*;

/**
 *
 * @author neupane
 */
@WebServlet(urlPatterns = {"/handleCreate0"})
public class handleCreate0 extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("uname") == null) {
            res.sendRedirect("/crud");
        } else {

            PrintWriter out = res.getWriter();
            res.setContentType("text/html");
            out.println("<!DOCTYPE html>\n"
                    + "<!--\n"
                    + "To change this license header, choose License Headers in Project Properties.\n"
                    + "To change this template file, choose Tools | Templates\n"
                    + "and open the template in the editor.\n"
                    + "-->\n"
                    + "<html>\n"
                    + "    <head>\n"
                    + "        <title>Create a new record</title>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css\" integrity=\"sha512-8bHTC73gkZ7rZ7vpqUQThUDhqcNFyYi2xgDgPDHc+GXVGHXq+xPjynxIopALmOPqzo9JZj0k6OqqewdGO3EsrQ==\" crossorigin=\"anonymous\" />\n"
                    + "\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "        <div class=\"ui container\">\n"
                    + "            <div class=\"ui segment\">\n"
                    + "                <form class=\"ui form\" action=\"handleCreate\" method=\"post\">\n"
                    + "                    <h2>Create a new record</h2>\n"
                    + "                    <div class=\"field\">\n"
                    + "                        <input  type=\"text\" placeholder=\"Enter name\" name=\"name\"/><br>\n"
                    + "                    </div>\n"
                    + "                    <div class=\"field\">\n"
                    + "                        <input type=\"text\" placeholder=\"Enter salary\" name=\"salary\"/><br>\n"
                    + "                    </div>\n"
                    + "                    <div class=\"field\">\n"
                    + "                        <input class=\"ui button\"type=\"submit\" value=\"submit\"/>\n"
                    + "                    </div>\n"
                    + "                </form>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "    </body>\n"
                    + "</html>\n"
                    + "");
        }
    }
}
