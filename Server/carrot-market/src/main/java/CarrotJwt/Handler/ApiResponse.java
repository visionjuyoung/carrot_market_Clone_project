package CarrotJwt.Handler;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Data
public class ApiResponse {
    private int code = HttpStatus.OK.value();
    private Object result;

    public ApiResponse(){}
    public ApiResponse(int code, Object result){
        this.code = code;
        this.result = result;
    }

    public void setResult(Object result){
        this.result = result;
    }
}

