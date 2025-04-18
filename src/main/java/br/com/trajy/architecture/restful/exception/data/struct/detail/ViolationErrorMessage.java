package br.com.trajy.architecture.restful.exception.data.struct.detail;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class ViolationErrorMessage {

    String clazz;

    List<FieldErrorMessage> fields;

}
