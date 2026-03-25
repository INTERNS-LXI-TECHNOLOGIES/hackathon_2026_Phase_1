<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Intern" %>

<!DOCTYPE html>
<html>
<head>
    <title>Intern Management</title>

    <link rel="stylesheet" href="css/table.css">
</head>

<body>

<div class="table-container">

    <h2>Intern List</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Photo </th>
        </tr>

<%
    List<Intern> interns = (List<Intern>) request.getAttribute("interns");

    if (interns != null && !interns.isEmpty()) {
        for (Intern intern : interns) {
%>

        <tr>
            <td><%= intern.getId() %></td>
            <td><%= intern.getName() %></td>
            <td><%= intern.getPhone() %></td>
            <td><%= intern.getEmail() %></td>
         <td>
    <img src="<%= request.getContextPath() %>/image?id=<%= intern.getId() %>" 
         width="100" height="100"/>
</td>
        </tr>

<%
        }
    } else {
%>
   
    <p class="no-data">No interns found</p>
<%
    }
%>
 </table>
    


<%
Integer currentPageObj = (Integer) request.getAttribute("currentPage");
Integer totalPagesObj = (Integer) request.getAttribute("totalPages");

int currentPage = (currentPageObj != null) ? currentPageObj : 1;
int totalPages = (totalPagesObj != null) ? totalPagesObj : 1;
%>
<div     class ="pagination" style="margin-top:20px;">

    <% if (currentPage > 1) { %>
        <a href="interns?page=<%= currentPage - 1 %>">Prev</a>
    <% } %>

    <% for (int i = 1; i <= totalPages; i++) { %>
        <a href="interns?page=<%= i %>"><%= i %></a>
    <% } %>

    <% if (currentPage < totalPages) { %>
        <a href="interns?page=<%= currentP  age + 1 %>">Next</a>
    <% } %>

</div>
<a href="welcome.jsp" class="back">Back to Home</a>

</div>


</body>
</html>