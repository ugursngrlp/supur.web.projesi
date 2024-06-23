package com.supur.controller;

import com.supur.entity.User;
import com.supur.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.security.Principal;

/**
 * Kullanıcı işlemlerini yöneten kontrolör sınıfı.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Kayıt olma formunu görüntüler.
     * @param model Model nesnesi.
     * @return Kayıt olma sayfası.
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Kullanıcıyı kaydeder.
     * @param user Kaydedilecek kullanıcı.
     * @return Yönlendirme giriş sayfasına.
     */
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/login";
    }

    /**
     * Giriş yapma formunu görüntüler.
     * @return Giriş yapma sayfası.
     */
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    /**
     * Kullanıcının giriş yapmasını sağlar.
     * @param username Kullanıcı adı.
     * @param password Şifre.
     * @param request HTTP isteği.
     * @return Yönlendirme ana sayfaya.
     */
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        User user = userService.findByUsernameAndPassword(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "redirect:/";
        }
        return "redirect:/login?error";
    }

    /**
     * Kullanıcı profilini görüntüler.
     * @param model Model nesnesi.
     * @param session HTTP oturumu.
     * @return Profil sayfası.
     */
    @GetMapping("/profile")
    public String showProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("username", user.getUsername());
            return "profile";
        }
        return "redirect:/login";
    }

    /**
     * Kullanıcıyı oturumdan çıkarır.
     * @param session HTTP oturumu.
     * @return Yönlendirme giriş sayfasına.
     */
    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
