package llc.synvata.interview.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "tb_login")
public class LoginEntity {
    public LoginEntity(String email, String senha){
        this.email = email;
        this.senha = senha;
    }

    @Id
    @Column(name = "id_login", updatable = false, nullable = false)
    private String id;

    @Column(name = "email", unique = true )
    private String email;

    @Column(name = "senha" )
    private String senha;

    @Column(name = "valido")
    private Boolean valido;
}
