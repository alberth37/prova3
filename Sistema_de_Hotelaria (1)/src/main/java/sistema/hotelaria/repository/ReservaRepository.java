package sistema.hotelaria.repository;

import sistema.hotelaria.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    @Query("""
        select r from Reserva r
        where r.quarto.id = :quartoId
          and r.status <> 'CANCELADO'
          and not (r.dataSaida <= :entrada or r.dataEntrada >= :saida)
    """)
    List<Reserva> findConflictingReservations(@Param("quartoId") Long quartoId, 
                                              @Param("entrada") LocalDate entrada, 
                                              @Param("saida") LocalDate saida);
}
