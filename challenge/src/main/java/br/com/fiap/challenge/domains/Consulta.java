package br.com.fiap.challenge.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta", nullable = false)
    private Integer idConsulta;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_clinica", nullable = false)
    private Clinica clinica;

    @ManyToOne
    @JoinColumn(name = "id_dentista", nullable = false)
    private Dentista dentista;

    @Column(name = "tipo_servico", length = 100, nullable = false)
    private String tipoServico;

    @Column(name = "data_consulta", nullable = false)
    private LocalDateTime dataConsulta;

    @Column(name = "status_consulta", length = 1)
    private Character statusConsulta;

    @Column(name = "observacoes", length = 250)
    private String observacoes;

    @Column(name = "sintomas", length = 250)
    private String sintomas;

    @Column(name = "tratamento_recomendado", length = 250)
    private String tratamentoRecomendado;

    @Column(name = "custo")
    private Double custo;

    @Column(name = "prescricao", length = 250)
    private String prescricao;

    @Column(name = "data_retorno")
    private LocalDate dataRetorno;
}
