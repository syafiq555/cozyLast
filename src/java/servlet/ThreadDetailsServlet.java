package servlet;

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
 
@WebServlet (urlPatterns = {"/threadDetails"})
public class ThreadDetailsServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
 
   public ThreadDetailsServlet() {
       super();
   }
 
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       
        HttpSession session = request.getSession();
        User userInSession = MyUtils.getLoginedUser(session);
        boolean hasError = false;
        String errorString = null;
        Thread thread = null;
        int threadId = 0;
        try{
            threadId = Integer.parseInt(request.getParameter("threadId"));
        }catch(NumberFormatException e){
            PrintWriter out = response.getWriter();
            out.println(threadId+" "+e);
            e.printStackTrace();
            
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
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                thread = DBUtils.findThread(conn, threadId);
                    
            } catch (Exception e) {
                PrintWriter out=response.getWriter();
                out.println(e);
                e.printStackTrace();
                errorString = e.getMessage();
            }
          //  String threadName = list.get(0).getThreadName();
            //errorString = threadName;
            // Store info in request attribute, before forward to views
            request.setAttribute("errorString", errorString);
            //request.setAttribute("threadName", threadName);
            request.setAttribute("thread", thread);
            RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/threadDetails.jsp");
            dispatcher.forward(request, response);
        }
        
   }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doGet(request, response);
   }
 
}