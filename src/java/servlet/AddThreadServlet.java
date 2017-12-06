/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import beans.Thread;
import beans.User;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import utils.DBUtils;
import utils.MyUtils;
/**
 *
 * @author User
 */
@WebServlet(name = "AddThreadServlet", urlPatterns = {"/addThread"})
public class AddThreadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public AddThreadServlet() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String threadName = request.getParameter("threadName");
        String threadDetails = request.getParameter("threadDetails");
        
        HttpSession session = request.getSession();
        User userInSession = MyUtils.getLoginedUser(session);
        
        Thread thread;
        boolean hasError = false;
        String errorString = null;
        
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
        // If no error
        // Store user information in Session
        // And redirect to userInfo page.
        else {
            // Redirect to userInfo page.
            try {
                Connection conn = MyUtils.getStoredConnection(request);
                String username = userInSession.getUsername();
                thread = new Thread(threadName, threadDetails, username);
                
                // create the user in the DB.
                DBUtils.createThread(conn, thread);
                
            } catch (SQLException e) {
                hasError = true;
                errorString = e.getMessage();
                
                request.setAttribute("errorString", errorString);
            }
            RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/forum");

            dispatcher.forward(request, response);
        }
    }
}
