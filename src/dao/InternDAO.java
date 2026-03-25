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
            String sql = "INSERT INTO intern (name, email, phone ,photo) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, intern.getName());
            ps.setString(2, intern.getEmail());
            ps.setString(3, intern.getPhone());
            ps.setBytes(4, intern.getPhoto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public List<Intern>  getAllInterns(int start , int total ) throws SQLException{
     List<Intern> interns = new ArrayList<>();
     String sql = "SELECT * FROM intern LIMIT ? , ?";
          try(Connection  con = getConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
           ps.setInt(1,start);
           ps.setInt(2,total);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                byte[] photo = rs.getBytes("photo");
                Intern intern = new Intern(name, phone, email,photo);
                intern.setId(id);
                
                   interns.add(intern);
            }
          }
          return interns;
        }
            public void updateIntern(Intern intern) {
            String sql = "UPDATE intern SET name = ?, email = ?, phone = ? , photo = ? WHERE id = ?";
            try(Connection con = getConnection()){
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, intern.getName());
                ps.setString(2, intern.getEmail());
                ps.setString(3, intern.getPhone());
                ps.setInt(5, intern.getId());
                ps.setBytes(4, intern.getPhoto());
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

       public  int getTotalRecords(){
          int count = 0 ; 
          try{
  Connection con = getConnection();
  String sql = "SELECT COUNT(*) from intern ";
        PreparedStatement ps = con.prepareStatement(sql);
          ResultSet rs = ps.executeQuery();
          if(rs.next()){
            count = rs.getInt(1);
          }
        } 
    catch (Exception e){
         e.printStackTrace();
    }
            return count  ;
        }
    }