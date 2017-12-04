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
import beans.Medication;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;
import utils.DBUtils;
import utils.MyUtils;
 
@WebServlet(urlPatterns = { "/editReminder" })
public class EditReminderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public EditReminderServlet() {
        super();
    }
 
    // Show product edit page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        int medicationId = Integer.parseInt(request.getParameter("medicationId"));
 
        Medication medication = null;
 
        String errorString = null;
 
        try {
            medication = DBUtils.findMedication(conn, medicationId);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        // If no error.
        // The product does not exist to edit.
        // Redirect to productList page.
        if (errorString != null && medication == null) {
            response.sendRedirect(request.getServletPath() + "/details?medicationId="+medicationId);
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("medication", medication);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editReminderDetails.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // After the user modifies the product information, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String medicationName = request.getParameter("medicationName");
        int medicationType = Integer.parseInt(request.getParameter("medicationType"));
        String time = request.getParameter("time");
        int medicationId = 0;
        try{
            medicationId = Integer.parseInt(request.getParameter("medicationId"));
        }catch(Exception e){
           out.println(e); 
        
        }
        HttpSession session = request.getSession();
        User userInSession = MyUtils.getLoginedUser(session);
        
        Medication medication = null;
        boolean hasError = false;
        String errorString = null;
        String date1 = request.getParameter("date_start");
        String date2 = request.getParameter("date_end");
        Date date_start = null;
        Date date_end = null;
        
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        try{
            date_start = date.parse(date1);
            date_end = date.parse(date2);
        }catch(ParseException e){
            hasError = true;
            errorString = e.getMessage();
        }
        
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
                medication = new Medication(medicationId, medicationType, medicationName, username, time, date_start, date_end);
                
                // create the user in the DB.
                DBUtils.updateMedication(conn, medication);
                
            } catch (SQLException e) {
                out.println(e);
            }
            RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/homePage");

            dispatcher.forward(request, response);
        }
    }
}