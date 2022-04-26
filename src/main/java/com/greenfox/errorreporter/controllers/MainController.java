package com.greenfox.errorreporter.controllers;

import com.greenfox.errorreporter.models.Report;
import com.greenfox.errorreporter.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class MainController {

    private ReportService reportService;

    @Autowired
    public MainController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(value = {"/"})
    public String getIndex(Model model, @RequestParam (required = false) String error) {
        if (reportService.showAll().isEmpty() || error != null) {
            model.addAttribute("error", "ERROR!");
        }

        model.addAttribute("report", reportService.showAll());
        model.addAttribute("reportDistinct", reportService.findDistinctReporter());

        return "index";
    }

    @GetMapping(value = {"/list"})
    public String getList(Model model) {
        model.addAttribute("reports", reportService.showAll());

        return "list";
    }

    @PostMapping(value = {"/report"})
    public String postReport(@RequestParam String reporter, @RequestParam String manufacturer, @RequestParam String serialNumber, @RequestParam String description) {
        if (reporter.isEmpty() || manufacturer.isEmpty() || description.isEmpty() || serialNumber.isEmpty()) {
            return "redirect:/?error=validation-failed";
        }

        else {
            reportService.addNewReport(new Report(reporter, manufacturer, serialNumber, description));
            return "redirect:/";
        }
    }
}
