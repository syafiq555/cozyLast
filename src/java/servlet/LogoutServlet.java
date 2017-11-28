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

@WebServlet(urlPatterns = { "/LogoutServlet" })
public class LogoutServlet extends HttpServlet {  
        protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {  
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter();  
              
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
            
            dispatcher.forward(request, response);
            HttpSession session=request.getSession();  
            session.invalidate();  
             
            out.print("<center><p style='color:Tomato;'>You are successfully logged out!</p></center>");  
              
            out.close();  
    }  
} 