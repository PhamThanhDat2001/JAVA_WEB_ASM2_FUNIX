<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <style>

        </style>

     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/profile-user.css"


</head>
<body>
<header class="header-container">
    <div class="header-image-wrapper" >
        <img class="header-img" src="../resources/images/bg_1.jpg" alt="Hình ảnh" >
    </div>
    <div class="header-logo">
        <h5>Word CV</h5>
    </div>
    <div class="header-content">
        <button onClick="redirectToHome()">Trang chủ</button>
        <button>Công việc</button>
        <button>Ứng cử viên</button>

      <!-- Kiểm tra xem người dùng đã xác thực hay chưa -->
           <sec:authorize access="isAuthenticated()">
               <div class="dropdown">
                   <button class="btn btn-secondary dropdown-toggle" type="button"
                   id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                   aria-expanded="false">
                       Chào, ${loggedInUser.fullName}
                   </button>
                   <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                       <a class="dropdown-item" href="/profileUser">Hồ sơ người dùng</a>
                       <a class="dropdown-item" href="/profile">Hồ sơ công ty</a>
                       <a class="dropdown-item" href="/posts/list">Danh sách bài đăng</a>
                       <a class="dropdown-item" href="/logout" onclick="logout()">Đăng xuất</a>

                   </div>
                       <button onclick="redirectToRecruit()">Đăng tuyển</button>

               </div>
           </sec:authorize>

              <sec:authorize access="!isAuthenticated()">
                  <button onclick="redirectToLogin()">Đăng nhập</button>
                  <button onclick="redirectToRegister()">Đăng ký</button>
              </sec:authorize>


    </div>
   <div class="header-text">
       <h2>Hồ sơ</h2>
       <c:choose>
           <c:when test="${not empty loggedInUser.image}">
               <img src="${loggedInUser.image}" alt="Image" class="profile-image">
           </c:when>
           <c:otherwise>
               <img src="../resources/images/default.jpg" alt="Default Image" class="profile-image">
           </c:otherwise>
       </c:choose>
   </div>


</header>
  <!-- Form upload CV -->
 <form method="post" action="${pageContext.request.contextPath}/uploadCV" enctype="multipart/form-data">
        <div class="form-group">
            <label for="file">Chọn CV (.pdf)</label>
            <input type="file" class="form-control-file" id="file" name="file">
        </div>
        <button type="submit" class="btn btn-primary">Tải lên</button>
    </form>

<section class="container mt-4 pt-4 center-section">
    <div class="row">
        <div class="col-md-12">
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title">Thông tin cá nhân</h3>
                    <form:form modelAttribute="user" action="updatePersonalInfo" method="post" accept-charset="UTF-8" class="user-form">
                        <!-- Hidden input field for 'id' -->
                        <form:hidden path="id" />
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <form:input path="email" readonly="true" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="fullName">Họ và tên:</label>
                            <form:input path="fullName" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="address">Địa chỉ:</label>
                            <form:input path="address" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="phoneNumber">Số điện thoại:</label>
                            <form:input path="phoneNumber" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="description">Mô tả bản thân:</label>
                            <form:textarea path="description" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="cv">CV:</label>
                            <form:input path="cv.fileName" class="form-control"  readonly="true" />
                        </div>
                        <button type="submit" class="btn btn-primary">Cập nhật thông tin</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</section>




<script>
    function redirectToHome() {
        window.location.href = "${pageContext.request.contextPath}/asm2/home";
    }

      function redirectToRecruit() {
            window.location.href = "${pageContext.request.contextPath}/posts/recruit";
        }

    function redirectToLogin() {
        window.location.href = "${pageContext.request.contextPath}/login";
    }

    function redirectToRegister() {
        window.location.href = "${pageContext.request.contextPath}/register";
    }

    function logout() {
        window.location.href = "${pageContext.request.contextPath}/logout";
    }
</script>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
