package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/image")
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
            int i = Integer.parseInt(req.getParameter("id"));
       try {
        Connection con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/inters","root","MYSQL");
        PreparedStatement ps =con.prepareStatement("SELECT photo FROM intern WHERE id = ?");
        ps.setInt(1 , i);
        ResultSet rs =  ps.executeQuery();
        if(rs.next()){
                byte[] img = rs.getBytes("photo");
                resp.setContentType("imgae/jpeg") ;
                resp.getOutputStream().write(img);         
        }
           } catch (Exception e) {
// TODO: handle exception
        
                  e.printStackTrace();
       } 
    }
}
