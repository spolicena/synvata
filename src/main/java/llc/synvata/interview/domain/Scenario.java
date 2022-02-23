package llc.synvata.interview.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import llc.synvata.interview.domain.entity.ScenarioEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Scenario {

    @JsonProperty("periods")
    private String periods;
    @JsonProperty("index_a")
    private String indexA;
    @JsonProperty("index_b")
    private String indexB;
    @JsonProperty("index_c")
    private String indexC;
    @JsonProperty("index_d")
    private String indexD;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("accumulated")
    private String accumulated;
    @JsonProperty("obtained")
    private String obtained;
    @JsonProperty("calculated_total")
    private String calculatedTotal;

    public static Scenario buildFromScenarioEntity(ScenarioEntity scenarioEntity) {
        return Scenario.builder()
                .periods(scenarioEntity.getPeriods())
                .indexA(scenarioEntity.getIndexA())
                .indexB(scenarioEntity.getIndexB())
                .indexC(scenarioEntity.getIndexC())
                .indexD(scenarioEntity.getIndexD())
                .amount(scenarioEntity.getAmount())
                .accumulated(scenarioEntity.getAccumulated())
                .obtained(scenarioEntity.getObtained())
                .calculatedTotal(scenarioEntity.getCalculatedTotal())
                .build();
    }

}
