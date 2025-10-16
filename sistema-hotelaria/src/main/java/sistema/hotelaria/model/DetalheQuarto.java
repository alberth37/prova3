package sistema.hotelaria.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "detalhe_quarto")
public class DetalheQuarto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    private Double areaM2;

    private Boolean possuiArCondicionado = false;
    private Boolean possuiTv = false;
    private Boolean possuiFrigoBar = false;

    @Size(max = 1000)
    private String descricaoExtra;

    @OneToOne
    @JoinColumn(name = "quarto_id", nullable = false, unique = true)
    private Quarto quarto;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getAreaM2() { return areaM2; }
    public void setAreaM2(Double areaM2) { this.areaM2 = areaM2; }
    public Boolean getPossuiArCondicionado() { return possuiArCondicionado; }
    public void setPossuiArCondicionado(Boolean possuiArCondicionado) { this.possuiArCondicionado = possuiArCondicionado; }
    public Boolean getPossuiTv() { return possuiTv; }
    public void setPossuiTv(Boolean possuiTv) { this.possuiTv = possuiTv; }
    public Boolean getPossuiFrigoBar() { return possuiFrigoBar; }
    public void setPossuiFrigoBar(Boolean possuiFrigoBar) { this.possuiFrigoBar = possuiFrigoBar; }
    public String getDescricaoExtra() { return descricaoExtra; }
    public void setDescricaoExtra(String descricaoExtra) { this.descricaoExtra = descricaoExtra; }
    public Quarto getQuarto() { return quarto; }
    public void setQuarto(Quarto quarto) { this.quarto = quarto; }
}
