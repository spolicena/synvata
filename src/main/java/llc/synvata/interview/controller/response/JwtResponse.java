package llc.synvata.interview.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 1l;
    private final String token;

}