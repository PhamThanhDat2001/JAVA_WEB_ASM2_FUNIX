<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/styles.css' />">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
           body {
               background-color: #f8f9fa;
           }
           .login-container {
               max-width: 400px;
               margin: 50px auto;
               padding: 20px;
               background-color: #fff;
               box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
               border-radius: 8px;
           }
           .login-title {
               text-align: center;
               margin-bottom: 20px;
           }
           .login-button {
               width: 100%;
               margin-top: 10px; /* Khoảng cách giữa các nút */
           }
           .register-button {
               width: 100%;
               margin-top: 10px; /* Khoảng cách giữa các nút */
           }
       </style>
</head>
<body>
<div class="container">
 <a href="/asm2/home"> Trang chủ </a>
    <h2>Đăng nhập</h2>
   <form:form action="/authenticateTheUser" method="post" modelAttribute="user">
       <div class="form-group">
           <form:label for="email" path="email">Email:</form:label>
           <form:input type="email" class="form-control" id="email" path="email" required="true"/>
       </div>
       <div class="form-group">
           <form:label for="password" path="password">Mật khẩu:</form:label>
           <form:password class="form-control" id="password" path="password" required="true"/>
       </div>

       <button type="submit" class="btn btn-primary">Đăng nhập</button>
   </form:form>
  <c:if test="${not empty error}">
          <div class="alert alert-danger mt-3">
              ${error}
          </div>
      </c:if>
</div>

</body>

</html>
