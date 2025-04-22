package br.com.fiap.challenge.domains;

import br.com.fiap.challenge.domains.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "clinica")
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clinica", nullable = false)
    private Integer  idClinica;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "endereco", length = 250, nullable = false)
    private String endereco;

    @Column(name = "telefone", length = 15, nullable = false)
    private String telefone;

    @Column(name = "avaliacao", nullable = false)
    private Float avaliacao;

    @Column(name = "preco_medio", nullable = false)
    private Double precoMedio;

    private Integer role;

    public Role getRole() {
        return Role.toEnum(role);
    }

    public void setRole(Role role) {
        this.role = role.getCod();
    }
}
