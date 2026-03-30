package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/image")
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id= req.getParameter("id");
        if (id == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id");
            return;
        }

        int i;
        try {
            i = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"invalid id ");
            return;
        }

        String url = "jdbc:mysql://localhost:3306/interns";
        String user = "root";
        String pass = "MYSQL";

        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = con.prepareStatement("SELECT photo FROM intern WHERE id = ?")) {

            ps.setInt(1, i);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    byte[] imageData = rs.getBytes("photo");
                    if (imageData != null && imageData.length > 0) {
                        resp.setContentType("image/jpeg");
                        try (OutputStream os = resp.getOutputStream()) {
                            os.write(imageData);
                            os.flush();
                        }
                        return;
                    }
                }
            }

            // if we reach here, no image found
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);

        } catch (SQLException e) {
            throw new ServletException("DB error while loading image", e);
        }
    }
}
