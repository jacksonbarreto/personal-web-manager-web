package com.jacksonleonardo.unpaper.model.DTO;

import com.jacksonleonardo.unpaper.model.entities.IMovement;
import com.jacksonleonardo.unpaper.model.entities.Movement;
import com.jacksonleonardo.unpaper.model.enumerators.EOperationType;
import com.jacksonleonardo.unpaper.model.enumerators.ERepetitionFrequency;
import com.jacksonleonardo.unpaper.model.repositories.FormOfPaymentRepository;
import com.jacksonleonardo.unpaper.model.repositories.MovementCategoryRepository;
import com.jacksonleonardo.unpaper.model.repositories.PayeeRepository;
import com.jacksonleonardo.unpaper.model.valueObjects.IAttachment;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.UUID;

public class OperationDTO {
    @NotBlank
    @NotNull
    @Size(min=3)
    private String name;
    private String description;
    @NotNull
    @DecimalMin(value= "0", inclusive=false)
    private BigDecimal amount;
    @NotNull
    private String dueDate;
    @NotNull
    private String formOfPayment;
    @NotNull
    private String payee;
    @NotNull
    private String category;
    @NotNull
    private int movementType;

    private int frequency;

    private String accomplishDate;
    @Min(value = 1)
    private int installment;

    private String operationType;
    private String wallet;



    public void fromOperation(IMovement movement){
        this.name = movement.getName();
        this.description = movement.getDescription();
        this.amount = movement.getAmount();
        this.dueDate = movement.getDueDate().toString();
        this.formOfPayment = movement.getFormOfPayment().getID().toString();
        this.payee = movement.getPayee().getID().toString();
        this.category = movement.getCategory().getID().toString();
        this.movementType = movement.isCredit() ? EOperationType.CREDIT.getID() : EOperationType.DEBIT.getID();
        this.frequency = movement.getRepetitionFrequency().getID();
        this.accomplishDate = movement.getAccomplishDate().toString();
        if (movement.isCommonMovement()){
            this.operationType = "common";
        } else if(movement.isInstallment()){
            this.operationType = "installment";
        } else{
            this.operationType = "recurrent";
        }


    }

public String getFrequenciString(){
    ERepetitionFrequency rp;
    switch (this.frequency){
        case 1:
            rp = ERepetitionFrequency.WEEKLY;
            break;
        case 2:
            rp = ERepetitionFrequency.FORTNIGHTLY;
            break;
        case 3:
            rp = ERepetitionFrequency.MONTHLY;
            break;
        case 4:
            rp = ERepetitionFrequency.QUARTERLY;
            break;
        case 5:
            rp = ERepetitionFrequency.YEARLY;
            break;
        default:
            rp = ERepetitionFrequency.NONE;
    }
    return rp.getName();
}

public ERepetitionFrequency getRepetitionEnum(){
    ERepetitionFrequency rp;
    switch (this.frequency){
        case 1:
            rp = ERepetitionFrequency.WEEKLY;
            break;
        case 2:
            rp = ERepetitionFrequency.FORTNIGHTLY;
            break;
        case 3:
            rp = ERepetitionFrequency.MONTHLY;
            break;
        case 4:
            rp = ERepetitionFrequency.QUARTERLY;
            break;
        case 5:
            rp = ERepetitionFrequency.YEARLY;
            break;
        default:
            rp = ERepetitionFrequency.NONE;
    }
    return rp;
}
    public IMovement toOperation(){
        this.description =  this.description == null ? "" : this.description;
        EOperationType op = this.movementType == 1 ? EOperationType.DEBIT : EOperationType.CREDIT;
        ERepetitionFrequency rp;
        switch (this.frequency){
            case 1:
                rp = ERepetitionFrequency.WEEKLY;
                break;
            case 2:
                rp = ERepetitionFrequency.FORTNIGHTLY;
                break;
            case 3:
                rp = ERepetitionFrequency.MONTHLY;
                break;
            case 4:
                rp = ERepetitionFrequency.QUARTERLY;
                break;
            case 5:
                rp = ERepetitionFrequency.YEARLY;
                break;
            default:
                rp = ERepetitionFrequency.NONE;
        }
        return new Movement(this.name, this.description, this.amount, LocalDate.parse(this.dueDate),
                FormOfPaymentRepository.defaultFormOfPaymentRepository().get(UUID.fromString(this.formOfPayment)),
                PayeeRepository.getInstance().get(UUID.fromString(this.payee)),
                MovementCategoryRepository.getInstance().get(UUID.fromString(this.category)), new HashSet<IAttachment>(),
                op,rp, null);
    }


    @Override
    public String toString() {
        return "OperationDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", dueDate=" + dueDate +
                ", formOfPayment='" + formOfPayment + '\'' +
                ", payee='" + payee + '\'' +
                ", category='" + category + '\'' +
                ", movementType=" + movementType +
                ", frequency=" + frequency +
                ", accomplishDate=" + accomplishDate +
                ", installment=" + installment +
                ", operationType='" + operationType + '\'' +
                ", wallet='" + wallet + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getFormOfPayment() {
        return formOfPayment;
    }

    public void setFormOfPayment(String formOfPayment) {
        this.formOfPayment = formOfPayment;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMovementType() {
        return movementType;
    }

    public void setMovementType(int movementType) {
        this.movementType = movementType;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getAccomplishDate() {
        return accomplishDate;
    }

    public void setAccomplishDate(String accomplishDate) {
        this.accomplishDate = accomplishDate;
    }

    public int getInstallment() {
        return installment;
    }

    public void setInstallment(int installment) {
        this.installment = installment;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }
}
