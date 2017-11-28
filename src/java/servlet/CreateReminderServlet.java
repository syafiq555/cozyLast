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
import javax.servlet.http.HttpSession;

import beans.Medication;
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
@WebServlet(name = "CreateReminderServlet", urlPatterns = {"/create"})
public class CreateReminderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public CreateReminderServlet() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/create.jsp");
 
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String medicationName = request.getParameter("medicationName");
        int medicationType = Integer.parseInt(request.getParameter("medicationType"));
        String time = request.getParameter("time");
        HttpSession session = request.getSession();
        User userInSession = MyUtils.getLoginedUser(session);
        
        Medication medication;
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
                medication = new Medication(medicationType, medicationName, username, time);
                
                // create the user in the DB.
                DBUtils.createMedication(conn, medication);
                
            } catch (SQLException e) {
                PrintWriter out=response.getWriter();
                out.println(e);
            }
            RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/view.jsp");

            dispatcher.forward(request, response);
        }
    }
}
