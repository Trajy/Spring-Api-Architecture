package br.com.trajy.architecture.restful.exception.data.struct;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
* Data Structure referenced in the standard RFC7807
* @Author Trajy
*/
@Getter
@Setter
@Builder
public class ErrorMessage<T> {

    String status;

    String title;

    String type;

    T detail;

}
