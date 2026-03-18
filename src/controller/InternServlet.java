package controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import dao.InternDAO;
//import jakarta.servlet.*;
import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Intern;

public class InternServlet extends HttpServlet {  
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the content type of the response       
        // Set 
        // Get the PrintWriter to write the response
        //   PrintWriter out = response.getWriter();

        String name  = request.getParameter("name");
        String  email= request.getParameter("email");
        String phonne = request.getParameter("phone");
        Intern intern = new Intern( name, phonne, email);
        InternDAO dao = new InternDAO();
        dao.addIntern(intern);  
        response.getWriter().println("Intern created ");
        response.getWriter().println("Servlet working!");
        //Write 

    }
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
          
          InternDAO internDAO = new InternDAO();
           try {
            List<Intern> intern = internDAO.getAllInterns();
            request.setAttribute("interns", intern);
            request.getRequestDispatcher("interns.jsp").forward(request, response);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }

}










