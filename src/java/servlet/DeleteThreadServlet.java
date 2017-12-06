/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import utils.DBUtils;
import utils.MyUtils;
 
@WebServlet(urlPatterns = { "/deleteThread" })
public class DeleteThreadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public DeleteThreadServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        HttpSession session = request.getSession();
        User userInSession = MyUtils.getLoginedUser(session);
        
        boolean hasError = false;
        String errorString = null;
        int threadId = Integer.parseInt (request.getParameter("threadId"));
        
        if (userInSession == null) {
            hasError = true;
            errorString = "Please re-login to continue using this service";
        }
        if (hasError) {
            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
 
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
 
            dispatcher.forward(request, response);
        }
        
        try {
            DBUtils.deleteThread(conn, threadId);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
            PrintWriter out = response.getWriter();
            out.println(errorString);
            
        } 
         
        // If has an error, redirecte to the error page.
        if (errorString != null) {
            // Store the information in the request attribute, before forward to views.
            request.setAttribute("errorString", errorString);
            // 
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/myForum");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.        
        else {
            response.sendRedirect(request.getContextPath() + "/myForum");
        }
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}