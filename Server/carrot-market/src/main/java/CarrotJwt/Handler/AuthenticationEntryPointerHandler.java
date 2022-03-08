package CarrotJwt.Handler;

import CarrotJwt.SecurityConfiguration.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationEntryPointerHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String exception = (String) request.getAttribute("exception");
        ErrorCode errorCode;

        if(exception == null){
            errorCode = ErrorCode.UNAUTHORIZEDException;
            setResponse(response, errorCode);
        }

        if(exception.equals("ExpiredJwtException")){
            errorCode = ErrorCode.ExpiredJwtException;
            setResponse(response, errorCode);
            return;
        }
    }
    private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException{
        JSONObject json = new JSONObject();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        json.put("code", errorCode.getCode());
        json.put("message", errorCode.getMessage());
        response.getWriter().print(json);
    }
}
