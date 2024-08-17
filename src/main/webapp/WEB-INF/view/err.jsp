<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lỗi</title>
         <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">

</head>
<body>
<div class="container">
    <h1 class="mt-5">Đã xảy ra lỗi</h1>
    <div class="alert alert-danger mt-3">
        <c:if test="${not empty errorMessage}">
            ${errorMessage}
        </c:if>
        <c:if test="${empty errorMessage}">
            Một lỗi không xác định đã xảy ra.
        </c:if>
    </div>
    <a href="/asm2/home" class="btn btn-primary">Trở về trang chủ</a>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
