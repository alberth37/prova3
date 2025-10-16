package sistema.hotelaria.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "quarto")
public class Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private Integer numeroQuarto;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoQuarto tipoQuarto;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusQuarto status;

    @NotNull
    @PositiveOrZero
    private BigDecimal precoDiaria;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "quarto", orphanRemoval = true, fetch = FetchType.LAZY)
    private DetalheQuarto detalheQuarto;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getNumeroQuarto() { return numeroQuarto; }
    public void setNumeroQuarto(Integer numeroQuarto) { this.numeroQuarto = numeroQuarto; }
    public TipoQuarto getTipoQuarto() { return tipoQuarto; }
    public void setTipoQuarto(TipoQuarto tipoQuarto) { this.tipoQuarto = tipoQuarto; }
    public StatusQuarto getStatus() { return status; }
    public void setStatus(StatusQuarto status) { this.status = status; }
    public BigDecimal getPrecoDiaria() { return precoDiaria; }
    public void setPrecoDiaria(BigDecimal precoDiaria) { this.precoDiaria = precoDiaria; }
    public DetalheQuarto getDetalheQuarto() { return detalheQuarto; }
    public void setDetalheQuarto(DetalheQuarto detalheQuarto) { 
        this.detalheQuarto = detalheQuarto; 
        if (detalheQuarto != null) detalheQuarto.setQuarto(this);
    }
}
