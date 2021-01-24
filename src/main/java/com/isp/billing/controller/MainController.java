package com.isp.billing.controller;

import com.isp.billing.model.User;
import com.isp.billing.service.DashBoard.DBInterFaceHeaderService;
import com.isp.billing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * Created by Toufiq on 7/20/2019.
 */
@Controller
public class MainController {

    @Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    private UserService userService;




    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        return authenticationTrustResolver.isAnonymous(authentication);
    }

    @GetMapping({"", "/", "/index"})
    public String index(Map<String, Object> model) {
        model.put("message", "Welcome to AYE World.");
        return "index";
    }

    @GetMapping(value = "/login")
    public String login(Model model, String error, String logout) {
        if (isCurrentAuthenticationAnonymous()) {

            if (error != null)
                model.addAttribute("error", "Your username and password is invalid.");

            if (logout != null)
                model.addAttribute("message", "You have been logged out successfully.");

            return "login";
        } else {
            return "redirect:/Dashboard/";
        }
    }
    @GetMapping(value = "/DashBoard")
    public String getDashBoard(Model model)
    {
//        IReactiveDataDriverContextVariable iRddCv = new ReactiveDataDriverContextVariable(this.dbInterFaceHeaderService.findAll(), 1);

        return "/DashBoard/DashBoard";
    }
    @Autowired
    private DBInterFaceHeaderService dbInterFaceHeaderService;
    @GetMapping("/main")
    public String main(
//            @ModelAttribute("manus") List<UserAccessTemltDtl> userMenus,
//    @ModelAttribute("manusN") List<UserAccessTemltDtl> userAccessTemltDtls,
            Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User curUser = this.userService.findByName(auth.getName());

//        if (this.clientInfosService.findByUser(curUser)!=null)
//        {
//            return "redirect:/csi/getCsi/" + pageId+'/'+temltId;
//        }
//        userMenus = this.menuService.getUserAccessNew1(curUser);
////        setReportMenu(new UserAccessTemltDtl(),1,model);
//
        model.addAttribute("intrDb",this.dbInterFaceHeaderService.findAll());
//        return "main";
        return "/DashBoard/DashBoard";
    }

}
