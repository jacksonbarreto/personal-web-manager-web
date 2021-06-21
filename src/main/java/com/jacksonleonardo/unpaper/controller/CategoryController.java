package com.jacksonleonardo.unpaper.controller;

import com.jacksonleonardo.unpaper.model.DTO.CategoryDTO;
import com.jacksonleonardo.unpaper.model.entities.IMovementCategory;
import com.jacksonleonardo.unpaper.model.repositories.UserRepository;
import com.jacksonleonardo.unpaper.model.services.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping(value = "/categories")
public class CategoryController {

    @GetMapping({"","/index.html"})
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("categories/index");
        mv.addObject("categories", SessionService.getCurrentUser().getCategory());
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(CategoryDTO categoryDTO){
        ModelAndView mv = new ModelAndView("categories/new");
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView create(@Valid CategoryDTO categoryDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ModelAndView("/categories/new");
        }else {
            SessionService.getCurrentUser().addCategory(categoryDTO.toMovementCategory());
            UserRepository.getInstance().update(SessionService.getCurrentUser());
            ModelAndView mv = new ModelAndView("redirect:/categories");
            mv.addObject("msg","Categoria criada com sucesso.");
            mv.addObject("error",false);
            return mv;
        }
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable String id, CategoryDTO categoryDTO){

        Optional<IMovementCategory> optional = SessionService.getCurrentUser().getCategory().stream().filter(w -> w.getID().equals(UUID.fromString(id))).findFirst();
        if (optional.isPresent()){
            ModelAndView mv = new ModelAndView("categories/edit");
            IMovementCategory movementCategory = optional.get();
            categoryDTO.fromMovementCategory(movementCategory);
            mv.addObject("category",categoryDTO);
            mv.addObject("categoryId", movementCategory.getID().toString());
            mv.addObject("msg","Categoria alterada com sucesso.");
            mv.addObject("error",false);
            return mv;
        }else {
            ModelAndView mv = new ModelAndView("redirect:/categories");
            mv.addObject("msg","Categoria não encontrada.");
            mv.addObject("error",false);
            return mv;
        }
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable String id, @Valid CategoryDTO categoryDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ModelAndView("/categories/edit");
        }else {
            ModelAndView mv = new ModelAndView("redirect:/categories");
            Optional<IMovementCategory> optional = SessionService.getCurrentUser().getCategory().stream().filter(w -> w.getID().equals(UUID.fromString(id))).findFirst();
            if (optional.isPresent()){

                IMovementCategory movementCategory = optional.get();
                movementCategory.updateName(categoryDTO.getName());
                SessionService.getCurrentUser().updateCategory(movementCategory);
                UserRepository.getInstance().update(SessionService.getCurrentUser());
                mv.addObject("msg","Categoria alterada com sucesso.");
                mv.addObject("error",false);
                return mv;
            }else {
                mv.addObject("msg","Categoria não encontrada.");
                mv.addObject("error",false);
                return mv;
            }

        }
    }


    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable String id) {
        ModelAndView mv = new ModelAndView("redirect:/categories");
        Optional<IMovementCategory> optional;
        UUID uuid = null;
        try{
            uuid = UUID.fromString(id);
            UUID finalUuid = uuid;
            optional = SessionService.getCurrentUser().getCategory().stream().filter(w -> w.getID().equals(UUID.fromString(id))).findFirst();
        } catch (Exception e){
            mv.addObject("msg","Categoria não encontrada.");
            mv.addObject("error",true);
            return mv;
        }

        if (optional.isPresent()){
            IMovementCategory movementCategory  = optional.get();
            SessionService.getCurrentUser().removeCategory(movementCategory);
            UserRepository.getInstance().update(SessionService.getCurrentUser());
            mv.addObject("msg","Categoria removida com sucesso.");
            mv.addObject("error",false);
        }else{
            mv.addObject("msg","Carteira não encontrada.");
            mv.addObject("error",true);
        }
        return mv;
    }


}
