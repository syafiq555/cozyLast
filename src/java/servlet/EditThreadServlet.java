/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import beans.User;
import beans.Thread;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;
import utils.DBUtils;
import utils.MyUtils;
 
@WebServlet(urlPatterns = { "/editThread" })
public class EditThreadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public EditThreadServlet() {
        super();
    }
 
    // Show product edit page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        int threadId = Integer.parseInt(request.getParameter("threadId"));
 
        Thread thread = null;
 
        String errorString = null;
 
        try {
            thread = DBUtils.findThread(conn, threadId);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        // If no error.
        // The product does not exist to edit.
        // Redirect to productList page.
        if (errorString != null && thread == null) {
            response.sendRedirect(request.getServletPath() + "/threadDetails?threadId="+threadId);
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("thread", thread);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editThread.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // After the user modifies the product information, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String threadName = request.getParameter("threadName");
        String threadDetails = request.getParameter("threadDetails");
        int threadId = 0;
        try{
            threadId = Integer.parseInt(request.getParameter("threadId"));
        }catch(Exception e){
           out.println(e); 
        
        }
        HttpSession session = request.getSession();
        User userInSession = MyUtils.getLoginedUser(session);
        
        Thread thread = null;
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
                thread = new Thread(threadId, threadName, threadDetails, username);
                
                // create the user in the DB.
                DBUtils.updateThread(conn, thread);
                
            } catch (SQLException e) {
                out.println(e);
            }
            RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/myForum");

            dispatcher.forward(request, response);
        }
    }
}