package pl.pomoku.cli.manager;

public interface JwtTokenManager {
    void saveJwtToken(String token);
    String getJwtToken();
}
