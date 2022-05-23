package llc.synvata.interview.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import llc.synvata.interview.domain.entity.ScenarioEntity;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Login {

    private String email;

    private String senha;

}
