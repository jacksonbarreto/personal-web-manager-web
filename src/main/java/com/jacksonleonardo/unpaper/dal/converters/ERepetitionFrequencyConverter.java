package com.jacksonleonardo.unpaper.dal.converters;

import com.jacksonleonardo.unpaper.model.enumerators.ERepetitionFrequency;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.Serializable;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ERepetitionFrequencyConverter implements AttributeConverter<ERepetitionFrequency, Integer>, Serializable {
    @Override
    public Integer convertToDatabaseColumn(ERepetitionFrequency frequency) {
        return frequency.getID();
    }

    @Override
    public ERepetitionFrequency convertToEntityAttribute(Integer integer) {
        return Stream.of(ERepetitionFrequency.values())
                .filter((e) -> e.getID().equals(integer))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
