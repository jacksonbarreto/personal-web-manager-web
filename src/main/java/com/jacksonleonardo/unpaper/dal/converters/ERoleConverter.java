package com.jacksonleonardo.unpaper.dal.converters;

import com.jacksonleonardo.unpaper.model.enumerators.ERole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.Serializable;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ERoleConverter implements AttributeConverter<ERole, Integer>, Serializable {
    @Override
    public Integer convertToDatabaseColumn(ERole role) {
        return role.getID();
    }

    @Override
    public ERole convertToEntityAttribute(Integer integer) {
        return Stream.of(ERole.values())
                .filter((e) -> e.getID().equals(integer))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
