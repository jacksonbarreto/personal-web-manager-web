package com.jacksonleonardo.unpaper.model.DTO;

import com.jacksonleonardo.unpaper.model.entities.IFormOfPayment;
import com.jacksonleonardo.unpaper.model.entities.IWallet;
import com.jacksonleonardo.unpaper.model.entities.Payee;
import com.jacksonleonardo.unpaper.model.entities.Wallet;
import com.jacksonleonardo.unpaper.model.repositories.FormOfPaymentRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

public class WalletDTO {
    @NotBlank
    @NotNull
    private String name;
    private String description;
    @Size(min=3, max=3)
    private String currency;

    @NotNull
    @NotEmpty
    private List<String> paymentChecked;

    public WalletDTO(){}



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<String> getPaymentChecked() {
        return paymentChecked;
    }
    public void setPaymentChecked(List<String> paymentChecked) {
        this.paymentChecked = paymentChecked;
    }


    @Override
    public String toString() {
        return "WalletDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", currency='" + currency + '\'' +
                ", paymentChecked=" + paymentChecked +
                '}';
    }

    public IWallet toWallet(){
        List<IFormOfPayment> formOfPayments = new ArrayList<>();
        if (this.paymentChecked != null){
            for (String formOfPayment : this.paymentChecked){
              formOfPayments.add(FormOfPaymentRepository.defaultFormOfPaymentRepository().get(UUID.fromString(formOfPayment)));
            }
        }
        return new Wallet(this.name, this.description, Currency.getInstance(this.currency), formOfPayments,new Payee(this.name));

    }

    public void fromWallet(IWallet wallet){
        this.currency = wallet.getCurrency().getCurrencyCode();
        this.description = wallet.getDescription();
        this.name = wallet.getName();
        this.paymentChecked = new ArrayList<>();
        for (IFormOfPayment payment : wallet.getFormOfPayment()){
            this.paymentChecked.add(payment.getID().toString());
        }
    }



}
