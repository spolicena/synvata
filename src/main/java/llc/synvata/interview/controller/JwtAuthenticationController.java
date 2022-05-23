package llc.synvata.interview.controller;

import llc.synvata.interview.config.JwtTokenUtil;
import llc.synvata.interview.controller.request.JwtRequest;
import llc.synvata.interview.controller.response.JwtResponse;
import llc.synvata.interview.domain.Login;
import llc.synvata.interview.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private LoginService loginService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest request) throws Exception {
        authenticate(request.getEmail(), request.getSenha());
        final UserDetails userDetails = loginService.findByEmail(request.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/logoff", method = RequestMethod.POST)
    public ResponseEntity<?> invalidateToken(@RequestBody String email) throws Exception {
        loginService.invalidateToken(email);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/new_user", method = RequestMethod.POST)
    public ResponseEntity<?> createNewUser(@RequestBody JwtRequest request){
        loginService.saveUser(new Login(request.getEmail(), request.getSenha()));
        return ResponseEntity.ok().build();
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, passwordEncoder.encode(password)));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}