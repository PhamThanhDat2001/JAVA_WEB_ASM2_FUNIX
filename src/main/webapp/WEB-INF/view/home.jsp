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
            <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css"
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" rel="stylesheet">

    </head>
    <body>
  <header class="header-container" style="position: relative;">
      <!-- Header Image Wrapper with Overlay -->
      <div class="header-image-wrapper" style="position: relative;">
          <img class="header-img" src="../resources/images/bg_1.jpg" alt="Hình ảnh">
          <!-- Overlay with semi-transparent color -->
          <div class="header-overlay"></div>
      </div>
        <div class="header-logo">
            <h5>Word CV</h5>
        </div>
        <div class="header-content">
            <button onclick="redirectToHome()">Trang chủ</button>
            <button  onclick="redirectToJobs()">Công việc</button>
            <button  onclick="redirectToCompanys()">Công ty</button>
            <button onclick="redirectToCandidates()">Ứng cử viên</button>


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
                   </div>
                    <button onclick="redirectToRecruit()">Đăng tuyển</button>
               </sec:authorize>

                  <sec:authorize access="!isAuthenticated()">
                      <button onclick="redirectToLogin()">Đăng nhập</button>
                      <button onclick="redirectToRegister()">Đăng ký</button>
                  </sec:authorize>


        </div>
        <div class="header-text">
            <p>Tìm việc làm, Cơ hội làm việc và Nghề nghiệp</p>
            <h3>Cách dễ dàng nhất để có được công việc mới của bạn</h3>
        </div>


<div class="header-search">
  <div class="container">
     <!-- Navigation Pills -->
     <ul id="myTabs" class="nav nav-pills justify-content-center">
       <li class="nav-item">
         <a class="nav-link active" href="#" data-toggle="pill" data-target="#searchRecruit">Tìm công việc</a>
       </li>
       <li class="nav-item">
         <a class="nav-link" href="#" data-toggle="pill" data-target="#searchCompany">Tìm theo công ty</a>
       </li>
       <li class="nav-item">
         <a class="nav-link" href="#" data-toggle="pill" data-target="#searchLocation">Tìm theo địa điểm</a>
       </li>
     </ul>

     <!-- Input Area -->
   <div class="mt-4">
       <div class="tab-content">
           <div id="searchRecruit" class="tab-pane fade show active">
               <form:form method="GET" action="/posts/searchJob" modelAttribute="">
                   <div style="display:flex;">
                       <input class="form-control" name="query" placeholder="&#x1F50D; Tìm theo công việc"/>
                       <button style="min-width: 120px;" type="submit" class="btn btn-primary search-btn">Tìm kiếm</button>
                   </div>
               </form:form>
           </div>
           <div id="searchCompany" class="tab-pane fade">
               <form:form method="GET" action="/posts/searchCompany" modelAttribute="">
                   <div style="display:flex;">
                       <input class="form-control" name="query" placeholder="&#x1F50D; Tìm theo công ty"/>
                       <button style="min-width: 120px;" type="submit" class="btn btn-primary search-btn">Tìm kiếm</button>
                   </div>
               </form:form>
           </div>
           <div id="searchLocation" class="tab-pane fade">
               <form:form method="GET" action="/posts/searchLocation" modelAttribute="">
                   <div style="display:flex;">
                       <input class="form-control" name="query" placeholder="&#x1F50D; Tìm theo địa điểm"/>
                       <button style="min-width: 120px;" type="submit" class="btn btn-primary search-btn">Tìm kiếm</button>
                   </div>
               </form:form>
           </div>
       </div>
   </div>




</div>




 <div class="header-info">
         <div style="display:flex;margin-top: 10px">
             <div>
                 <i class="bi bi-person-fill"></i>
             </div>
             <div style="margin-left: 10px;">
                 <p>${applyPostCount}</p>
              <span style="display: inline-block; width: 100px;">Ứng cử viên</span>

             </div>
         </div>

         <div style="display:flex; margin-top: 10px; margin-left:50px;  margin-right:50px;">
             <div>
                 <i class="bi bi-building"></i>
             </div>
             <div style="margin-left: 10px;">
                 <p>${companyCount}</p>
                 <span style="display: inline-block; width: 100px;" >Công ty</span>
             </div>
         </div>

         <div style="display:flex;margin-top: 10px">
             <div>
                 <i class="bi bi-receipt"></i>
             </div>
             <div style="margin-left: 10px;">
                  <p>${recruitmentCount}</p>
                  <span  style="display: inline-block; width: 100px;">Tuyển dụng</span>
             </div>
         </div>
     </div>

    </header>

    <section class="container">
        <div class="heading-section-wrapper">
            <div class="col-md-7 heading-section text-center">
                <span class="subheading">Danh mục công việc</span>
                <h2 class="mb-0">Top Danh Mục</h2>
            </div>
        </div>
        <div class="categories">
            <c:forEach var="category" items="${topCategories}">
                <div class="category-item">
                    <span class="category-name">${category.name}</span>
                    <span class="category-recruitments">${category.num_recruitments} Vị trí </span>
                </div>
            </c:forEach>
        </div>
    </section>

<section class="intro">
        <div>
         <i class="bi bi-receipt"></i>
           <h5 class=" mb-3">Tìm kiếm hàng triệu việc làm</h5>
            <p>Một con sông nhỏ tên là Duden chảy qua nơi ở và nguồn cung cấp của họ.</p>
        </div>

        <div>
        <i class="bi bi-people-fill"></i>
            <h5 class=" mb-3">Dễ dàng quản lý công việc</h5>
            <p>Một con sông nhỏ tên là Duden chảy qua nơi ở và nguồn cung cấp của họ.</p>
        </div>

        <div>
       <i class="bi bi-credit-card-2-back-fill"></i>
        <h5 class=" mb-3">Top Nghề nghiệp</h5>
        <p>Một con sông nhỏ tên là Duden chảy qua nơi ở và nguồn cung cấp của họ.</p>
        </div>

        <div>
        <i class="bi bi-person-plus-fill"></i>
        <h5 class=" mb-3">Ứng viên Chuyên gia Tìm kiếm</h5>
        <p>Một con sông nhỏ tên là Duden chảy qua nơi ở và nguồn cung cấp của họ.</p>
        </div>
</section>


    <section>
        <div class="col-container" style="background-color:#F8F9FB;">
            <div class="col-md-12">
                <span class="subheading">CÔNG VIỆC ĐƯỢC NHIỀU NGƯỜI ỨNG TUYỂN</span>
                <h2 class="mb-4">Các bài đăng về việc làm nổi bật</h2>
                <div style="display:flex; padding:20px ; justify-content:space-between; align-items:center; background-color:#FFFFFF">
                    <div>
                        <p style="color:blue;">${topRecruitment.type}</p>
                        <h4>Tuyển ${topRecruitment.title}</h4>
                        <div style="display:flex">
                            <p><i class="bi bi-stack"></i>${topRecruitment.name_company}</p>
                            <p style="margin-left:8px;"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-crosshair" viewBox="0 0 16 16">
                                <path d="M8.5.5a.5.5 0 0 0-1 0v.518A7 7 0 0 0 1.018 7.5H.5a.5.5 0 0 0 0 1h.518A7 7 0 0 0 7.5 14.982v.518a.5.5 0 0 0 1 0v-.518A7 7 0 0 0 14.982 8.5h.518a.5.5 0 0 0 0-1h-.518A7 7 0 0 0 8.5 1.018zm-6.48 7A6 6 0 0 1 7.5 2.02v.48a.5.5 0 0 0 1 0v-.48a6 6 0 0 1 5.48 5.48h-.48a.5.5 0 0 0 0 1h.48a6 6 0 0 1-5.48 5.48v-.48a.5.5 0 0 0-1 0v.48A6 6 0 0 1 2.02 8.5h.48a.5.5 0 0 0 0-1zM8 10a2 2 0 1 0 0-4 2 2 0 0 0 0 4"/>
                            </svg>${topRecruitment.address}</p>
                        </div>
                    </div>
                    <div>
<button class="btn btn-primary" onclick="openApplyModal()">Apply Job</button>
<button class="btn btn-primary" onclick="redirectToDetails(${topRecruitment.id})">Chi tiết</button>
                    </div>
                </div>
            </div>
            <div class="col-md-12 heading-section">
                <h2 class="mb-4">Công ty nổi bật</h2>
                <div>
                    <div class="company-logo">
                        <img src="${topRecruitment.logo}" alt="Company Logo">
                    </div>
                    <br>
                    <b>${topRecruitment.name_company}</b>
                    <p>Số lượng ứng tuyển: ${topRecruitment.num_applications}</p>
                </div>
            </div>
        </div>
    </section>



<!-- Modal -->
<div class="modal fade" id="applyModal" tabindex="-1" role="dialog" aria-labelledby="applyModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="applyModalLabel">${topRecruitment.title}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
               <form:form id="applyForm" method="post" modelAttribute="applyPost" action="${pageContext.request.contextPath}/applypost/save" enctype="multipart/form-data">
                   <!-- Add data-recruitment-id attribute to store topRecruitment.id -->
        <input type="hidden" id="recruitmentId" name="recruitmentId" value="${topRecruitment.id}">


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
       function redirectToJobs() {
                  window.location.href = "${pageContext.request.contextPath}/listjobs";
              }
       function redirectToCompanys() {
                  window.location.href = "${pageContext.request.contextPath}/listcompany";
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
