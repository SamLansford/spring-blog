package com.example.demo.controller;

import com.example.demo.models.Ad;
import com.example.demo.models.AdCategory;
import com.example.demo.repositories.AdCategoryRepo;
import com.example.demo.repositories.AdDetailsRepo;
import com.example.demo.repositories.AdRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdController {

    // Constructor dependency injection
    private final AdRepo adsDao;
    private final AdDetailsRepo detailsDao;
    private final AdCategoryRepo catsDao;

    public AdController(AdRepo adsDao, AdDetailsRepo detailsDao, AdCategoryRepo catsDao) {
        this.adsDao = adsDao;
        this.detailsDao = detailsDao;
        this.catsDao = catsDao;
    }

    @GetMapping("/ads")
    @ResponseBody
    public List<Ad> getAllAds() {
        return adsDao.findAll();
    }

    /*
    How do we delete? Edit? Insert? Show a specific record?
     */

    @GetMapping("/ads/{id}")
    public String getOneAd(@PathVariable long id, Model vModel) {
        // get ad from dao then put in vModel
        Ad ad = adsDao.getOne(id);
        vModel.addAttribute("ad", ad);
        return "ads/show";
    }

    @GetMapping("/ads/delete")
    public String deleteAd() {
        // delete ad
        adsDao.deleteById(4L);
        return "redirect:/ads"; // redirect to "/ads"
    }

    @GetMapping("/ads/create")
    public String createAd() {
        Ad ad = new Ad(
                "Unicorn",
                "It is a very small unicorn",
                2000
        );
        adsDao.save(ad);
        return "redirect:/ads";
    }

    @GetMapping("/ads/update")
    public String updateAd() {
        Ad updatedAd = new Ad(
                101,
                "Dragon",
                "It is a dragon.",
                200
        );
        adsDao.save(updatedAd);
        return "redirect:/ads";
    }
}