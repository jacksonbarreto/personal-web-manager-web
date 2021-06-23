package com.jacksonleonardo.unpaper.controller;

import com.jacksonleonardo.unpaper.model.DTO.OperationDTO;
import com.jacksonleonardo.unpaper.model.DTO.SearchDTO;
import com.jacksonleonardo.unpaper.model.entities.IWallet;
import com.jacksonleonardo.unpaper.model.enumerators.ERepetitionFrequency;
import com.jacksonleonardo.unpaper.model.repositories.FormOfPaymentRepository;
import com.jacksonleonardo.unpaper.model.repositories.MovementCategoryRepository;
import com.jacksonleonardo.unpaper.model.repositories.UserRepository;
import com.jacksonleonardo.unpaper.model.services.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping(value = "/operations")
public class OperationController {

    @GetMapping({"","/index.html", "/index"})
    public ModelAndView index(SearchDTO searchDTO, ModelAndView modelAndView){
        System.out.println("search GET: "+searchDTO);
        ModelAndView mv = new ModelAndView("/operations/index");
        mv.addObject("wallets", SessionService.getCurrentUser().getWallets());
        Optional<IWallet> optional = SessionService.getCurrentUser().getWallets().stream().findFirst();
        IWallet wallet = null;
        if (optional.isPresent()){
            wallet = optional.get();
        }
        if (modelAndView.isEmpty() && wallet != null){
            mv.addObject("operations", wallet.getMonthOperations());
            mv.addObject("walletName", wallet.getName());
        } else {
            mv = modelAndView;
        }



        return mv;
    }

    @PostMapping("/search")
    public ModelAndView search(SearchDTO searchDTO){
        System.out.println("search POST: "+searchDTO);
        ModelAndView mv = new ModelAndView("/operations/index");
        mv.addObject("wallets", SessionService.getCurrentUser().getWallets());
        IWallet wallet = SessionService.getCurrentUser().getWallets().stream().filter(w -> w.getID().equals(UUID.fromString(searchDTO.getWallet()))).toList().get(0);
        mv.addObject("walletName", wallet.getName());
        mv.addObject("operations", wallet.getOperationsBetween(YearMonth.from(LocalDate.parse(searchDTO.getStartDate())) , YearMonth.from(LocalDate.parse(searchDTO.getEndDate()))) );
        System.out.println(wallet.getOperationsBetween(YearMonth.from(LocalDate.parse(searchDTO.getStartDate())) , YearMonth.from(LocalDate.parse(searchDTO.getEndDate()))));
        return mv;
    }

    @GetMapping("/new")
    public ModelAndView nnew(OperationDTO operationDTO){
        System.out.println("new Get: "+operationDTO);

        ModelAndView mv = new ModelAndView("operations/new");
        Set<IWallet> wallets = SessionService.getCurrentUser().getWallets();
        mv.addObject("wallets", wallets);
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView create(@Valid OperationDTO operationDTO, BindingResult bindingResult){
        System.out.println("new post: "+operationDTO);
            ModelAndView mv = new ModelAndView("redirect:/operations/new2");
        mv.addObject("wallet", operationDTO.getWallet());
        mv.addObject("operationType", operationDTO.getOperationType());
        mv.addObject("categories", MovementCategoryRepository.getInstance().getAll());
        mv.addObject("payees", SessionService.getCurrentUser().getPayee());
            return mv;

    }

    @GetMapping("/new2")
    public ModelAndView new2(OperationDTO operationDTO){
        System.out.println("new2  GET: " + operationDTO);
        if (operationDTO.getWallet() == null || operationDTO.getOperationType() == null){
            ModelAndView mv = new ModelAndView("redirect:/operations");
            mv.addObject("msg","Reinicie a criação da operação.");
            mv.addObject("error",true);
            return mv;
        }


        ModelAndView mv = new ModelAndView("operations/new2");
        Set<IWallet> wallets = SessionService.getCurrentUser().getWallets();
        mv.addObject("wallets", wallets);
        mv.addObject("categories", MovementCategoryRepository.getInstance().getAll());

        mv.addObject("payees", SessionService.getCurrentUser().getPayee());
        mv.addObject("wallet", operationDTO.getWallet());
        mv.addObject("operationType", operationDTO.getOperationType());
        mv.addObject("operationDTO", operationDTO);
        return mv;
    }

    @PostMapping("/new2")
    public ModelAndView create2(@Valid OperationDTO operationDTO, BindingResult bindingResult){
        System.out.println(Arrays.stream(ERepetitionFrequency.values()).filter(rp -> rp.getID()<6).toList());
        System.out.println("new2 POST: " + operationDTO);
        if (bindingResult.hasFieldErrors("name")){
            return new ModelAndView("redirect:/operations/new2");
        }else {
            ModelAndView mv = new ModelAndView("redirect:/operations/new3");
            mv.addObject("categories", MovementCategoryRepository.getInstance().getAll());
            mv.addObject("payees", SessionService.getCurrentUser().getPayee());
            IWallet wallet = SessionService.getCurrentUser().getWallets().stream().filter(w -> w.getID().toString().equals(operationDTO.getWallet())).toList().get(0);
            mv.addObject("formOfPayments", wallet.getFormOfPayment());
            mv.addObject("frequencies", Arrays.stream(ERepetitionFrequency.values()).filter(rp -> rp.getID()<6).toList());
            mv.addObject("wallet", operationDTO.getWallet());
            mv.addObject("operationType", operationDTO.getOperationType());
            mv.addObject("description", operationDTO.getDescription());
            mv.addObject("name", operationDTO.getName());
            mv.addObject("payee", operationDTO.getPayee());
            mv.addObject("category", operationDTO.getCategory());
            mv.addObject("operationDTO", operationDTO);
            return mv;
        }
    }

    @GetMapping("/new3")
    public ModelAndView new3(OperationDTO operationDTO){
        System.out.println("new3 Get: "+operationDTO);

        ModelAndView mv = new ModelAndView("operations/new3");
        Set<IWallet> wallets = SessionService.getCurrentUser().getWallets();
       IWallet wallet = SessionService.getCurrentUser().getWallets().stream().filter(w -> w.getID().toString().equals(operationDTO.getWallet())).toList().get(0);
        mv.addObject("frequencies", Arrays.stream(ERepetitionFrequency.values()).filter(rp -> rp.getID()<6).toList());
        mv.addObject("wallets", wallets);
        mv.addObject("categories", MovementCategoryRepository.getInstance().getAll());
        mv.addObject("payees", SessionService.getCurrentUser().getPayee());
        mv.addObject("formOfPayments", wallet.getFormOfPayment());
        mv.addObject("operationDTO", operationDTO);
        return mv;
    }

    @PostMapping("/new3")
    public ModelAndView create3(@Valid OperationDTO operationDTO, BindingResult bindingResult){
        System.out.println("new3 post: "+operationDTO);
        if (false){
            return new ModelAndView("redirect:/operations/new3");
        }else {
            ModelAndView mv = new ModelAndView("redirect:/operations/new4");
            IWallet walletName = SessionService.getCurrentUser().getWallets().stream().filter(w -> w.getID().toString().equals(operationDTO.getWallet())).toList().get(0);
            mv.addObject("walletName", walletName.getName());
            mv.addObject("operationDTO", operationDTO);
            mv.addObject("categories", MovementCategoryRepository.getInstance().getAll());
            mv.addObject("payees", SessionService.getCurrentUser().getPayee());
            IWallet wallet = SessionService.getCurrentUser().getWallets().stream().filter(w -> w.getID().toString().equals(operationDTO.getWallet())).toList().get(0);
            mv.addObject("formOfPayments", wallet.getFormOfPayment());
            mv.addObject("frequencies", Arrays.stream(ERepetitionFrequency.values()).filter(rp -> rp.getID()<6).toList());
            mv.addObject("wallet", operationDTO.getWallet());
            mv.addObject("operationType", operationDTO.getOperationType());
            String OpName;
            if (operationDTO.getOperationType().equalsIgnoreCase("common")){
                OpName = "Normal";
            } else if(operationDTO.getOperationType().equalsIgnoreCase("installment")){
                OpName = "Parcelamento";
            }else{
                OpName = "Recorrente";
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            mv.addObject("categories", MovementCategoryRepository.getInstance().getAll());
            mv.addObject("payees", SessionService.getCurrentUser().getPayee());
            mv.addObject("operationTypeName", OpName);
            mv.addObject("formOfPayments", wallet.getFormOfPayment());
            mv.addObject("description", operationDTO.getDescription());
            mv.addObject("name", operationDTO.getName());
            mv.addObject("payee", operationDTO.getPayee());
            mv.addObject("payeeName", SessionService.getCurrentUser().getPayee().stream().filter(p -> p.getID().equals(UUID.fromString(operationDTO.getPayee()))).toList().get(0).getName());
            mv.addObject("categoryName", MovementCategoryRepository.getInstance().getAll().stream().filter(c -> c.getID().equals(UUID.fromString(operationDTO.getCategory()))).toList().get(0).getName() );
            mv.addObject("amount", operationDTO.getAmount());
            mv.addObject("dueDate", operationDTO.getDueDate());
            mv.addObject("dueDateFormat", LocalDate.parse(operationDTO.getDueDate()).format(dtf) );
            mv.addObject("formOfPayment", operationDTO.getFormOfPayment());
            mv.addObject("formOfPaymentName", FormOfPaymentRepository.defaultFormOfPaymentRepository().get(UUID.fromString(operationDTO.getFormOfPayment())).getName() );
            mv.addObject("movementType", operationDTO.getMovementType());
            mv.addObject("frequency", operationDTO.getFrequency());
            mv.addObject("frequencyName", operationDTO.getFrequenciString());
            mv.addObject("accomplishDate", operationDTO.getAccomplishDate());
            mv.addObject("category", operationDTO.getCategory());
            mv.addObject("accomplishDateFormat", LocalDate.parse(operationDTO.getAccomplishDate()).format(dtf));
            mv.addObject("installment", operationDTO.getInstallment());
            mv.addObject("wallet", operationDTO.getWallet());
            mv.addObject("operationDTO", operationDTO);
            return mv;
        }
    }

    @GetMapping("/new4")
    public ModelAndView new4(OperationDTO operationDTO){
        ModelAndView mv = new ModelAndView("operations/new4");
        System.out.println("new4 GET: "+operationDTO);
        Set<IWallet> wallets = SessionService.getCurrentUser().getWallets();
        mv.addObject("wallets", wallets);
        IWallet walletName = SessionService.getCurrentUser().getWallets().stream().filter(w -> w.getID().toString().equals(operationDTO.getWallet())).toList().get(0);
        mv.addObject("walletName", walletName.getName());
        mv.addObject("operationDTO", operationDTO);
        mv.addObject("categories", MovementCategoryRepository.getInstance().getAll());
        mv.addObject("payees", SessionService.getCurrentUser().getPayee());
        IWallet wallet = SessionService.getCurrentUser().getWallets().stream().filter(w -> w.getID().toString().equals(operationDTO.getWallet())).toList().get(0);
        mv.addObject("formOfPayments", wallet.getFormOfPayment());
        mv.addObject("frequencies", Arrays.stream(ERepetitionFrequency.values()).filter(rp -> rp.getID()<6).toList());
        mv.addObject("wallet", operationDTO.getWallet());
        mv.addObject("operationType", operationDTO.getOperationType());
        String OpName;
        if (operationDTO.getOperationType().equalsIgnoreCase("common")){
            OpName = "Normal";
        } else if(operationDTO.getOperationType().equalsIgnoreCase("installment")){
            OpName = "Parcelamento";
        }else{
            OpName = "Recorrente";
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        mv.addObject("categories", MovementCategoryRepository.getInstance().getAll());
        mv.addObject("payees", SessionService.getCurrentUser().getPayee());
        mv.addObject("operationTypeName", OpName);
        mv.addObject("formOfPayments", wallet.getFormOfPayment());
        mv.addObject("description", operationDTO.getDescription());
        mv.addObject("name", operationDTO.getName());
        mv.addObject("payee", operationDTO.getPayee());
        mv.addObject("payeeName", SessionService.getCurrentUser().getPayee().stream().filter(p -> p.getID().equals(UUID.fromString(operationDTO.getPayee()))).toList().get(0).getName());
        mv.addObject("categoryName", MovementCategoryRepository.getInstance().getAll().stream().filter(c -> c.getID().equals(UUID.fromString(operationDTO.getCategory()))).toList().get(0).getName() );
        mv.addObject("amount", operationDTO.getAmount());
        mv.addObject("dueDate", operationDTO.getDueDate());
        mv.addObject("dueDateFormat", LocalDate.parse(operationDTO.getDueDate()).format(dtf) );
        mv.addObject("formOfPayment", operationDTO.getFormOfPayment());
        mv.addObject("formOfPaymentName", FormOfPaymentRepository.defaultFormOfPaymentRepository().get(UUID.fromString(operationDTO.getFormOfPayment())).getName() );
        mv.addObject("movementType", operationDTO.getMovementType());
        mv.addObject("frequency", operationDTO.getFrequency());
        mv.addObject("frequencyName", operationDTO.getFrequenciString());
        mv.addObject("accomplishDate", operationDTO.getAccomplishDate());
        mv.addObject("category", operationDTO.getCategory());
        mv.addObject("accomplishDateFormat", LocalDate.parse(operationDTO.getAccomplishDate()).format(dtf));
        mv.addObject("installment", operationDTO.getInstallment());
        mv.addObject("wallet", operationDTO.getWallet());
        mv.addObject("operationDTO", operationDTO);
        return mv;
    }

    @PostMapping("/new4")
    public ModelAndView create4( OperationDTO operationDTO){
        System.out.println("new4 post: "+operationDTO);
        ModelAndView mv = new ModelAndView("redirect:/operations");
        IWallet wallet = SessionService.getCurrentUser().getWallets().stream().filter(w -> w.getID().toString().equals(operationDTO.getWallet())).toList().get(0);

        if (operationDTO.getOperationType().equalsIgnoreCase("common")){
            wallet.addMovement(operationDTO.toOperation());
        } else if (operationDTO.getOperationType().equalsIgnoreCase("installment")){
            wallet.addInstallment(operationDTO.toOperation(), operationDTO.getRepetitionEnum(), operationDTO.getInstallment());
        }
        SessionService.getCurrentUser().updateWallet(wallet);
        UserRepository.getInstance().update(SessionService.getCurrentUser());
        //aqui cria e salva a operaçao
        mv.addObject("msg","Operação criada com sucesso.");
        mv.addObject("error",false);
        return mv;


    }

}
