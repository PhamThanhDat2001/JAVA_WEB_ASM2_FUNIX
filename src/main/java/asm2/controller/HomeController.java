package asm2.controller;

import asm2.dao.RecruitmentDAOImpl;
import asm2.entity.ApplyPost;
import asm2.service.ApplyPostService;
import asm2.service.CompanyService;
import asm2.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/asm2")
public class HomeController {

    @Autowired
    private RecruitmentService recruitmentService;
    @Autowired
    private ApplyPostService applyPostService;
    @Autowired
    private CompanyService companyService;

    @GetMapping("/home")
    public String showHome(Model model) {
        // Fetch top recruitment details
        Map<String, Object> topRecruitment = recruitmentService.getTopRecruitment();
        model.addAttribute("topRecruitment", topRecruitment);

        // Fetch top 4 categories based on number of recruitments
        List<Map<String, Object>> topCategories = recruitmentService.getTopCategories();
        model.addAttribute("topCategories", topCategories);
        int recruitmentCount = recruitmentService.countRecruitments();
        int applyPostCount = applyPostService.countApplyPosts();
        int companyCount = companyService.countCompanies();

        // đếm số lương công ty, tuyển dụng ở header
        model.addAttribute("recruitmentCount", recruitmentCount);
        model.addAttribute("applyPostCount", applyPostCount);
        model.addAttribute("companyCount", companyCount);
        model.addAttribute("applyPost", new ApplyPost());
        return "home";
    }


}
