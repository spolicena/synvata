package llc.synvata.interview.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "tb_scenario")
public class ScenarioEntity implements Serializable {

    @Id
    @Column(name = "id_scenario", updatable = false, nullable = false)
    private String id;

    @Column(name = "periods" )
    private String periods;

    @Column(name = "index_a" )
    private String indexA;

    @Column(name = "index_b", updatable = false)
    private String indexB;

    @Column(name = "index_c" )
    private String indexC;

    @Column(name = "index_d", updatable = false)
    private String indexD;

    @Column(name = "amount" )
    private String amount;

    @Column(name = "accumulated", updatable = false)
    private String accumulated;

    @Column(name = "obtained", updatable = false)
    private String obtained;

    @Column(name = "calculated_total", updatable = false)
    private String calculatedTotal;

}
