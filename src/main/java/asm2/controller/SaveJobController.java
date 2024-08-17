package asm2.controller;

import asm2.entity.*;
import asm2.service.RecruitmentService;
import asm2.service.SaveJobService;
import asm2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SaveJobController {

    @Autowired
    private SaveJobService saveJobService;

    @Autowired
    private RecruitmentService recruitmentService;

    @Autowired
    private UserService userService;

    @PostMapping("/saveJob")
    public String saveJob(@RequestParam("recruitId") int recruitId, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        // Kiểm tra loggedInUser có null hay không
        if (loggedInUser == null) {
            // Chuyển hướng đến trang đăng nhập và truyền thông điệp
            redirectAttributes.addFlashAttribute("error", "Bạn cần đăng nhập để lưu công việc.");
            return "redirect:/login";
        }

        Integer userId = loggedInUser.getId();
        User user = userService.findById(userId);
        Recruitment recruitment = recruitmentService.findById(recruitId);

        SaveJob saveJob = new SaveJob();
        saveJob.setUser(user);
        saveJob.setRecruitment(recruitment);

        saveJobService.saveJob(saveJob);


        redirectAttributes.addFlashAttribute("message", "Công việc đã được lưu thành công!");
        return "redirect:/posts/detailsrecruit?recruitId=" + recruitId;
    }


    @PostMapping("/unsaveJob")
    public String unsaveJob(@RequestParam("recruitId") int recruitId, HttpSession session, RedirectAttributes redirectAttributes) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            // Chuyển hướng đến trang đăng nhập với thông báo lỗi
            redirectAttributes.addFlashAttribute("error", "Bạn cần đăng nhập để thực hiện chức năng này.");
            return "redirect:/login";
        }

        Integer userId = loggedInUser.getId();
        boolean isSaved = saveJobService.isJobSaved(userId, recruitId);
        if (isSaved) {
            // Nếu đã lưu, thực hiện xóa bản ghi SaveJob tương ứng
            saveJobService.deleteSaveJob(userId, recruitId);
            redirectAttributes.addFlashAttribute("message", "Bạn đã hủy lưu công việc thành công.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Bạn chưa lưu công việc này.");
        }

        return "redirect:/posts/detailsrecruit?recruitId=" + recruitId;
    }

    @GetMapping("/recruitment/{recruitId}/checkSaved")
    public String checkSaved(@PathVariable("recruitId") int recruitId, HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            boolean isSaved = saveJobService.isJobSaved(loggedInUser.getId(), recruitId);
            model.addAttribute("isSaved", isSaved);
        } else {
            // Handle case where user is not logged in
            model.addAttribute("isSaved", false);
        }
        return "redirect:/posts/detailsrecruit?recruitId=" + recruitId;
    }


    @GetMapping("/listcompany")
    public String listjobs(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            // Chuyển hướng đến trang đăng nhập với thông báo lỗi
            redirectAttributes.addFlashAttribute("error", "Bạn cần đăng nhập để xem chi tiết công việc.");
            return "redirect:/login";
        }
        Integer userId = loggedInUser.getId();
        List<Company> companies = saveJobService.findAllCompaniesFromSaveJob(userId);
        model.addAttribute("recruitments", companies);
        model.addAttribute("applyPost", new ApplyPost());
        return "list-company";
    }

    @GetMapping("/listjobs")
    public String listjobs(HttpSession session, Model model, RedirectAttributes redirectAttributes,
                           @RequestParam(name = "page", defaultValue = "1") int page) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            // Chuyển hướng đến trang đăng nhập với thông báo lỗi
            redirectAttributes.addFlashAttribute("error", "Bạn cần đăng nhập để xem chi tiết công việc.");
            return "redirect:/login";
        }
        Integer userId = loggedInUser.getId();

        // Số lượng bản ghi trên mỗi trang
        int pageSize = 5;

        // Tính offset (vị trí bắt đầu của trang hiện tại)
        int offset = (page - 1) * pageSize;

        // Lấy danh sách công việc đã lưu cho người dùng hiện tại, phân trang
        List<Recruitment> recruitments = saveJobService.findAllRecruitmentFromSaveJob(userId, offset, pageSize);

        // Đếm tổng số trang
        long totalRecruitments = saveJobService.countRecruitmentsByUserId(userId);
        int totalPages = (int) Math.ceil((double) totalRecruitments / pageSize);

        model.addAttribute("recruitments", recruitments);
        model.addAttribute("applyPost", new ApplyPost());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "listjob";
    }



}
