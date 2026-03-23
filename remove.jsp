<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Remove Intern</title>

    <!-- Reuse same CSS -->
    <link rel="stylesheet" href="css/remove.css">
</head>

<body>

<div class="form-container">

    <h2>Remove Intern</h2>

    <form action="interns" method="post">

        <input type="hidden" name="action" value="remove">

        <label for="id">Enter Intern ID</label>
        <input type="text" id="id" name="id" required>

        <div class="btn-group">
            <button type="submit" class="delete-btn">Delete</button>
            <button type="reset">Clear</button>
        </div>

        <a href="welcome.jsp" class="back">Back to Home</a>

    </form>

</div>

</body>
</html>