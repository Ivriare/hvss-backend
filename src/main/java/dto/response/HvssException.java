package dto.response;

import lombok.Data;

@Data
public class HvssException {
    String message;
    HvssExceptionCode code;
}
