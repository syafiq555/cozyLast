/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.EmailUtility;
import java.sql.SQLException;

import utils.DBUtils;
import utils.MyUtils;

/**
 *
 * @author User
 */
@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/forgotPassword"})
public class ForgotPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String host;
    private String port;
    private String user;
    private String pass;
 
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
    
    public ForgotPasswordServlet(){
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/forgotPassword.jsp");
 
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String subject = "Cozy :Reset Password";
        String password = null;
        String username = null;
        Connection conn = MyUtils.getStoredConnection(request);
        try {            
            password = DBUtils.findPassword(conn, email);
            username = DBUtils.findUsername(conn, email);
        }catch(SQLException e){
            PrintWriter out = response.getWriter();
            out.println(e.getMessage());
        }
        String content = "Your password and username for COZY : MedSafe\n"
                + "username = "+username+"\n"
                + "password = "+password+"\n"
                + "Next time remember your password and be safe with the help of our app";
 
        String resultMessage = "";
 
        try {
            EmailUtility.sendEmail(host, port, user, pass, email, subject,content);
            resultMessage = "The e-mail was sent successfully";
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher("/WEB-INF/views/Result.jsp").forward(
                    request, response);
        }
    }
}
