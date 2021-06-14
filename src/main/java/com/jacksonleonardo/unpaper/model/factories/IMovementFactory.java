package com.jacksonleonardo.unpaper.model.factories;

import com.jacksonleonardo.unpaper.model.entities.IMovement;
import com.jacksonleonardo.unpaper.model.entities.Movement;
import com.jacksonleonardo.unpaper.model.enumerators.EOperationType;

import java.time.LocalDate;

public interface IMovementFactory {

    static IMovement createRecurrentMovement(IMovement movement, LocalDate dueDate){
        return new Movement(
                movement.getName(),
                movement.getDescription(),
                movement.getAmount(),
                dueDate,
                movement.getFormOfPayment(),
                movement.getPayee(),
                movement.getCategory(),
                movement.getAttachments(),
                movement.isCredit() ? EOperationType.CREDIT : EOperationType.DEBIT,
                movement.getRepetitionFrequency(),
                movement.getGroupID()
        );
    }
}
