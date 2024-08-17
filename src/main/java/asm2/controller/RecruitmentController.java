package asm2.controller;

import asm2.entity.ApplyPost;
import asm2.entity.Recruitment;
import asm2.entity.User;
import asm2.service.RecruitmentService;
import asm2.service.SaveJobService;
import asm2.service.UserService;
import org.hibernate.TransientObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/posts")
public class RecruitmentController {
    @Autowired
    private RecruitmentService recruitmentService;

@GetMapping("/list")
public String showPosts(Model model, HttpSession session,
                        @RequestParam(name = "page", defaultValue = "1") int page) {
    // companyId lấy từ session
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Integer companyId = loggedInUser.getId();

    // Calculate pagination parameters
    int pageSize = 5;
    int start = (page - 1) * pageSize;

    // Retrieve paginated list of recruitments
    List<Recruitment> recruitments = recruitmentService.findPaginatedRecruitments(companyId, start, pageSize);

    // Count total number of recruitments for pagination
    int totalRecruitments = recruitmentService.countByCompanyId(companyId);

    // Calculate pagination details
    int totalPages = (int) Math.ceil((double) totalRecruitments / pageSize);

    model.addAttribute("recruitments", recruitments);
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", totalPages);


    return "posts"; // Đường dẫn tới file JSP (ví dụ: posts.jsp)
}

    @GetMapping("/recruit")
    public String recruit(Model model, HttpSession session) {

        model.addAttribute("recruit",new Recruitment());
        return "recruit";
    }
    @GetMapping("/showrecruit")
    public String showrecruit(Model model, HttpSession session) {

        model.addAttribute("recruit",new Recruitment());
        return "recruit";
    }
//    @GetMapping("/detailsrecruit")
//    public String detailsrecruit(@RequestParam("recruitId")int theId ,Model model, HttpSession session) {
//      Recruitment recruitment=  recruitmentService.findById(theId);
//        model.addAttribute("recruit",recruitment);
//        //thêm applyPostd để mở modal
//        model.addAttribute("applyPost",new ApplyPost());
//        return "details-recruit";
//    }

    @Autowired
    private SaveJobService saveJobService;
    @Autowired
    private UserService userService;
    @GetMapping("/detailsrecruit")
    public String detailsrecruit(@RequestParam("recruitId") int theId, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Recruitment recruitment = recruitmentService.findById(theId);
        model.addAttribute("recruit", recruitment);

        // Thêm applyPost để mở modal
        model.addAttribute("applyPost", new ApplyPost());

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            // Chuyển hướng đến trang đăng nhập với thông báo lỗi
            redirectAttributes.addFlashAttribute("error", "Bạn cần đăng nhập để xem chi tiết công việc.");
            return "redirect:/login";
        }

        Integer userId = loggedInUser.getId();
        boolean isSaved = saveJobService.isJobSaved(userId, theId);
        model.addAttribute("isSaved", isSaved);

        return "details-recruit";
    }

    @GetMapping("/showupdaterecruit")
    public String showupdaterecruit(@RequestParam("recruitId") int theId, Model theModel, HttpSession session) {

        // Lấy thông tin Recruitment từ theId
        Recruitment recruitment = recruitmentService.findById(theId);

        // Kiểm tra xem recruitment có null không
        if (recruitment == null) {
            // Xử lý khi recruitment không tìm thấy
            // Ví dụ: chuyển hướng đến trang lỗi hoặc trang chính
            System.out.println("vao day2");
            return "redirect:/error";
        }

        // Thêm thông tin recruitment vào model
        theModel.addAttribute("recruit", recruitment);

        // Trả về tên trang JSP
        return "update-recruit";
    }


    @PostMapping("/uprecruit")
    public String uprecruit(@ModelAttribute("recruit") Recruitment recruitment,
                            Model model,
                            RedirectAttributes redirectAttributes,
                            HttpSession session) {
        try {
            recruitmentService.saveRecruit(recruitment);

            return "redirect:/posts/list";
        } catch (IllegalStateException e) {
            // Đặt thông báo lỗi vào redirectAttributes
            redirectAttributes.addFlashAttribute("errorMessage", "Bạn chưa thiết lập công ty, hãy vào hồ sơ để thiết lập.");
            return "redirect:/error";
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác nếu cần
            redirectAttributes.addFlashAttribute("errorMessage", "Đã xảy ra lỗi khi xử lý yêu cầu.");
            return "redirect:/error";
        }
    }

    // Xóa user
    @GetMapping("/delete")
    public String deleteUser(@RequestParam("recruitId") int theId ) {
        recruitmentService.deleteRecruitment(theId);
        return "redirect:/posts/list";
    }


    @GetMapping("/searchJob")
    public String searchJob(@RequestParam("query") String query, Model model) {
        List<Recruitment> jobs = recruitmentService.searchJobs(query);
        model.addAttribute("jobs", jobs);
        model.addAttribute("applyPost", new ApplyPost());
        return "result-search-job"; // Tên của trang JSP hiển thị kết quả tìm kiếm
    }
    @GetMapping("/searchCompany")
    public String searchCompany(@RequestParam("query") String query, Model model) {
        List<Recruitment> jobs = recruitmentService.searchJobsByCompanyName(query);
        model.addAttribute("jobs", jobs);
        model.addAttribute("applyPost", new ApplyPost());
        return "result-search-job"; // Tên của trang JSP hiển thị kết quả tìm kiếm
    }

    @GetMapping("/searchLocation")
    public String searchLocation(@RequestParam("query") String query, Model model) {
        List<Recruitment> jobs = recruitmentService.searchJobsByLocation(query);
        model.addAttribute("jobs", jobs);
        model.addAttribute("applyPost", new ApplyPost());
        return "result-search-job"; // Tên của trang JSP hiển thị kết quả tìm kiếm
    }



    @PostMapping("/followJob")
    @ResponseBody
    public Map<String, Boolean> followJob(@RequestParam("recruitId") Long recruitId) {
        boolean followed = recruitmentService.toggleFollowJob(recruitId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("followed", followed);
        return response;
    }

    @PostMapping("/followCompany")
    @ResponseBody
    public Map<String, Boolean> followCompany(@RequestParam("companyId") Long companyId) {
        boolean followed = recruitmentService.toggleFollowCompany(companyId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("followed", followed);
        return response;
    }
}
