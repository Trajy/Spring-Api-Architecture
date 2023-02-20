package br.com.trajy.architecture.restful.exception.data.struct;

import br.com.trajy.architecture.restful.exception.data.struct.detail.ErrorDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import java.util.List;

/**
* Data Structure referenced in the standard RFC7807
* @Author Trajy
*/
@Getter
@Setter
@Builder
public class ErrorMessage {

    String status;

    String title;

    String type;

    @Singular("detail")
    List<ErrorDetail> details;

}
