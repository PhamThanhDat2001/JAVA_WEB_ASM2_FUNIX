<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/recruit.css">
 <style>
 body {
     background-color: #E0E0E0;
 }


 </style>
</head>
<body>
<header class="header-container">
    <div class="header-image-wrapper">
    </div>

    <div class="header-content">
        <a href="/asm2/home" style="color:white;"> Trang chủ </a> / <br> <h4> Đăng bài </h4>
    </div>

</header>

<section>
<a href="/posts/list"> Quay lại </a>
            <h3>Chi tiết bài tuyển dụng </h3>
   <form:form action="${pageContext.request.contextPath}/posts/uprecruit" method="post" modelAttribute="recruit">

             <form:hidden path="id" />
            <div class="form-group">
                <label for="title">Tiêu đề</label>
                <form:input class="form-control" id="title" path="title" required="true"/>
            </div>

            <div class="form-group">
                    <label for="description">Mô tả công việc</label>
                <form:input type="text" class="form-control" id="description" path="description" required="true"/>
            </div>

            <div class="form-group">
                <label for="experience">Kinh nghiệm</label>
                <form:input class="form-control" id="experience" path="experience" required="true"/>
            </div>

            <div class="form-group">
                <label for="quantity">Số người cần tuyển</label>
                <form:input class="form-control" id="quantity" path="quantity" required="true"/>
            </div>

            <div class="form-group">
                <label for="address">Địa chỉ</label>
                <form:input class="form-control" id="address" path="address" required="true"/>
            </div>

            <div class="form-group">
                <label for="deadline">Hạn ứng tuyển</label>
                <form:input type="date" class="form-control" id="deadline" path="deadline" required="true"/>
            </div>

            <div class="form-group">
                <label for="salary">Lương</label>
                <form:input class="form-control" id="salary" path="salary" required="true"/>
            </div>



        <form:hidden id="company" path="company.id" value="${sessionScope.loggedInCompany.id}" required="true"/>


            <div class="form-group">
                <label for="type">Loại công việc</label>
                <form:select class="form-control" id="type" path="type" required="true">
                    <form:option value="Toàn thời gian">Toàn thời gian</form:option>
                    <form:option value="Bán thời gian">Bán thời gian</form:option>
                </form:select>
            </div>

            <div class="form-group">
                <label for="category">Danh mục công việc</label>
                <form:select class="form-control" id="category" path="category" required="true">
                    <form:option value="1">Công nghệ thông tin</form:option>
                    <form:option value="2">Kinh doanh</form:option>
                    <form:option value="3">Y tế</form:option>
                    <form:option value="4">Kỹ thuật</form:option>
                    <form:option value="5">Nghệ thuật</form:option>
                </form:select>
            </div>

            <button type="submit" class="btn btn-primary register-button">Đăng</button>

        </form:form>
</section>


<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
