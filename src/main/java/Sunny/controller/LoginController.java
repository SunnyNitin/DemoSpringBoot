package Sunny.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @RequestMapping("/Login")
    public String loginPage(){
        System.out.println("login page requested.");
        return "Login";
    }

    @RequestMapping("/loginCheck")
    public ModelAndView loginCheck(
            HttpServletRequest request,
            @RequestParam String username,
            @RequestParam ("password") String pass){
        ModelAndView modelAndView=new ModelAndView();
        if (username.equals("Sunny") && pass.equals("742828")){
            HttpSession httpSession=request.getSession();
            modelAndView.setViewName("Dashboard");
            modelAndView.addObject("user",username);
            httpSession.setAttribute("user",username);
            return modelAndView;
    }
        modelAndView.setViewName("Login");
        modelAndView.addObject("loginError","Invalid login with user '"+username+"'");
        return modelAndView;
}

@RequestMapping("/Dashboard")
public ModelAndView Dashboard(HttpServletRequest request){
    HttpSession httpSession= request.getSession(false);
    ModelAndView modelAndView=new ModelAndView();

    if (httpSession!=null && httpSession.getAttribute("user")!=null){
        String username= (String) httpSession.getAttribute("user");
        modelAndView.addObject("user",username);
        modelAndView.setViewName("Dashboard");
        return modelAndView;
    }
    modelAndView.setViewName("Login");
    modelAndView.addObject("loginError","Invalid session.");
    return modelAndView;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request){
        HttpSession httpSession= request.getSession(false);
        ModelAndView modelAndView=new ModelAndView();

        if (httpSession!=null){
            httpSession.invalidate();
        }
        modelAndView.setViewName("Login");
        modelAndView.addObject("logoutMsg","Successfully logged out.");
        return modelAndView;
    }
}