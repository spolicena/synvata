package llc.synvata.interview.service;

import llc.synvata.interview.domain.Login;
import llc.synvata.interview.domain.entity.LoginEntity;
import llc.synvata.interview.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LoginServiceImpl implements LoginService {

    private final Logger logger = Logger.getLogger(LoginServiceImpl.class.getName());

    private final String regexPatternEmail = "^(.+)@(.+)$";
    private final String regexStrongPass = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8,20}$";

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void saveUser(Login login) {
        Pattern pattern = Pattern.compile(regexPatternEmail);
        Matcher matcher = pattern.matcher(login.getEmail());
        if(!matcher.matches()){
            throw new RuntimeException("Email invalido");
        }

        pattern = Pattern.compile(regexStrongPass);
        matcher = pattern.matcher(login.getSenha());
        if(!matcher.matches()){
            throw new RuntimeException("Senha fraca");
        }

        LoginEntity entity = new LoginEntity(login.getEmail(), passwordEncoder.encode(login.getSenha()));
        entity.setValido(Boolean.TRUE);
        loginRepository.save(entity);
    }

    @Override
    public UserDetails findByEmail(String email) {
        LoginEntity login = loginRepository.findByEmail(email);
        return new User(email, login.getSenha(),
                new ArrayList<>());
    }

    @Override
    public void invalidateToken(String email) {
        LoginEntity login = loginRepository.findByEmail(email);
        login.setValido(Boolean.FALSE);
        loginRepository.save(login);
    }
}
