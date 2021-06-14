package com.jacksonleonardo.unpaper.dal.converters;

import com.jacksonleonardo.unpaper.model.enumerators.EOperationType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class EOperationTypeConverter implements AttributeConverter<EOperationType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(EOperationType type) {
        return type.getID();
    }

    @Override
    public EOperationType convertToEntityAttribute(Integer integer) {
        return Stream.of(EOperationType.values())
                .filter((e) -> e.getID().equals(integer))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
