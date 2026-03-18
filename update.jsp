<%@ page import="java.sql.*" %>

<html>
   <body>
      <form>
         <label for ="name">Name</label>
         <input type="text" id ="name" name = "name"><br><br>
         <label for ="id" id = "id"> Id</label>
         <input type="text" id  = "id" name ="id"> <br>
         <label for="email" id = "email">Email</label>
         <input type="email" id ="email" name = "email"><br>
      <button type="submit">submit</button>
      <button type ="reset">clear</button> <br>
      </form>
   </body>                      
</html>
<% 

String idStr = request.getParameter("id");
String name = request.getParameter("name");  
String email = request.getParameter("email");
if(idStr != null && email != null){
int id = Integer.parseInt(idStr);
String url = "jdbc:mysql://localhost:3306/interns";
String user ="root";
String pass = "MYSQL";

Connection con= null;  
PreparedStatement ps = null ;
ResultSet rs = null ;
 try{

    Class.forName("com.mysql.cj.jdbc.Driver");
    con = DriverManager .getConnection(url,user,pass);
    String sql = "UPDATE  intern  SET name= ?, email =? where id = ?";
    ps = con.prepareStatement(sql);
    ps.setString(1,name);
    ps.setString(2,email);
    ps.setInt(3,id);
    ps.executeUpdate 
    ();
 }
 catch(Exception e){
 out.print(e);
 }
}

%>