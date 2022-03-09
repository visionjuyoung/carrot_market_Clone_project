package JwtService;

import CarrotJwt.Handler.ApiResponse;
import CarrotJwt.Handler.AuthMapper;
import CarrotJwt.Handler.ResponseMap;
import CarrotJwt.JwtDao.AuthDTO;
import CarrotJwt.JwtDao.RefreshToken;
import CarrotJwt.JwtProvider.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final AuthMapper authMapper;

    public ApiResponse login(AuthDTO.LoginDTO loginDTO){
        ResponseMap result = new ResponseMap();

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );
            Map createToken = createTokenReturn(loginDTO);
            result.setResponseData("accessToken", createToken.get("accessToken"));
            result.setResponseData("refreshIdx", createToken.get("refreshIdx"));
        }catch (Exception e){
            e.printStackTrace();
            throw new AuthenticationException(ErrorCode.UsernameOrPasswordNotFoundException);
        }
        return result;
    }

    public ApiResponse newAccessToken(AuthDTO.GetNewAccessTokenDTO getNewAccessTokenDTO, HttpServletRequest request){
        ResponseMap result = new ResponseMap();
        String refreshToken = authMapper.findRefreshTokenByIdx(getNewAccessTokenDTO.getRefreshIdx());

        if(jwtProvider.validateJwtToken(request, refreshToken)){
            String email = jwtProvider.getUserInfo(refreshToken);
            AuthDTO.LoginDTO loginDTO = new AuthDTO.LoginDTO();
            loginDTO.setEmail(email);

            Map createToken = createTokenReturn(loginDTO);
            result.setResponseData("accessToken", createToken.get("accessToken"));
            result.setResponseData("refreshIdx", createToken.get("refreshIdx"));
        } else {
            result.setResponseData("code", ErrorCode.ReLogin.getCode());
            result.setResponseData("message", ErrorCode.ReLogin.getMessage());
            result.setResponseData("HttpStatus", ErrorCode.ReLogin.getStatus());
        }
        return result;
    }

    private Map<String, String> createTokenReturn(AuthDTO.LoginDTO loginDTO){
        Map result = new HashMap();

        String accessToken = jwtProvider.createAccessToken(loginDTO);
        String refreshToken = jwtProvider.createRefreshToken(loginDTO).get("refreshToken");
        String refreshTokenExpirationAt = jwtProvider.createRefreshToken(loginDTO).get("refreshTokenExpirationAt");

        RefreshToken insertRefreshToken = RefreshToken.builder()
                .userEmail(loginDTO.getEmail())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .refreshTokenExpirationAt(refreshTokenExpirationAt)
                .build();

        authMapper.insertOrUpdateRefreshToken(insertRefreshToken);

        result.put("accessToken", accessToken);
        result.put("refreshIdx", insertRefreshToken.getIdx());

        return result;
    }
}
