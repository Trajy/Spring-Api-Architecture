package br.com.trajy.architecture.restful.exception.data.struct.detail;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class FieldErrorMessage {

    String field;

    String validation;

}
