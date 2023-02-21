package br.com.trajy.architecture.restful.exception.data.struct.detail;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FieldErrorMessage {

    String field;

    String validation;

}
