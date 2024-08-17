<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/styles.css' />">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style>
        body {
            background-color: #f8f9fa;
        }
        .register-container {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        .register-title {
            text-align: center;
            margin-bottom: 20px;
        }
        .register-button {
            width: 100%;
        }
        .error-message {
            color: #dc3545; /* Màu chữ đỏ */
            font-size: 0.9rem; /* Kích thước font nhỏ hơn */
            margin-top: 5px; /* Khoảng cách từ lỗi đến các thành phần khác */
        }
    </style>
</head>
<body>
    <div class="register-container">
    <a href="/asm2/home"> Trang chủ </a>
        <h2 class="register-title">Đăng ký</h2>
      <form:form action="${pageContext.request.contextPath}/register" method="post" modelAttribute="user">
          <div class="form-group">
              <label for="email">Email</label>
              <form:input type="email" class="form-control" id="email" path="email" required="true"/>
          </div>
          <div class="form-group">
              <label for="fullName">Họ và tên</label>
              <form:input type="text" class="form-control" id="fullName" path="fullName" required="true"/>
          </div>
          <div class="form-group">
              <label for="password">Mật khẩu</label>
              <form:password class="form-control" id="password" path="password" required="true"/>
          </div>
            <div class="form-group">
                <label for="confirmPassword">Nhập lại mật khẩu</label>
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
            </div>
          <div class="form-group">
              <label for="role">Vai trò</label>
              <form:select class="form-control" id="role" path="role" required="true">
                  <form:option value="1">Người dùng</form:option>
                  <form:option value="2">Công ty</form:option>
              </form:select>
          </div>

          <button type="submit" class="btn btn-primary register-button">Đăng ký</button>
          <span class="error-message">${error}</span>
      </form:form>

    </div>

</body>

</html>
