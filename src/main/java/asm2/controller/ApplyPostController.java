package asm2.controller;

import asm2.entity.ApplyPost;
import asm2.entity.Recruitment;
import asm2.entity.User;
import asm2.service.ApplyPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/applypost")
public class ApplyPostController {

    @Autowired
    private ApplyPostService applyPostService;
    @PostMapping("/save")
    public String saveApplyPost(@ModelAttribute("applyPost") ApplyPost applyPost,
                                BindingResult bindingResult,
                                @RequestParam(value = "file", required = false) MultipartFile file,
                                HttpSession session,
                                @RequestParam("recruitmentId") Long recruitmentId,
                                Model model) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.toString());
            });
            return "err";  // Return to an error page
        }

        // Set created_at to current timestamp
        applyPost.setCreatedAt(String.valueOf(LocalDateTime.now()));

        // Create a Recruitment object and set it in applyPost
        Recruitment recruitment = new Recruitment();
        recruitment.setId(Math.toIntExact(recruitmentId));
        applyPost.setRecruitment(recruitment);

        // Get user from session and get CV fileName if no file is uploaded
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null && loggedInUser.getCv() != null && (file == null || file.isEmpty())) {
            applyPost.setNameCv(loggedInUser.getCv().getFileName());
        } else {
            // Save the file to a directory if file is uploaded
            String uploadDir = System.getProperty("catalina.home") + "/uploads/";
            Path uploadPath = Paths.get(uploadDir);

            try {
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                if (file != null && !file.isEmpty()) {
                    String fileName = file.getOriginalFilename();
                    Path filePath = uploadPath.resolve(fileName);

                    // Check if the file already exists
                    if (Files.exists(filePath)) {
                        // Delete the existing file
                        Files.delete(filePath);
                    }

                    // Copy the new file
                    Files.copy(file.getInputStream(), filePath);

                    // Set the file name in the applyPost object
                    applyPost.setNameCv(fileName);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "error";  // Return to an error page or handle the exception
            }
        }

        // Set the user for applyPost
        applyPost.setUser(loggedInUser);

        // Save applyPost to the database
        applyPostService.saveApplyPost(applyPost);

        return "redirect:/asm2/home";  // Redirect to a success page
    }




//    @PostMapping("/save")
//    public String saveApplyPost(@ModelAttribute("applyPost") ApplyPost applyPost,
//                                BindingResult bindingResult,
//                                @RequestParam(value = "file", required = false) MultipartFile file,
//                                HttpSession session,
//                                @RequestParam("recruitmentId") Long recruitmentId,
//                                Model model) {
//
//        if (bindingResult.hasErrors()) {
//            bindingResult.getAllErrors().forEach(error -> {
//                System.out.println(error.toString());
//            });
//            return "err";  // Return to an error page
//        }
//
//        // Set created_at to current timestamp
//        applyPost.setCreatedAt(String.valueOf(LocalDateTime.now()));
//
//        // Create a Recruitment object and set it in applyPost
//        Recruitment recruitment = new Recruitment();
//        recruitment.setId(Math.toIntExact(recruitmentId));
//        applyPost.setRecruitment(recruitment);
//
//        // Get user_id from session (assuming it's stored as an attribute)
//        User loggedInUser = (User) session.getAttribute("loggedInUser");
//        applyPost.setUser(loggedInUser);
//
//        if ("newCV".equals(applyPost.getApplicationType()) && file != null && !file.isEmpty()) {
//            try {
//                // Save the file to a directory
//                String uploadDir = System.getProperty("catalina.home") + "/uploads/";
//                Path uploadPath = Paths.get(uploadDir);
//
//                if (!Files.exists(uploadPath)) {
//                    Files.createDirectories(uploadPath);
//                }
//
//                String fileName = file.getOriginalFilename();
//                Path filePath = uploadPath.resolve(fileName);
//
//                // Check if the file already exists
//                if (Files.exists(filePath)) {
//                    // Delete the existing file
//                    Files.delete(filePath);
//                }
//
//                // Copy the new file
//                Files.copy(file.getInputStream(), filePath);
//
//                // Set the file name in the applyPost object
//                applyPost.setNameCv(fileName);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return "error";  // Return to an error page
//            }
//        }
//
//        // Save applyPost to the database
//        applyPostService.saveApplyPost(applyPost);
//
//        return "redirect:/asm2/home";  // Redirect to a success page
//    }


}
