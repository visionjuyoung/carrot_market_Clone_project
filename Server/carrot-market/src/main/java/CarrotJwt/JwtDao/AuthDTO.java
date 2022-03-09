package CarrotJwt.JwtDao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

public class AuthDTO {

    @Data
    public static class LoginDTO{
        @NotBlank
        @ApiModelProperty(value = "아이디", example = "admin@naver.com", required = true)
        private String email;

        @NotBlank
        @ApiModelProperty(value = "비밀번호", example = "12345", required = true)
        private String password;
    }

    @Data
    public static class GetNewAccessTokenDTO{
        @ApiModelProperty(value = "Refresh Token Index", example = "1", required = true)
        private long refreshIdx;
    }

}
