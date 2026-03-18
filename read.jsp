<%@ page import ="java.sql.*" %>

<html>
    <body>
        <center>
        
        <h1>Interns List </h1>
        <table border="1">

            <tr><th>Id</th>
                <th>Name</th> 
                <th>Phone</th>
                <th>Email</th>
            </tr>
            <% String url = "jdbc:mysql://localhost:3306/interns";
            
            String uesrName ="root";

            String pass="MYSQL";
            Connection con =null;
            PreparedStatement ps = null ;
            ResultSet rs = null ;
            try{

    Class.forName("com.mysql.cj.jdbc.Driver");

    con = DriverManager.getConnection(url,uesrName,pass);
                String sql = "SELECT * FROM intern";
                ps = con.prepareStatement(sql);
                rs =ps.executeQuery();
                while(rs.next()){
                   %>

<tr><td><%= rs.getInt("id") %></td>
<td><%= rs.getString("name") %></td>
<td><%= rs.getString("phone") %></td>
<td><%= rs.getString("email") %></td>
</tr>

<%

                }
            }catch(Exception e){
                out.print(e);
            }
            %>
            
        
        </table>
        </center>
    </body>
</html>