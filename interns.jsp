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

            
                </form>
            </td>
        </tr>

<%
        }
    } else {
%>
    </table>
    <p class="no-data">No interns found</p>
<%
    }
%>

    <a href="welcome.html" class="back">Back to Home</a>

</div>

</body>
</html>