<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Danh sách ứng viên ứng tuyển</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/userCompany.css">

    <style>
        .profile-image {
            margin-left: 80px;
            margin-right: 60px;
            width: 100px; /* Độ rộng của hình ảnh */
            height: 100px; /* Chiều cao của hình ảnh */
            object-fit: cover; /* Đảm bảo hình ảnh vừa khít với khu vực chứa */
            border-radius: 50%; /* Làm hình ảnh tròn */
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
        }

        .user-item {
            border: 1px solid #ddd;
            margin-bottom: 10px;
            padding: 10px;
            background-color: #fff;
        }

        .user-details {
            margin-top: 10px;
            display: flex;
        }

        .recruitment-details {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<header class="header-container">
    <div class="header-image-wrapper">
    </div>
    <div class="header-content">
        <a href="/asm2/home" style="color:white;"> Trang chủ </a> / <br><br> <h3> Danh sách ứng cử viên</h3>
    </div>
</header>
<div class="container">
    <c:if test="${not empty users}">
        <c:forEach var="user" items="${users}">
            <div class="user-item">
                <div class="user-details">
                    <div>
                        <img src="${pageContext.request.contextPath}/resources/images/default.jpg" alt="Default Image" class="profile-image">
                    </div>
                    <div>
                        <p><strong>Họ và tên:</strong> ${user.fullName}</p>
                        <p><strong>Email:</strong> ${user.email}</p>
                        <p><strong>Số điện thoại:</strong> ${user.phoneNumber}</p>
                        <p><strong>Vị trí ứng tuyển:</strong> ${user.recruitmentTitle}</p>
                    </div>
                </div>
                <div class="recruitment-details">
                    <!-- Các chi tiết tuyển dụng nếu cần -->
                </div>
            </div>
        </c:forEach>

      <!-- Pagination controls -->
                     <ul class="pagination justify-content-end mt-3">
                         <c:if test="${currentPage > 1}">
                             <li class="page-item">
                                 <a class="page-link" href="?page=1">First</a>
                             </li>
                             <li class="page-item">
                                 <a class="page-link" href="?page=${currentPage - 1}">Previous</a>
                             </li>
                         </c:if>
                         <c:forEach var="pageNum" begin="1" end="${totalPages}">
                             <li class="page-item ${pageNum == currentPage ? 'active' : ''}">
                                 <a class="page-link" href="?page=${pageNum}">${pageNum}</a>
                             </li>
                         </c:forEach>
                         <c:if test="${currentPage < totalPages}">
                             <li class="page-item">
                                 <a class="page-link" href="?page=${currentPage + 1}">Next</a>
                             </li>
                             <li class="page-item">
                                 <a class="page-link" href="?page=${totalPages}">Last</a>
                             </li>
                         </c:if>
                     </ul>


    </c:if>
    <c:if test="${empty users}">
        <p>Không có dữ liệu người dùng ứng tuyển vào công ty.</p>
    </c:if>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
