package controller;
import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import dao.InternDAO;
//import jakarta.servlet.*;
import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Intern;

public class InternServlet extends HttpServlet {  
   

    InternDAO internDAO = new InternDAO();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the content type of the response       
        // Set 
        // Get the PrintWriter to write the response
        //   PrintWriter out = response.getWriter();
         String action = request.getParameter("action");
         System.out.println(action);

         if (action == null){
          response.sendRedirect("welcome.html");
          return;
         }
         if(action.equals("insert")){
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
    if(action.equals("update")){
        String name =request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int id = Integer.parseInt(request.getParameter("id"));
         Intern intern = new Intern(name, phone, email);
         intern.setId(id);
         try{

         internDAO.updateIntern(intern);
         request.setAttribute("interns", internDAO.getAllInterns());
RequestDispatcher rd = request.getRequestDispatcher("interns.jsp");
rd.forward(request, response);
         }catch(Exception e){
            System.err.println(e.getMessage());
         }
    }
    if (action.equals("remove")) {
        int id = Integer.parseInt(request.getParameter("id"));
        try{
        internDAO.deleteIntern(id);
        System.out.println("Intern deleted");
        response.sendRedirect("welcome.html");
     /*    request.setAttribute("interns", internDAO.getAllInterns());
        RequestDispatcher req =request.getRequestDispatcher("interns.jsp");
        req.forward(request, response);
        */
    }
    catch(Exception e){
      e.printStackTrace();
    }
}
}
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
          
          
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










