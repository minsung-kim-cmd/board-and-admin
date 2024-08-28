package com.project.boardadmin.domain.converter;

import com.project.boardadmin.domain.constant.RoleType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class RoleTypesConverter implements AttributeConverter<Set<RoleType>, String> {

    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(Set<RoleType> attribute) {
        return attribute.stream().map(RoleType::name).sorted().collect(Collectors.joining()); //각원소들의 이름 가져와서 문자열로 모으기
    }

    @Override
    public Set<RoleType> convertToEntityAttribute(String dbDate) {
        return Arrays.stream(dbDate.split(DELIMITER)).map(RoleType::valueOf).collect(Collectors.toSet());
    }
}
