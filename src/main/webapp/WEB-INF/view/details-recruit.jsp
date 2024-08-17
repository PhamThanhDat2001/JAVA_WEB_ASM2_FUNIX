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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/details-recruit.css">
    <style>

    </style>
</head>
<body>
<header class="header-container">
    <div class="header-image-wrapper">
    </div>

    <div class="header-content">
        <a href="/asm2/home" style="color:white;"> Trang chủ </a> / <br> <h4> Chi tiết công việc     </h4>
    </div>

</header>

<section>
    <div class="container">
        <div class="job-details">
            <div>
                <h3 style="color:blue">${recruit.title}</h3>
                <div style="display:flex;">
                    <span style="margin-right:5px;"><i class="bi bi-briefcase-fill"></i> ${recruit.company.nameCompany} </span>
                    <span  style="margin-right:5px;"><i class="bi bi-geo-alt-fill"></i> ${recruit.address} </span>
                    <span  style="margin-right:5px; margin-bottom:50px;" ><i class="bi bi-clock-fill"></i> ${recruit.type} </span>
                </div>
                <div>
                    <span style="color:blue;"><i class="bi bi-text-left"></i> Mô tả công việc</span>
                    <span>${recruit.experience}</span>
                </div>
            </div>

            <div>

              <c:url var="saveJobUrl" value="/saveJob"/>
              <c:url var="unsaveJobUrl" value="/unsaveJob"/>

              <c:choose>
                  <c:when test="${isSaved}">
                      <form method="post" action="${pageContext.request.contextPath}${unsaveJobUrl}">
                          <input type="hidden" name="recruitId" value="${recruit.id}">
                          <button type="submit" class="btn btn-custom" >
                              Hủy lưu
                          </button>
                      </form>
                  </c:when>
                  <c:otherwise>
                      <form method="post" action="${pageContext.request.contextPath}${saveJobUrl}">
                          <input type="hidden" name="recruitId" value="${recruit.id}">
                          <button type="submit" class="btn btn-custom" >
                              <i class="bi bi-heart-fill" style="margin-bottom: 30px;"></i> Lưu
                          </button>
                      </form>
                  </c:otherwise>
              </c:choose>




                <button class="btn btn-custom" onclick="openApplyModal()">Apply Job</button>
                <div class="job-summary">
                    <p>Tóm tắt công việc</p>
                    <span>Ngày tạo:
                        <c:choose>
                            <c:when test="${empty recruit.createdAt}">
                                Không có thông tin ngày tạo
                            </c:when>
                            <c:otherwise>
                                ${recruit.createdAt}
                            </c:otherwise>
                        </c:choose>
                    </span>
                    <span>Kiểu công việc: ${recruit.type}</span>
                    <span>Loại công việc: ${recruit.category.name}</span>
                    <span>Kinh nghiệm: ${recruit.experience}</span>
                    <span>Địa chỉ: ${recruit.address}</span>
                    <span>Lương: ${recruit.salary}</span>
                    <span>Số lượng: ${recruit.quantity}</span>
                    <span>Hạn nộp: ${recruit.deadline}</span>
                </div>
                <div class="share-icons">
                    <p style="color:blue;">Share</p>
                    <i class="bi bi-linkedin"></i>
                    <i class="bi bi-facebook"></i>
                    <i class="bi bi-twitter"></i>
                    <i class="bi bi-pinterest"></i>
                </div>
            </div>
        </div>


            <h4 style="text-align:center">Những công việc liên quan</h4>
        <div class="related-jobs">
            <div class="related-job-item">
                            <p style="color:blue;">${recruit.type}</p>
                            <h4>Tuyển ${recruit.title}</h4>
                            <div style="display:flex">
                                <p><i class="bi bi-stack"></i>${recruit.company.nameCompany}</p>
                                <p style="margin-left:8px;">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-crosshair" viewBox="0 0 16 16">
                                        <path d="M8.5.5a.5.5 0 0 0-1 0v.518A7 7 0 0 0 1.018 7.5H.5a.5.5 0 0 0 0 1h.518A7 7 0 0 0 7.5 14.982v.518a.5.5 0 0 0 1 0v-.518A7 7 0 0 0 14.982 8.5h.518a.5.5 0 0 0 0-1h-.518A7 7 0 0 0 8.5 1.018zm-6.48 7A6 6 0 0 1 7.5 2.02v.48a.5.5 0 0 0 1 0v-.48a6 6 0 0 1 5.48 5.48h-.48a.5.5 0 0 0 0 1h.48a6 6 0 0 1-5.48 5.48v-.48a.5.5 0 0 0-1 0v.48A6 6 0 0 1 2.02 8.5h.48a.5.5 0 0 0 0-1zM8 10a2 2 0 1 0 0-4 2 2 0 0 0 0 4"/>
                                    </svg>${recruit.address}
                                </p>
                            </div>
                        </div>
            <div>
                <button class="btn btn-primary" onclick="openApplyModal()">Apply Job</button>
            </div>
        </div>
    </div>
</section>


<!-- Modal -->
<div class="modal fade" id="applyModal" tabindex="-1" role="dialog" aria-labelledby="applyModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="applyModalLabel">${recruit.title}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
               <form:form id="applyForm" method="post" modelAttribute="applyPost" action="${pageContext.request.contextPath}/applypost/save" enctype="multipart/form-data">
                   <!-- Add data-recruitment-id attribute to store topRecruitment.id -->
        <input type="hidden" id="recruitmentId" name="recruitmentId" value="${recruit.id}">


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
