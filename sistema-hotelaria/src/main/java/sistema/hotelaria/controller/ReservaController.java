package sistema.hotelaria.controller;

import sistema.hotelaria.model.Reserva;
import sistema.hotelaria.repository.QuartoRepository;
import sistema.hotelaria.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
	
	 private final ReservaService reservaService;
	    private final QuartoRepository quartoRepository;

	    public ReservaController(ReservaService reservaService, QuartoRepository quartoRepository) {
	        this.reservaService = reservaService;
	        this.quartoRepository = quartoRepository;
	    }

	    @PostMapping
	    public ResponseEntity<?> criarReserva(@Valid @RequestBody Reserva reserva) {

	        if (reserva.getQuarto() == null || reserva.getQuarto().getId() == null ||
	            quartoRepository.findById(reserva.getQuarto().getId()).isEmpty()) {
	            return ResponseEntity.badRequest().body("Quarto inválido ou não encontrado (precisa informar quarto.id)");
	        }
	        try {
	            Reserva saved = reservaService.save(reserva);
	            return ResponseEntity.ok(saved);
	        } catch (IllegalStateException ex) {
	            return ResponseEntity.status(409).body(ex.getMessage());
	        }
	    }

}
