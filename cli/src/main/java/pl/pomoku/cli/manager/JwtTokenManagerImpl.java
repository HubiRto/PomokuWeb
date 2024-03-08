package pl.pomoku.cli.manager;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenManagerImpl implements JwtTokenManager{
    private String jwtToken;

    @Override
    public void saveJwtToken(String token) {
        this.jwtToken = token;
    }

    @Override
    public String getJwtToken() {
        return this.jwtToken;
    }
}
