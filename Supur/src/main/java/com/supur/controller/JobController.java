package com.supur.controller;

import com.supur.entity.Category;
import com.supur.entity.Job;
import com.supur.entity.User;
import com.supur.service.JobService;
import com.supur.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.List;

/**
 * İş ilanları işlemlerini yöneten kontrolör sınıfı.
 */
@Controller
public class JobController {
    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    /**
     * Anasayfayı görüntüler ve kategorileri model nesnesine ekler.
     *
     * @param model   Model nesnesi.
     * @param session HTTP oturumu.
     * @return Anasayfa.
     */
    @GetMapping("/")
    public String showHomePage(Model model, HttpSession session) {
        model.addAttribute("job", new Job());
        model.addAttribute("categories", Category.values());
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("username", user.getUsername());
            List<Job> jobs = jobService.getJobsByUser(user);
            model.addAttribute("jobs", jobs);
        } else {
            model.addAttribute("jobs", jobService.findByCategory(Category.TUMU));
        }
        return "index";
    }


    /**
     * Belirli bir kategorideki iş ilanlarını görüntüler.
     *
     * @param category Kategori.
     * @param model    Model nesnesi.
     * @return Kategori sayfası.
     */
    @GetMapping("/category/{category}")
    public String viewJobsByCategory(@PathVariable Category category, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("username", user.getUsername());
        }
        List<Job> jobs = jobService.findByCategory(category);
        model.addAttribute("jobs", jobs);
        return "category";
    }

    @GetMapping("/job/{id}")
    public String viewJobDetails(@PathVariable Long id, Model model) {
        Job job = jobService.findById(id);
        if (job != null) {
            model.addAttribute("job", job);
            return "job-details";
        }
        return "redirect:/"; // İlan bulunamazsa ana sayfaya yönlendir.
    }


    /**
     * İlan yayınlama sayfasını gösterir.
     *
     * @param model model
     * @return ilan yayınlama sayfası görünümü
     */
    @GetMapping("/postJob")
    public String showPostJobPage(Model model) {
        model.addAttribute("job", new Job());
        model.addAttribute("categories", Category.values());
        return "postJob";
    }

    /**
     * Yeni bir iş ilanı oluşturur.
     *
     * @param job     iş ilanı
     * @param session oturum
     * @return yönlendirme anasayfa
     */
    @PostMapping("/postJob")
    public String postJob(@ModelAttribute Job job, HttpSession session) {
        String username = (String) session.getAttribute("username");
        User user = userService.findByUsername(username);
        job.setUser(user);
        jobService.saveJob(job);
        return "redirect:/";
    }
}
