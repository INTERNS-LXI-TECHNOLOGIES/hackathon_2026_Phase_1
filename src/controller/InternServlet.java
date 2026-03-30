package controller;

import java.io.IOException;
import java.io.InputStream;

//import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;import

    dao.InternDAO;  
//import jakarta.servlet.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Intern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MultipartConfig
public class InternServlet extends HttpServlet {

  ///-*/ private static final Logger logger = LoggerFactory.getLogger(InternServlet.class);

 //  private static final Logger logger = LoggerFactory.getLogger(InternServlet.class);
    InternDAO internDAO = new InternDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set the content type of the response
        // Set
        // Get the PrintWriter to write the response
        // PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
    //  logger.info("action: {}", action);

        if (action == null) {
            response.sendRedirect("welcome.jsp");
            return;
        }
        if (action.equals("insert")) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phonne = request.getParameter("phone");
            Part filePart = request.getPart("photo");
            InputStream inputStream = filePart.getInputStream();
            byte[] photoInByte = inputStream.readAllBytes();
            Intern intern = new Intern(name, phonne, email, photoInByte);
            InternDAO dao = new InternDAO();
            dao.addIntern(intern);

            RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
            rd.forward(request, response);

        }
        if (action.equals("update")) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            int id = Integer.parseInt(request.getParameter("id"));
            Part photo = request.getPart("photo");
            InputStream phoInputStream = photo.getInputStream();
            byte[] photoInByte = phoInputStream.readAllBytes();
            Intern intern = new Intern(name, phone, email, photoInByte);
            intern.setId(id);
            try {

                internDAO.updateIntern(intern);
                // request.setAttribute("interns", internDAO.getAllInterns());
                // RequestDispatcher rd = request.getRequestDispatcher("interns.jsp");
                // rd.forward(request, response);
                RequestDispatcher rb = request.getRequestDispatcher("welcome.jsp");
                rb.forward(request, response);
            } catch (Exception e) {
          logger.error("Error updating intern: {}", e.getMessage(), e);
            }
        }
        if (action.equals("remove")) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                internDAO.deleteIntern(id);
          logger.info("Intern deleted with id={}", id);
                response.sendRedirect("welcome.jsp");
                /*
                 * request.setAttribute("interns", internDAO.getAllInterns());
                 * RequestDispatcher req =request.getRequestDispatcher("interns.jsp");
                 * req.forward(request, response);
                 */
            } catch (Exception e) {
             //   logger.error("Error deleting intern id={}: {}", id, e.getMessage(), e);
            }
        }
    }
    // protected void doGet(HttpServletRequest request , HttpServletResponse
    // response) throws ServletException, IOException {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int page = 1; // default page
            int recordsPerPage = 5; // how many per page

            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            // calculate start (OFFSET)
            int start = (page - 1) * recordsPerPage;

            // call DAO with pagination
            List<Intern> internList = internDAO.getAllInterns(start, recordsPerPage);

            // get total records
            int totalRecords = internDAO.getTotalRecords();

            // calculate total pages
            int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
         /*  logger.debug("Page: {}", page);
            logger.debug("Start: {}", start);
            logger.debug("Total Records: {}", totalRecords);
            logger.debug("List Size: {}", internList.size());
            
           */
           // send data to JSP
    
            request.setAttribute("interns", internList);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            request.getRequestDispatcher("interns.jsp").forward(request1, response);
        } catch (Exception e) {
            //logger.error("Error in doGet: {}", e.getMessage(), e);
        }
    }

}
