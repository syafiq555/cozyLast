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
@WebServlet(name = "ForumSerlvet", urlPatterns = {"/forum"})
public class ForumServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public ForumServlet() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/forum.jsp");
 
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        int birthYear = Integer.parseInt(request.getParameter("birthYear"));
        String password = null;
        
        User user = null;
        boolean hasError = false;
        String errorString = null;
        
        if (!(password1 == null ? password2 == null : password1.equals(password2))) {
            hasError = true;
            errorString = "Incorrect passwords combinations";
        } else {
            password = password1;
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // Find the user in the DB.
                user = DBUtils.findUser(conn, username);
 
                if (user != null) {
                    hasError = true;
                    errorString = "User Name has been used!";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        if (hasError) {
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
 
            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
 
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/register.jsp");
 
            dispatcher.forward(request, response);
        }
        // If no error
        // Store user information in Session
        // And redirect to userInfo page.
        else {
            // Redirect to userInfo page.
            try {
                password = password1;
                Connection conn = MyUtils.getStoredConnection(request);
                User newUser = new User(email, password, username, gender, birthYear);
                
                // create the user in the DB.
                DBUtils.createUser(conn, newUser);
                
            } catch (SQLException e) {
                PrintWriter out=response.getWriter();
                out.println(e);
            }
            RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");

            dispatcher.forward(request, response);
        }
    }
}
