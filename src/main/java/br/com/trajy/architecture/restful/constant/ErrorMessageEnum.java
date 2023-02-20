package br.com.trajy.architecture.restful.constant;

import static java.util.List.of;
import static org.apache.commons.collections4.IterableUtils.find;

import lombok.Getter;

@Getter
public enum ErrorMessageEnum {

    PATH_URL_ID_REQUIRED("O id deve ser informado");

    private String message;

    ErrorMessageEnum(String message) {
        this.message = message;
    }

    public String getMessageFromEnum(ErrorMessageEnum type) {
        return find(of(ErrorMessageEnum.values()), value -> value.equals(type)).getMessage();
    }

}
