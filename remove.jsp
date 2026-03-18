<%@ page import ="java.sql.*" %>
<html>
    <body>
        <h1>To remove intern using Id </h1>
        <form>
            <label for="id">Id</label>

            <input type = "text" name = "id">
            <button type ="submit">submit</button>
        </form>
    </body>
</html>
<% 
String strId = request.getParameter("id");
if(strId != null){
int id = Integer.parseInt(strId);
Connection con = null;
PreparedStatement ps = null;
String user = "root";
String pass ="MYSQL";
String url ="jdbc:mysql://localhost:3306/interns";

try{

    Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager.getConnection(url,user,pass);
    String sql = "DELETE FROM intern where id = ?";
    ps =con.prepareStatement(sql);
    ps.setInt(1,id);
    ps.executeUpdate();
}
catch(Exception e){
    out.print(e);
}
}
%>