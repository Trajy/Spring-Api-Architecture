package br.com.trajy.architecture.layer.util;

import static java.util.List.of;
import static org.apache.commons.collections4.IterableUtils.find;

import lombok.Getter;

@Getter
public enum ValidationErrorMessageEnum {

    POSITIVE_REQUIRED("Value must be positive"),
    REQUEST_BODY_REQUERED("Request body required");

    private String message;

    ValidationErrorMessageEnum(String message) {
        this.message = message;
    }

    public static String getMessage(ValidationErrorMessageEnum type) {
        return find(of(ValidationErrorMessageEnum.values()), value -> value.equals(type)).getMessage();
    }

}
