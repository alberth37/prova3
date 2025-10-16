package sistema.hotelaria.service;

import sistema.hotelaria.model.Reserva;
import sistema.hotelaria.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    /**
     * Checks for date conflicts for the same room (quarto).
     * Returns true if there is a conflict.
     */
    public boolean hasConflict(Reserva reserva) {
        Long quartoId = reserva.getQuarto().getId();
        if (quartoId == null) {
           
            return false;
        }
        List<Reserva> conflicts = reservaRepository.findConflictingReservations(
                quartoId, reserva.getDataEntrada(), reserva.getDataSaida());
        
        if (reserva.getId() != null) {
            conflicts.removeIf(r -> r.getId().equals(reserva.getId()));
        }
        return !conflicts.isEmpty();
    }

    @Transactional
    public Reserva save(Reserva reserva) {
        if (hasConflict(reserva)) {
            throw new IllegalStateException("Conflito de datas: já existe reserva para esse quarto no período informado.");
        }
        return reservaRepository.save(reserva);
    }
}
