package com.jacksonleonardo.unpaper.controller;

import com.jacksonleonardo.unpaper.model.DTO.PayeeDTO;
import com.jacksonleonardo.unpaper.model.entities.IPayee;
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
@RequestMapping(value = "/payees")
public class PayeeController {

    @GetMapping({"","/index.html"})
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("payees/index");
        mv.addObject("payees", SessionService.getCurrentUser().getPayee());
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(PayeeDTO payeeDTO){
        ModelAndView mv = new ModelAndView("payees/new");
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView create(@Valid PayeeDTO payeeDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ModelAndView("/payees/new");
        }else {
            SessionService.getCurrentUser().addPayee(payeeDTO.toPayee());
            UserRepository.getInstance().update(SessionService.getCurrentUser());
            ModelAndView mv = new ModelAndView("redirect:/payees");
            mv.addObject("msg","Beneficiário criado com sucesso.");
            mv.addObject("error",false);
            return mv;
        }
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable String id, PayeeDTO payeeDTO){

        Optional<IPayee> optional = SessionService.getCurrentUser().getPayee().stream().filter(w -> w.getID().equals(UUID.fromString(id))).findFirst();
        if (optional.isPresent()){
            ModelAndView mv = new ModelAndView("payees/edit");
            IPayee payee = optional.get();
            payeeDTO.fromPayee(payee);
            mv.addObject("payee",payeeDTO);
            mv.addObject("payeeId", payee.getID().toString());
            mv.addObject("msg","Beneficiário alterado com sucesso.");
            mv.addObject("error",false);
            return mv;
        }else {
            ModelAndView mv = new ModelAndView("redirect:/payees");
            mv.addObject("msg","Beneficiário não encontrado.");
            mv.addObject("error",true);
            return mv;
        }
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable String id, @Valid PayeeDTO payeeDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ModelAndView("/payees/edit");
        }else {
            ModelAndView mv = new ModelAndView("redirect:/payees");
            Optional<IPayee> optional = SessionService.getCurrentUser().getPayee().stream().filter(w -> w.getID().equals(UUID.fromString(id))).findFirst();
            if (optional.isPresent()){

                IPayee payee = optional.get();
                payee.updateName(payeeDTO.getName());
                SessionService.getCurrentUser().updatePayee(payee);
                UserRepository.getInstance().update(SessionService.getCurrentUser());
                mv.addObject("msg","Beneficiário alterado com sucesso.");
                mv.addObject("error",false);
                return mv;
            }else {
                mv.addObject("msg","Beneficiário não encontrado.");
                mv.addObject("error",true);
                return mv;
            }
        }
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable String id) {
        ModelAndView mv = new ModelAndView("redirect:/payees");
        Optional<IPayee> optional;
        UUID uuid = null;
        try{
            uuid = UUID.fromString(id);
            UUID finalUuid = uuid;
            optional = SessionService.getCurrentUser().getPayee().stream().filter(w -> w.getID().equals(UUID.fromString(id))).findFirst();
        } catch (Exception e){
            mv.addObject("msg","Beneficiário não encontrado.");
            mv.addObject("error",true);
            return mv;
        }

        if (optional.isPresent()){
            IPayee payee  = optional.get();
            SessionService.getCurrentUser().removePayee(payee);
            UserRepository.getInstance().update(SessionService.getCurrentUser());
            mv.addObject("msg","Beneficiário removido com sucesso.");
            mv.addObject("error",false);
        }else{
            mv.addObject("msg","Beneficiário não encontrado.");
            mv.addObject("error",true);
        }
        return mv;
    }

}
