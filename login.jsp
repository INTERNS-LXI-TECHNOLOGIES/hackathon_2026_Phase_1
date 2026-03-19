<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Create Intern</title>

    <!-- Use SAME CSS -->
    <link rel="stylesheet" href="css/form.css">
</head>

<body>

<div class="form-container">

    <h2>Create Intern</h2>

    <form action="interns" method="post">

        <!-- Important for servlet -->
        <input type="hidden" name="action" value="create">

        <label>Name</label>
        <input type="text" name="name" required>

        <label>Email</label>
        <input type="email" name="email" required>

        <label>Phone</label>
        <input type="text" name="phone" required>

        <div class="btn-group">
            <button type="submit">Create</button>
            <button type="reset">Clear</button>
        </div>

        <!-- Correct way (NOT button inside link) -->
        <a href="welcome.html" class="back">Back to Home</a>

    </form>

</div>

</body>
</html>