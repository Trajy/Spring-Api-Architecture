package br.com.trajy.architecture.restful.exception.data.struct;

import br.com.trajy.architecture.restful.exception.data.struct.detail.ErrorDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
* Data Structure referenced in the standard RFC7807
* @Author Trajy
*/
@Getter
@Setter
@Builder
public class ErrorMessage {

    private ErrorMessage() { }

    String status;

    String title;

    String type;

    List<ErrorDetail> detail;

}
