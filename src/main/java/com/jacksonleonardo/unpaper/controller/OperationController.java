package com.jacksonleonardo.unpaper.controller;

import com.jacksonleonardo.unpaper.model.entities.IWallet;
import com.jacksonleonardo.unpaper.model.repositories.FormOfPaymentRepository;
import com.jacksonleonardo.unpaper.model.services.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping(value = "/operations")
public class OperationController {

    @GetMapping({"","/index.html", "/index"})
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("/operations/index");
        mv.addObject("wallets", SessionService.getCurrentUser().getWallets());
        Optional<IWallet> optional = SessionService.getCurrentUser().getWallets().stream().findFirst();
        IWallet wallet = null;
        if (optional.isPresent()){
            wallet = optional.get();
        }
        mv.addObject("wallet", wallet);

        mv.addObject("categories", SessionService.getCurrentUser().getCategory());
        mv.addObject("formOfPayments", FormOfPaymentRepository.defaultFormOfPaymentRepository().getAll());
        mv.addObject("payees", SessionService.getCurrentUser().getPayee());
        return mv;
    }

}
