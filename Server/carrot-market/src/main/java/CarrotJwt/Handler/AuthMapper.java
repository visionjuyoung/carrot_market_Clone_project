package CarrotJwt.Handler;


import CarrotJwt.JwtDao.RefreshToken;
import CarrotJwt.JwtDao.User;
import org.mapstruct.Mapper;

@Mapper
public interface AuthMapper {
    User findByEmail(String email);
    String findRefreshTokenByIdx(long idx);
    void insertOrUpdateRefreshToken(RefreshToken refreshToken);
}
