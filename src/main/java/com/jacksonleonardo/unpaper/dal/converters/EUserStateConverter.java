package com.jacksonleonardo.unpaper.dal.converters;

import com.jacksonleonardo.unpaper.model.enumerators.EUserState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.Serializable;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class EUserStateConverter implements AttributeConverter<EUserState, Integer>, Serializable {
    @Override
    public Integer convertToDatabaseColumn(EUserState userState) {
        return userState.getID();
    }

    @Override
    public EUserState convertToEntityAttribute(Integer integer) {
        return Stream.of(EUserState.values())
                .filter((e) -> e.getID().equals(integer))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
