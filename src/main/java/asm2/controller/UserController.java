        package asm2.controller;
    
        import asm2.config.RoleEditor;
        import asm2.entity.*;
        import asm2.service.CompanyService;
        import asm2.service.UserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
        import org.springframework.security.core.Authentication;
        import org.springframework.security.core.GrantedAuthority;
        import org.springframework.security.core.authority.SimpleGrantedAuthority;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.util.StringUtils;
        import org.springframework.validation.BindingResult;
        import org.springframework.web.bind.WebDataBinder;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;
        import org.springframework.web.servlet.mvc.support.RedirectAttributes;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.nio.file.Paths;
        import java.security.Principal;
        import java.util.Collections;
        import java.util.List;

        @Controller
        @RequestMapping("")
        public class UserController {
            @Autowired
            private UserService userService;

            @GetMapping("/access-denied")
            public String showAccessDenided() {
                return "access-denied";  // Tên của JSP file (home.jsp)
            }

//            @GetMapping("/profile")
//            public String profile(Model model) {
//                model.addAttribute("user", new User());
//                return "profile";
//            }

            // Mapping cho trang đăng nhập GET
            @GetMapping("/login")
            public String showLoginForm(Model model) {
                model.addAttribute("user", new User());
                return "login";
            }
            // đăng nhâp
//


            @Autowired
            private CompanyService companyService;

            @PostMapping("/authenticateTheUser")
            public String authenticateTheUser(@RequestParam("email") String email,
                                              @RequestParam("password") String password,
                                              HttpServletRequest request,
                                              Model model,
                                              RedirectAttributes redirectAttributes) {

                User user = userService.findByEmailAndPassword(email, password);

                if (user != null) {
                    // Lấy danh sách quyền (GrantedAuthority) của người dùng
                    List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getRoleName()));

                    // Tạo đối tượng authentication
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(user, password, authorities);

                    // Cập nhật SecurityContextHolder
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    // Lưu session
                    HttpSession session = request.getSession(true);
                    session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

                    // Lưu thông tin người dùng vào session
                    session.setAttribute("loggedInUser", user);

                    // Lấy thông tin công ty của người dùng nếu có
                    Company company = companyService.findById(user.getId());
                    if (company != null) {
                        session.setAttribute("loggedInCompany", company);
                    }

                    return "redirect:/asm2/home";
                } else {
                    // Trả về trang đăng nhập với thông báo lỗi
                    redirectAttributes.addFlashAttribute("error", "Email hoặc mật khẩu không đúng.");
                    return "redirect:/login";
                }
            }



            // Phương thức để khởi tạo một trình chỉnh sửa tùy chỉnh cho trường role
            @InitBinder
            public void initBinder(WebDataBinder binder) {
                binder.registerCustomEditor(Role.class, new RoleEditor());
            }
            @GetMapping("/register")
            public String register(Model model) {
                model.addAttribute("user", new User());
                return "register";
            }
    
            // Mapping for POST request to handle registration
            @PostMapping("/register")
            public String registerUser(@ModelAttribute("user") User user, BindingResult bindingResult, @RequestParam("confirmPassword") String confirmPassword, Model model) {

                // Check if passwords match
                if (!user.getPassword().equals(confirmPassword)) {
                    model.addAttribute("error", "Mật khẩu và nhập lại mật khẩu không khớp.");
                    return "register"; // Return to registration page with error message
                }
    
                // Check if user already exists
                if (userService.isUserExists(user.getEmail())) {
                    model.addAttribute("error", "Email đã tồn tại.");
                    return "register"; // Return to registration page with error message
                }
    
                // Save new user
                userService.saveUser(user);
    
                // Redirect to login page after successful registration
                return "redirect:/login";
            }
    
    
            // Mapping để đăng xuất người dùng
            @GetMapping("/logout")
            public String logout(HttpServletRequest request, HttpServletResponse response) {
                // Get authentication object
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth != null) {
                    // Perform logout
                    new SecurityContextLogoutHandler().logout(request, response, auth);
                }
                // Redirect to home page or any other page after logout
                return "redirect:/asm2/home";
            }


            // profile của công ty
            @GetMapping("/profile")
            public String showProfilePage(Model model, HttpSession session) {
                User loggedInUser = (User) session.getAttribute("loggedInUser");
                Company loggedInCompany = (Company) session.getAttribute("loggedInCompany"); // Lấy thông tin công ty từ session
                if (loggedInUser != null) {
                    model.addAttribute("user", loggedInUser);
                    if (loggedInCompany != null) {
                        model.addAttribute("company", loggedInCompany); // Thêm thông tin công ty vào model nếu có
                    } else {
                        model.addAttribute("company", new Company());
                    }
                    return "profile";
                }
                return "redirect:/login";
            }

            @GetMapping("/profileUser")
            public String profileUser(Model model, HttpSession session) {
                User loggedInUser = (User) session.getAttribute("loggedInUser");
                Company loggedInCompany = (Company) session.getAttribute("loggedInCompany"); // Lấy thông tin công ty từ session
                if (loggedInUser != null) {
                    model.addAttribute("user", loggedInUser);
                    if (loggedInCompany != null) {
                        model.addAttribute("company", loggedInCompany); // Thêm thông tin công ty vào model nếu có
                    } else {
                        model.addAttribute("company", new Company());
                    }
                    return "profile-user"; // Đổi tên view tương ứng
                }
                return "redirect:/login";
            }


            @PostMapping("/updatePersonalInfo")
            public String updatePersonalInfo(@ModelAttribute("user") User updatedUser, HttpSession session) {
                // Lấy người dùng hiện tại từ cơ sở dữ liệu
                User currentUser = userService.findById(updatedUser.getId());

                if (currentUser != null) {
                    // Cập nhật các trường từ form
                    currentUser.setFullName(updatedUser.getFullName());
                    currentUser.setAddress(updatedUser.getAddress());
                    currentUser.setPhoneNumber(updatedUser.getPhoneNumber());
                    currentUser.setDescription(updatedUser.getDescription());


                    System.out.println(updatedUser.getAddress());
                    // Lưu đối tượng đã cập nhật
                    userService.updateUser(currentUser);

                    // Cập nhật lại thông tin người dùng trong session
                    session.setAttribute("loggedInUser", currentUser);
                }

                return "redirect:/profile";
            }

            @PostMapping("/updatePersonalInfoUser")
            public String updatePersonalInfoUser(@ModelAttribute("user") User updatedUser, HttpSession session) {
                // Lấy người dùng hiện tại từ cơ sở dữ liệu
                User currentUser = userService.findById(updatedUser.getId());

                if (currentUser != null) {
                    // Cập nhật các trường từ form
                    currentUser.setFullName(updatedUser.getFullName());
                    currentUser.setAddress(updatedUser.getAddress());
                    currentUser.setPhoneNumber(updatedUser.getPhoneNumber());
                    currentUser.setDescription(updatedUser.getDescription());


                    System.out.println(updatedUser.getAddress());
                    // Lưu đối tượng đã cập nhật
                    userService.updateUser(currentUser);

                    // Cập nhật lại thông tin người dùng trong session
                    session.setAttribute("loggedInUser", currentUser);
                }

                return "redirect:/profile-user";
            }


            @GetMapping("/usersByCompany")
            public String getUsersByCompany(HttpSession session, @RequestParam(name = "page", defaultValue = "1") int page, Model model) {
                // Lấy companyId từ session
                User loggedInUser = (User) session.getAttribute("loggedInUser");
                Integer companyId = loggedInUser.getId();

                if (companyId == null) {
                    // Xử lý khi không tìm thấy companyId trong session
                    // Ví dụ: chuyển hướng hoặc thông báo lỗi
                    return "redirect:/selectCompanyPage"; // Chuyển hướng đến trang chọn công ty nếu cần thiết
                }

                // Số lượng bản ghi trên mỗi trang
                int pageSize = 5;

                // Sử dụng companyId và page để lấy danh sách người dùng từ Service
                List<UserRecruitmentInfo> users = userService.getUsersByCompany(companyId, page, pageSize);
                model.addAttribute("users", users);

                // Đưa vào model các thông tin cho phân trang
                int totalUsers = userService.countUsersByCompany(companyId);
                int totalPages = (int) Math.ceil((double) totalUsers / pageSize);
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", totalPages);

                return "usersByCompany"; // Tên của trang JSP hiển thị danh sách người dùng
            }


            private static final String UPLOAD_DIR = "uploads"; // Đường dẫn tới thư mục uploads
            @PostMapping("/uploadCV")
            public String handleFileUpload(@RequestParam("file") MultipartFile file, HttpSession session) {
                User loggedInUser = (User) session.getAttribute("loggedInUser");

                if (loggedInUser != null && !file.isEmpty()) {
                    try {
                        // Tạo đường dẫn tới thư mục uploads trong thư mục gốc của ứng dụng
                        String rootDir = System.getProperty("catalina.home"); // Thư mục chứa Tomcat
                        Path uploadPath = Paths.get(rootDir, UPLOAD_DIR);

                        // Tạo thư mục nếu chưa tồn tại
                        if (!Files.exists(uploadPath)) {
                            Files.createDirectories(uploadPath);
                        }

                        // Đổi tên file để tránh xung đột
                        String fileName = StringUtils.cleanPath(file.getOriginalFilename());


                        Path existingFilePath = uploadPath.resolve(fileName);
                        if (Files.exists(existingFilePath)) {
                            Files.delete(existingFilePath);
                        }

                        // Lưu file mới vào thư mục uploads
                        Path filePath = uploadPath.resolve(fileName);
                        Files.copy(file.getInputStream(), filePath);

                        // Kiểm tra và lưu User nếu chưa tồn tại
                        if (loggedInUser.getId() == null) {
                            userService.saveUser(loggedInUser);
                        }

                        // Tạo và lưu thông tin CV vào cơ sở dữ liệu
                        CV cv = new CV();
                        cv.setFileName(fileName);
                        loggedInUser.setCv(cv); // Gán CV cho người dùng
                        userService.saveCV(cv); // Lưu CV vào cơ sở dữ liệu

                        // Cập nhật lại thông tin người dùng trong session (nếu cần)
                        session.setAttribute("loggedInUser", loggedInUser);

                        return "redirect:/profileUser";
                    } catch (IOException e) {
                        e.printStackTrace();
                        // Xử lý khi có lỗi xảy ra trong quá trình tải lên
                    }
                }

                return "redirect:/error";
            }




        }
