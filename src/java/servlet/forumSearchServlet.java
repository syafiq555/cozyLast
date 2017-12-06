package servlet;

import beans.Post;
import beans.Thread;
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
import java.util.ArrayList;
import java.util.List;
 
@WebServlet(urlPatterns = { "/forumSearch"})
public class forumSearchServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
 
   public forumSearchServlet() {
       super();
   }
 
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
 
        HttpSession session = request.getSession();
        User userInSession = MyUtils.getLoginedUser(session);
        boolean hasError = false;
        String errorString = null;
        List<Thread> list = null;
        String string = request.getParameter("string");
        
        if (userInSession == null) {
            hasError = true;
            errorString = "Please re-login to continue using this service";
        }
        
        if(string == null){
            hasError = true;
            errorString = "You search for an empty string!";
            
            request.setAttribute("errorString", errorString);
 
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/forum.jsp");
 
            dispatcher.forward(request, response);
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
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                list = DBUtils.searchThread(conn, string);
                if(list.isEmpty()){
                    hasError = true;
                    errorString = "There's no thread named "+string+" that you are looking for";
                    
                    request.setAttribute("errorString", errorString);
                    RequestDispatcher dispatcher //
                        = this.getServletContext().getRequestDispatcher("/WEB-INF/views/forum.jsp");
                    dispatcher.forward(request, response);
                }
                    
            } catch (SQLException e) {
                PrintWriter out=response.getWriter();
                out.println(e);
                e.printStackTrace();
                errorString = e.getMessage();
            }
          //  String medicationName = list.get(0).getMedicationName();
            //errorString = medicationName;
            // Store info in request attribute, before forward to views
            request.setAttribute("errorString", errorString);
            //request.setAttribute("medicationName", medicationName);
            request.setAttribute("list", list);
            RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/searchedForum.jsp");
            dispatcher.forward(request, response);
        }
        
   }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doGet(request, response);
   }
 
}