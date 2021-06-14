package com.jacksonleonardo.unpaper.model.builders;

import com.jacksonleonardo.unpaper.model.entities.IFormOfPayment;
import com.jacksonleonardo.unpaper.model.entities.IMovement;
import com.jacksonleonardo.unpaper.model.entities.IMovementCategory;
import com.jacksonleonardo.unpaper.model.entities.IPayee;
import com.jacksonleonardo.unpaper.model.enumerators.EOperationType;
import com.jacksonleonardo.unpaper.model.enumerators.ERepetitionFrequency;
import com.jacksonleonardo.unpaper.model.valueObjects.IAttachment;

import java.time.LocalDate;
import java.util.UUID;

public interface IMovementBuilder {


    static IMovementBuilder makeMovement(String name, String amount, String dueDate, IFormOfPayment formOfPayment,
                                         IPayee payee, IMovementCategory category, EOperationType movementType) {
        return new MovementBuilder(name, amount, dueDate, formOfPayment, payee, category, movementType);
    }

    static IMovementBuilder makeMovement(String name, String amount, LocalDate dueDate, IFormOfPayment formOfPayment,
                                         IPayee payee, IMovementCategory category, EOperationType movementType) {
        return new MovementBuilder(name, amount, dueDate.getDayOfMonth() + "-" + dueDate.getMonthValue() + "-" + dueDate.getYear(), formOfPayment, payee, category, movementType);
    }

    IMovementBuilder addDescription(String description);

    IMovementBuilder addAttachments(IAttachment attachment);

    IMovementBuilder addRepetitionFrequency(ERepetitionFrequency frequency);

    IMovementBuilder addGroupID(UUID groupID);

    IMovement build();
}
