package asm2.controller;

import asm2.entity.Company;
import asm2.entity.Recruitment;
import asm2.entity.User;
import asm2.service.CompanyService;
import asm2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

//    @PostMapping("/updateCompanyInfo")
//    public String updateCompanyInfo(@ModelAttribute("company") Company updatedCompany, HttpSession session) {
//        // Xử lý cập nhật thông tin công ty ở đây
////        User loggedInUser = (User) session.getAttribute("loggedInUser");
////        Integer companyId = loggedInUser.getId();
//        Company currentUser = companyService.findById(updatedCompany.getId());
//
//        System.out.println("id"+updatedCompany.getId());
//        if (currentUser != null) {
//            // Cập nhật các trường từ form
//            currentUser.setNameCompany(updatedCompany.getNameCompany());
//            currentUser.setAddress(updatedCompany.getAddress());
//            currentUser.setPhoneNumber(updatedCompany.getPhoneNumber());
//            currentUser.setDescription(updatedCompany.getDescription());
//
//            // Lưu đối tượng đã cập nhật
//            companyService.updateCompany(currentUser);
//
//            // Cập nhật lại thông tin công ty trong session
//            session.setAttribute("loggedInCompany", currentUser);
//        }
//
//        return "redirect:/profile";
//    }
@PostMapping("/updateCompanyInfo")
public String updateCompanyInfo(@ModelAttribute("company") Company updatedCompany, HttpSession session) {
    // Kiểm tra nếu updatedCompany.getId() là null
    if (updatedCompany.getId() == null) {
        // Xử lý khi updatedCompany chưa có id (chưa được lưu vào CSDL)
        // Ví dụ: thêm mới công ty
        // Gọi service để lưu công ty mới và nhận lại đối tượng có id đã được cập nhật
        updatedCompany = companyService.saveCompany(updatedCompany); // Ví dụ method saveCompany trả về updatedCompany đã được lưu vào CSDL và có id mới

        // Lưu thông tin công ty vào session
        session.setAttribute("loggedInCompany", updatedCompany);
    } else {
        // Nếu updatedCompany.getId() không null, tức là công ty đã tồn tại trong CSDL
        // Lấy thông tin công ty từ CSDL dựa trên id
        Company currentUser = companyService.findById(updatedCompany.getId());

        if (currentUser != null) {
            // Cập nhật các trường từ form
            currentUser.setNameCompany(updatedCompany.getNameCompany());
            currentUser.setAddress(updatedCompany.getAddress());
            currentUser.setPhoneNumber(updatedCompany.getPhoneNumber());
            currentUser.setDescription(updatedCompany.getDescription());

            // Lưu đối tượng đã cập nhật
            companyService.updateCompany(currentUser);

            // Cập nhật lại thông tin công ty trong session
            session.setAttribute("loggedInCompany", currentUser);
        } else {
            // Xử lý khi không tìm thấy công ty trong CSDL
            // Ví dụ: trả về trang lỗi hoặc thông báo lỗi
            return "redirect:/error";
        }
    }

    // Sau khi cập nhật thành công, chuyển hướng về trang profile
    return "redirect:/profile";
}
    @GetMapping("/error")
    public String error(Model model) {

        return "err";
    }

}
