package CarrotController;

import CarrotJwt.Handler.ApiResponse;
import CarrotJwt.JwtDao.AuthDTO;
import JwtService.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(tags = "Auth / 로그인")
@RequestMapping("/app/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @ApiOperation(value = "로그인")
    public ApiResponse login(@RequestBody @Valid AuthDTO.LoginDTO loginDTO){
        return authService.login(loginDTO);
    }

    @PostMapping("/refreshToken")
    @ApiOperation(value = "새로운 토큰 발급")
    public ApiResponse newAccessToken(@RequestBody @Valid AuthDTO.GetNewAccessTokenDTO getNewAccessTokenDTO, HttpServletRequest request){
        return authService.newAccessToken(getNewAccessTokenDTO, request);
    }

}
