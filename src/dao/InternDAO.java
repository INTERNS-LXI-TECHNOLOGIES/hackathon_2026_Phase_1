package dao;
import model.Intern;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;
public class InternDAO {
    private  final String url ="jdbc:mysql://localhost:3306/interns";
    private final String username = "root";
    private final String password = "MYSQL";
       public InternDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

 private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
    public void addIntern(Intern intern) {
        try  (Connection con = getConnection()){
            String sql = "INSERT INTO intern (name, email, phone) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, intern.getName());
            ps.setString(2, intern.getEmail());
            ps.setString(3, intern.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public List<Intern>  getAllInterns() throws SQLException{
     List<Intern> interns = new ArrayList<>();
     

     String sql = "SELECT * FROM intern";
          try(Connection  con = getConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                Intern intern = new Intern(name, phone, email);
                intern.setId(id);
                
                   interns.add(intern);
            }
          }
          return interns;
        }
            public void updateIntern(Intern intern) {
            String sql = "UPDATE intern SET name = ?, email = ?, phone = ? WHERE id = ?";
            try(Connection con = getConnection()){
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, intern.getName());
                ps.setString(2, intern.getEmail());
                ps.setString(3, intern.getPhone());
                ps.setInt(4, intern.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
             System.err.println("Error updating intern: " + e.getMessage());
            }

}
             public void deleteIntern(int id) {
            String sql = "DELETE FROM intern WHERE id = ?";
            try(Connection con = getConnection()){
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error deleting intern: " + e.getMessage());
            }
        }
    }