package servlet;

import beans.Post;
import beans.ThreadPost;
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
 
@WebServlet(urlPatterns = { "/forumDetails"})
public class ForumDetailsServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
 
   public ForumDetailsServlet() {
       super();
   }
 
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
 
        HttpSession session = request.getSession();
        User userInSession = MyUtils.getLoginedUser(session);
        boolean hasError = false;
        String errorString = null;
        List<ThreadPost> list = null;
        Post post = null;
        int threadId = 0;
        try{
            threadId = Integer.parseInt(request.getParameter("threadId"));
        }catch(Exception e){
            hasError = true;
            errorString = e.getMessage();
            
            request.setAttribute("errorString", errorString);
 
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/forum.jsp");
            dispatcher.forward(request, response);
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
                list = DBUtils.queryThreadPost(conn, threadId);
                if(list.isEmpty()){
                    hasError = true;
                    errorString = "No post yet!";
                    
                    post = DBUtils.findPost(conn,threadId);
                    
                    request.setAttribute("errorString", errorString);
                    request.setAttribute("post", post);
                    RequestDispatcher dispatcher //
                        = this.getServletContext().getRequestDispatcher("/WEB-INF/views/ForumDetails.jsp");
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
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/ForumDetails.jsp");
            dispatcher.forward(request, response);
        }
        
   }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doGet(request, response);
   }
 
}