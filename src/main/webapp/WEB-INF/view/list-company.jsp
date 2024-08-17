<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Danh sách tuyển dụng</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <style>
        body {
            background-color: #F8F9FB;
            font-family: Arial, sans-serif;
        }

        .container {
            margin-top: 20px;
        }

        .related-job-item {
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 15px;
            margin-bottom: 15px;
            background-color: #fff;
            transition: box-shadow 0.3s;
        }

        .related-job-item:hover {
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .related-job-item h4 {
            font-size: 20px;
            color: #333;
        }

        .related-job-item p {
            margin: 0;
            color: #555;
        }

        .related-job-item .bi {
            margin-right: 5px;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
            transition: background-color 0.3s, border-color 0.3s;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #004085;
        }

        .btn-primary:focus, .btn-primary:active {
            box-shadow: none;
            outline: none;
        }

        .pagination {
            justify-content: center;
        }
        .header-container {
            background-color: #007bff;
            padding: 20px;
            color: white;
            text-align: center;
        }

        .header-image-wrapper {
            margin-bottom: 20px;
        }

        .header-content a {
            color: white;
            text-decoration: none;
            font-size: 18px;
        }

        .header-content h4 {
            margin-top: 10px;
            font-size: 24px;
        }
    </style>
</head>
<body>
<header class="header-container">
    <div class="header-image-wrapper">
    </div>

    <div class="header-content">
        <a href="/asm2/home" style="color:white;"> Trang chủ </a> / <br> <h4> Công ty </h4>
        <h3>Danh sách công ty đã theo dõi</h3>
    </div>

</header>
    <div class="container mt-4">
        <div class="related-jobs">
            <c:forEach var="company" items="${companys}">
                <div class="related-job-item">
                    <p style="color:blue;">${company.nameCompany}</p>
                    <div style="display:flex">
                        <p><i class="bi bi-stack"></i>${company.gmail}</p>
                        <p><i class="bi bi-stack"></i>${company.address}</p>
                        <p style="margin-left:8px;">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-crosshair" viewBox="0 0 16 16">
                                <path d="M8.5.5a.5.5 0 0 0-1 0v.518A7 7 0 0 0 1.018 7.5H.5a.5.5 0 0 0 0 1h.518A7 7 0 0 0 7.5 14.982v.518a.5.5 0 0 0 1 0v-.518A7 7 0 0 0 14.982 8.5h.518a.5.5 0 0 0 0-1h-.518A7 7 0 0 0 8.5 1.018zm-6.48 7A6 6 0 0 1 7.5 2.02v.48a.5.5 0 0 0 1 0v-.48a6 6 0 0 1 5.48 5.48h-.48a.5.5 0 0 0 0 1h.48a6 6 0 0 1-5.48 5.48v-.48a.5.5 0 0 0-1 0v.48A6 6 0 0 1 2.02 8.5h.48a.5.5 0 0 0 0-1zM8 10a2 2 0 1 0 0-4 2 2 0 0 0 0 4"/>
                            </svg>${recruitment.address}
                        </p>
                    </div>
                    <div>
<button class="btn btn-primary" onclick="openApplyModal()">Apply Job</button>
<button class="btn btn-primary" onclick="redirectToDetails(${recruitment.id})">Chi tiết</button>

                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- Modal -->
         <c:forEach var="company" items="${companys}">

    <div class="modal fade" id="applyModal" tabindex="-1" role="dialog" aria-labelledby="applyModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="applyModalLabel">${company.title}</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                   <form:form id="applyForm" method="post" modelAttribute="applyPost" action="${pageContext.request.contextPath}/applypost/save" enctype="multipart/form-data">
                       <!-- Add data-recruitment-id attribute to store topRecruitment.id -->
            <input type="hidden" id="recruitmentId" name="recruitmentId" value="${recruitment.id}">


                       <div class="form-group">
                           <label for="applicationType">Chọn loại nộp hồ sơ:</label>
                           <form:select class="form-control" id="applicationType" path="applicationType" onchange="toggleCVFields()">
                               <form:option value="existingCV" label="Dùng CV đã cập nhật"/>
                               <form:option value="newCV" label="Nộp CV mới"/>
                           </form:select>
                       </div>
                       <div class="form-group" id="existingCVGroup">
                           <label for="text">Giới thiệu bản thân:</label>
                           <form:textarea path="text" class="form-control" id="text" rows="3"/>

                       </div>
                       <div class="form-group" id="newCVGroup" style="display: none;">
                           <label for="nameCv">Chọn CV:</label>
                           <input type="file" name="file" class="form-control-file" id="nameCv"/>
                           <label for="text">Giới thiệu bản thân:</label>
                           <form:textarea path="text" class="form-control" id="text" rows="3"/>
                       </div>
                   </form:form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                    <button type="button" class="btn btn-primary" onclick="submitApplication()">Nộp</button>
                </div>
            </div>
        </div>
    </div>
</c:forEach>

  <!-- Controls phân trang -->
            <ul class="pagination justify-content-end mt-3" style="margin-right:125px;">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="?page=1">Đầu</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage - 1}">Trước</a>
                    </li>
                </c:if>
                <c:forEach var="pageNum" begin="1" end="${totalPages}">
                    <li class="page-item ${pageNum == currentPage ? 'active' : ''}">
                        <a class="page-link" href="?page=${pageNum}">${pageNum}</a>
                    </li>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage + 1}">Tiếp</a>
                    </li>
                    <li class="page-item">
                        <a class="page-link" href="?page=${totalPages}">Cuối</a>
                    </li>
                </c:if>
            </ul>


  <c:if test="${empty recruitments}">
        <p>Danh sách trống.</p>
    </c:if>
   <script>
        function redirectToHome() {
            window.location.href = "${pageContext.request.contextPath}/asm2/home";
        }

        function redirectToLogin() {
            window.location.href = "${pageContext.request.contextPath}/login";
        }

        function redirectToRegister() {
            window.location.href = "${pageContext.request.contextPath}/register";
        }
        function redirectToRecruit() {
            window.location.href = "${pageContext.request.contextPath}/posts/recruit";
        }

        function logout() {
            window.location.href = "${pageContext.request.contextPath}/logout";
        }
        function redirectToCandidates() {
            window.location.href = "${pageContext.request.contextPath}/usersByCompany";
        }
   function redirectToDetails(recruitmentId) {
          window.location.href = '${pageContext.request.contextPath}/posts/detailsrecruit?recruitId=' + recruitmentId;
      }

    </script>
<script>
      function openApplyModal() {
            $('#applyModal').modal('show');
        }

function toggleCVFields() {
    var applicationType = document.getElementById('applicationType').value;
    var existingCVGroup = document.getElementById('existingCVGroup');
    var newCVGroup = document.getElementById('newCVGroup');

    if (applicationType === 'existingCV') {
        existingCVGroup.style.display = 'block';
        newCVGroup.style.display = 'none';
    } else if (applicationType === 'newCV') {
        existingCVGroup.style.display = 'none';
        newCVGroup.style.display = 'block';
    }
}


           function submitApplication() {
               // Code to handle form submission
               Swal.fire({
                   title: 'Nộp thành công!',
                   text: 'Hồ sơ của bạn đã được nộp thành công.',
                   icon: 'success',
                   confirmButtonText: 'OK'
               }).then(() => {
                   document.getElementById('applyForm').submit(); // Submit the form
               });
           }

        function submitApplication() {
            // Code to handle form submission
            $('#applyForm').submit();    // Example submit action
            $('#applyModal').modal('hide'); // Close modal after submission
        }

    function submitApplication() {
        // Code to handle form submission
        Swal.fire({
            title: 'Nộp thành công!',
            text: 'Hồ sơ của bạn đã được nộp thành công.',
            icon: 'success',
            confirmButtonText: 'OK'
        }).then(() => {
            document.getElementById('applyForm').submit(); // Submit the form
        });
    }
</script>
<!-- SweetAlert2 JS -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
