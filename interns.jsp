<%
    List<Intern> interns = (List<Intern>) request.getAttribute("interns");
    if (interns != null) {
        for (Intern intern : interns) {
            out.println("<p>" + intern.getName() + " - " + intern.getEmail() + "</p>");
        }
    }
%>