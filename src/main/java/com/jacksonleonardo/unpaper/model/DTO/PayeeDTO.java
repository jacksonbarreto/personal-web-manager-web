package com.jacksonleonardo.unpaper.model.DTO;

import com.jacksonleonardo.unpaper.model.entities.IPayee;
import com.jacksonleonardo.unpaper.model.entities.Payee;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PayeeDTO {

    @NotBlank
    @NotNull
    @Size(min=3)
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void fromPayee(IPayee payee){
        this.name = payee.getName();
    }

    public IPayee toPayee(){
        return new Payee(this.name);
    }

    @Override
    public String toString() {
        return "PayeeDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
