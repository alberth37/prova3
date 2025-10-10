package sistema.hotelaria.repository;

import sistema.hotelaria.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface QuartoRepository extends JpaRepository<Quarto, Long> {
    Optional<Quarto> findByNumeroQuarto(Integer numeroQuarto);
}
