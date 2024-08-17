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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/posts.css">
 <style>
 body {
     background-color: #E0E0E0;
 }

     .recruitment-item {
         display: flex;
         padding: 20px;
         justify-content: space-between;
         align-items: center;
         background-color: #FFFFFF;
         margin-top: 20px; /* Adjust margin between items */
     }

     .content {
         flex: 1;
     }

     .company-info {
         display: flex;
         align-items: center;
     }

     .company-info p {
         margin-bottom: 0; /* Remove default bottom margin for paragraphs */
     }

     .buttons {
         display: flex;
         align-items: center;
     }

     .buttons button {
         margin-right: 10px; /* Adjust spacing between buttons */
     }
 </style>
</head>
<body>
<header class="header-container">
    <div class="header-image-wrapper">
    </div>

    <div class="header-content">
        <a href="/asm2/home" style="color:white;"> Trang chủ </a> > Danh sách bài đăng
    </div>
    <div class="header-text">
        <h1>Danh sách bài đăng</h1>
    </div>
</header>

<section>
    <div class="container">
        <c:forEach var="recruitment" items="${recruitments}">
            <div class="recruitment-item">
                <div class="content">
                    <p style="color: blue;">${recruitment.type}</p>
                    <h4>Tuyển ${recruitment.title}</h4>
                    <div class="company-info">
                        <p><i class="bi bi-stack"></i> ${recruitment.company.nameCompany}</p>
                        <p style="margin-left: 8px;">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-crosshair" viewBox="0 0 16 16">
                                <path d="M8.5.5a.5.5 0 0 0-1 0v.518A7 7 0 0 0 1.018 7.5H.5a.5.5 0 0 0 0 1h.518A7 7 0 0 0 7.5 14.982v.518a.5.5 0 0 0 1 0v-.518A7 7 0 0 0 14.982 8.5h.518a.5.5 0 0 0 0-1h-.518A7 7 0 0 0 8.5 1.018zm-6.48 7A6 6 0 0 1 7.5 2.02v.48a.5.5 0 0 0 1 0v-.48a6 6 0 0 1 5.48 5.48h-.48a.5.5 0 0 0 0 1h.48a6 6 0 0 1-5.48 5.48v-.48a.5.5 0 0 0-1 0v.48A6 6 0 0 1 2.02 8.5h.48a.5.5 0 0 0 0-1zM8 10a2 2 0 1 0 0-4 2 2 0 0 0 0 4"/>
                            </svg> ${recruitment.address}
                        </p>
                    </div>
                </div>
                <div class="buttons">
                    <button onClick="redirectToUpdateDetails(${recruitment.id})" class="btn btn-primary">Xem chi tiết</button>
                    <button onClick="redirectToUpdateRedirect(${recruitment.id})" class="btn btn-warning">Cập nhật</button>

            <a href="/posts/delete?recruitId=${recruitment.id}" class="btn btn-danger" data="${recruitment.title}" onclick="return confirmDelete(event, this)">Xóa</a>

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
    </div>
</section>
<script>
    function redirectToUpdateRedirect() {
        window.location.href = "${pageContext.request.contextPath}/posts/showupdaterecruit";
    }
  function redirectToUpdateRedirect(recruitId) {
        window.location.href = "${pageContext.request.contextPath}/posts/showupdaterecruit?recruitId=" + recruitId;
    }
     function redirectToUpdateDetails(recruitId) {
        window.location.href = "${pageContext.request.contextPath}/posts/detailsrecruit?recruitId=" + recruitId;
    }

   function handleButtonClick(recruitId) {
        if (confirmDelete(event)) {
            redirectToUpdateRedirect(recruitId);
        }
    }

</script>
 <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
 <script>
 function confirmDelete(event, element) {
     event.preventDefault(); // Ngăn chặn hành động mặc định của liên kết
     const userFullName = element.getAttribute('data');
     Swal.fire({
         title: "Bạn chắc chắn muốn xóa bài đăng tuyển " + userFullName + "?",
         text: "Hành động này không thể được hoàn tác!",
         icon: 'warning',
         showCancelButton: true,
         confirmButtonColor: '#3085d6',
         cancelButtonColor: '#d33',
         confirmButtonText: 'Xóa',
         cancelButtonText: 'Đóng'
     }).then((result) => {
         if (result.isConfirmed) {
             window.location.href = element.href;
         }
     });
     return false;
 }
 </script>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
