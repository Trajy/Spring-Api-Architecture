package br.com.trajy.architecture.restful.constant;

import static java.util.List.of;
import static org.apache.commons.collections4.IterableUtils.find;

import lombok.Getter;

@Getter
public enum ErrorMessageEnum {

    PATH_URL_ID_REQUIRED("Id required in path url"),
    REQUEST_BODY_REQUERED("Request body required"),
    RESOURCE_VIOLATION_CONSTRAINT("Please verify Resource constraint violations"),
    ENTITY_CONSTRAINT_VIOLATION("Please verify Entity constraint violations"),
    BAD_CREDENTIALS("Usuário inexistente ou senha inválida.");

    private String message;

    ErrorMessageEnum(String message) {
        this.message = message;
    }

    public static String getMessage(ErrorMessageEnum type) {
        return find(of(ErrorMessageEnum.values()), value -> value.equals(type)).getMessage();
    }

}
