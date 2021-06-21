package com.jacksonleonardo.unpaper.model.DTO;

import com.jacksonleonardo.unpaper.model.enumerators.EOperationType;
import com.jacksonleonardo.unpaper.model.enumerators.ERepetitionFrequency;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OperationDTO {
    private String name;
    private String description;
    private BigDecimal amount;
    private LocalDate dueDate;
    private String formOfPayment;
    private String payee;
    private String category;
    private EOperationType movementType;
    private ERepetitionFrequency frequency;
    private LocalDate accomplishDate;

}
