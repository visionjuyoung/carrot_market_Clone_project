package CarrotJwt.JwtDao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshToken {
    private long idx;
    private String userEmail;
    private String accessToken;
    private String refreshToken;
    private String refreshTokenExpirationAt;
}
