package com.test.springbootapp.controller;

import com.test.springbootapp.controller.form.ChickenForm;
import com.test.springbootapp.entity.Chicken;
import com.test.springbootapp.service.ChickenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChickenController {
    private final ChickenService chickenService;

    @GetMapping("/index")
    public String chickenList(Model model) {
        List<Chicken> chickenList = chickenService.selectAllChicken();
        model.addAttribute("chickens", chickenList);
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(ChickenForm chickenForm) {
        return "add_chicken";
    }

    @PostMapping("/addchicken")
    public String addChicken(@Valid ChickenForm chickenForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add_chicken";
        }

        Chicken chicken = new Chicken();

        BeanUtils.copyProperties(chickenForm, chicken);
        chickenService.insertChicken(chicken);

        model.addAttribute("chickens", chickenService.selectAllChicken());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Chicken chicken = chickenService.selectChicken(id);
        log.info("Chicken => {}", chicken);
        ChickenForm chickenForm = new ChickenForm();
        BeanUtils.copyProperties(chicken, chickenForm);
        log.info("ChickenForm => {}", chickenForm);
        model.addAttribute("chickenForm", chickenForm);
        return "update_chicken";
    }

    @PostMapping("/update/{id}")
    public String updateChicken(@PathVariable("id") long id, @Valid ChickenForm chickenForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            chickenForm.setId(id);
            return "update_chicken";
        }

        log.debug("ChickenForm => {}", chickenForm);

        Chicken chicken = new Chicken();

        BeanUtils.copyProperties(chickenForm, chicken);
        chickenService.insertChicken(chicken);

        model.addAttribute("chickens", chickenService.selectAllChicken());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteChicken(@PathVariable("id") long id, Model model) {
        Chicken chicken = chickenService.selectChicken(id);
        chickenService.deleteChicken(chicken.getId());
        model.addAttribute("chickens", chickenService.selectAllChicken());
        return "index";
    }
    
}
