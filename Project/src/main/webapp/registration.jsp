<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>

<h1>Registration</h1>

<form action="authentication" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>
    <input type="hidden" name="action" value="register">
    <button type="submit">Register</button>
</form>

<p>Already have an account? <a href="login.jsp">Login here</a></p>

</body>
</html>
