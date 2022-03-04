package com.beshton.audio.controllers;

import com.beshton.audio.entities.*;
import com.beshton.audio.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.*;
import org.springframework.web.servlet.view.*;

@Controller
@RequestMapping("/webemployee")
public class WebAudioController {
    private final AudioService employeeService;
    public WebAudioController(AudioService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/viewEmployee")
    public String viewEmployee(@RequestParam(name="name", required=false, defaultValue="1") String name, Model model) {
        Audio emp = employeeService.getEmployee(name);
        model.addAttribute("Employee", emp);
        return "viewEmployee";
    }
    @GetMapping("/viewEmployees")
    public String viewEmployeess(Model model) {
        model.addAttribute("employees", employeeService.getEmployees());
        return "listEmployee";
    }

    @GetMapping("/addEmployee")
    public String addEmployeeView(Model model) {
        model.addAttribute("emp", new Audio("dummy", "duummy", "dd"));
        return "addEmployee";
    }


    @PostMapping("/addEmployee")
    public RedirectView addEmployee(@ModelAttribute("emp") Audio emp, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/webemployee/addEmployee", true);
        Audio savedEmployee = employeeService.addEmployee(emp);
        redirectAttributes.addFlashAttribute("savedEmployee", savedEmployee);
        redirectAttributes.addFlashAttribute("addEmployeeSuccess", true);
        return redirectView;
    }

}