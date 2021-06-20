package com.jacksonleonardo.unpaper.controller;

import com.jacksonleonardo.unpaper.model.DTO.WalletDTO;
import com.jacksonleonardo.unpaper.model.entities.IWallet;
import com.jacksonleonardo.unpaper.model.repositories.FormOfPaymentRepository;
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
import java.util.*;

@Controller
@RequestMapping(value = "/wallets")
public class walletController {

    @GetMapping({"","/index.html"})
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("wallets/index");
        mv.addObject("wallets", SessionService.getCurrentUser().getWallets());
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(WalletDTO walletDTO){
        ModelAndView mv = new ModelAndView("wallets/new");
        mv.addObject("formOfPayments", FormOfPaymentRepository.defaultFormOfPaymentRepository().getAll());
        mv.addObject("currencies", getAllCurrencies());
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView create(@Valid WalletDTO walletDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("/wallets/new");
            mv.addObject("formOfPayments", FormOfPaymentRepository.defaultFormOfPaymentRepository().getAll());
            mv.addObject("currencies", getAllCurrencies());
            return  mv;
        }else {
            SessionService.getCurrentUser().addWallet(walletDTO.toWallet());
            UserRepository.getInstance().update(SessionService.getCurrentUser());
            return new ModelAndView("redirect:/wallets");
        }
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable String id){
        ModelAndView mv = new ModelAndView("wallets/show");
        Optional<IWallet> optional = SessionService.getCurrentUser().getWallets().stream().filter(w -> w.getID().equals(UUID.fromString(id))).findFirst();
        if (optional.isPresent()){
            IWallet wallet = optional.get();
            mv.addObject("wallet",wallet);
            mv.addObject("walletId", wallet.getID().toString());
            return mv;
        }else {
            return new ModelAndView("redirect:/wallets");
        }

    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        return "wallets/" + id + "/edit";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable String id, WalletDTO walletDTO){
        ModelAndView mv = new ModelAndView("wallets/edit");
        Optional<IWallet> optional = SessionService.getCurrentUser().getWallets().stream().filter(w -> w.getID().equals(UUID.fromString(id))).findFirst();
        if (optional.isPresent()){
            IWallet wallet = optional.get();
            walletDTO.fromWallet(wallet);
            mv.addObject("wallet",walletDTO);
            mv.addObject("walletId", wallet.getID().toString());
            mv.addObject("formOfPayments", FormOfPaymentRepository.defaultFormOfPaymentRepository().getAll());
            mv.addObject("currencies", getAllCurrencies());
            return mv;
        }else {
            return new ModelAndView("redirect:/wallets");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable String id, @Valid WalletDTO walletDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            ModelAndView mv = new ModelAndView("/wallets/edit");
            mv.addObject("formOfPayments", FormOfPaymentRepository.defaultFormOfPaymentRepository().getAll());
            mv.addObject("currencies", getAllCurrencies());
            return  mv;
        }else {

            Optional<IWallet> optional = SessionService.getCurrentUser().getWallets().stream().filter(w -> w.getID().equals(UUID.fromString(id))).findFirst();
            if (optional.isPresent()){
                IWallet wallet = optional.get();
                wallet.updateCurrency(Currency.getInstance(walletDTO.getCurrency()));
                wallet.updateName(walletDTO.getName());
                wallet.updateDescription(walletDTO.getDescription());
                //fazer a questao de att os meios de pagamento
                SessionService.getCurrentUser().updateWallet(wallet);
                UserRepository.getInstance().update(SessionService.getCurrentUser());
                return new ModelAndView("redirect:/wallets");
            }else {
                return new ModelAndView("redirect:/wallets");
            }

        }
    }


    public static Set<Currency> getAllCurrencies()
    {
        Set<Currency> toret = new HashSet<Currency>();
        Locale[] locs = Locale.getAvailableLocales();

        for(Locale loc : locs) {
            try {
                Currency currency = Currency.getInstance( loc );

                if ( currency != null ) {
                    toret.add( currency );
                }
            } catch(Exception exc)
            {
                // Locale not found
            }
        }

        return toret;
    }
}
