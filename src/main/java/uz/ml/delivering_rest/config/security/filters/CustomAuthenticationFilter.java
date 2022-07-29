package uz.ml.delivering_rest.config.security.filters;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.ml.delivering_rest.dto.UserResponseDTO;
import uz.ml.delivering_rest.dto.user.UserLoginDTO;
import uz.ml.delivering_rest.jwt.JWTUtils;
import uz.ml.delivering_rest.response.ApplicationError;
import uz.ml.delivering_rest.response.Data;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/api/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserLoginDTO dto = new ObjectMapper().readValue(request.getReader(), UserLoginDTO.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
            return authenticationManager.authenticate(token);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentic) throws IOException, ServletException {
        User user = (User) authentic.getPrincipal();
        Date accessDate = JWTUtils.getExpiry();
        Date refreshDate = JWTUtils.getExpiryForRefreshToken();

        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(accessDate)
                .sign(JWTUtils.getAlgorithm());

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setAccessToken(accessToken);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), userResponseDTO);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Data<ApplicationError> errorData = new Data<>(new ApplicationError(failed.getMessage(), request.getRequestURL().toString(), HttpStatus.INTERNAL_SERVER_ERROR));
        errorData.setSuccess(false);
        new ObjectMapper().writeValue(response.getOutputStream(), errorData);
    }
}
