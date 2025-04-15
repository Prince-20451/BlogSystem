<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Blog System</title>
    <link rel="stylesheet" href="css/register.css">
</head>
<body>
    <div class="container">
        <h2>🧾 User Registration</h2>

        <form action="RegisterServlet" method="post">
            <label>Name:</label>
            <input type="text" name="name" required>

            <label>Email:</label>
            <input type="email" name="email" required>

            <label>Password:</label>
            <input type="password" name="password" required>

            <button type="submit">✅ Register</button>
        </form>

        <div class="login-link">
            <p>Already have an account? <a href="login.jsp">Login Here</a></p>
        </div>
    </div>
</body>
</html>

