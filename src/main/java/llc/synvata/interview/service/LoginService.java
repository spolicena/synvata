package llc.synvata.interview.service;

import llc.synvata.interview.domain.Login;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoginService {
    void saveUser(Login login);

    UserDetails findByEmail(String email);

    void invalidateToken(String email);
}
