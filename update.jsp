<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Update Intern</title>

    <!-- Link CSS -->
    <link rel="stylesheet" href="css/form.css">
</head>

<body>

<div class="form-container">

    <h2>Update Intern</h2>

    <form action="interns" method="post">

        <input type="hidden" name="action" value="update">

        <label for="name">Name</label>
        <input type="text" id="name" name="name" required>

        <label for="id">ID</label>
        <input type="text" id="id" name="id" required>

        <label for="phone">Phone</label>
        <input type="text" id="phone" name="phone">

        <label for="email">Email</label>
        <input type="email" id="email" name="email">
        <label >Photo</label>
        <input type ="file" name ="photo">
        <div class="btn-group">
            <button type="submit">Update</button>
            <button type="reset">Clear</button>
        </div>

        <a href="welcome.jsp" class="back">Back to Home</a>

    </form>

</div>

</body>
</html>