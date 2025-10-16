package sistema.hotelaria.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sistema.hotelaria.model.Reserva;
import sistema.hotelaria.model.Quarto;
import sistema.hotelaria.repository.QuartoRepository;
import sistema.hotelaria.repository.ReservaRepository;
import sistema.hotelaria.service.ReservaService;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reservas")
public class ReservaMvcController {
	 private final ReservaRepository reservaRepository;
	    private final QuartoRepository quartoRepository;
	    private final ReservaService reservaService;

	    public ReservaMvcController(ReservaRepository reservaRepository, QuartoRepository quartoRepository, ReservaService reservaService) {
	        this.reservaRepository = reservaRepository;
	        this.quartoRepository = quartoRepository;
	        this.reservaService = reservaService;
	    }

	    @GetMapping
	    public String listar(Model model) {
	        List<Reserva> lista = reservaRepository.findAll();
	        model.addAttribute("reservas", lista);
	        return "reservas/list";
	    }

	    @GetMapping("/novo")
	    public String novoForm(Model model) {
	        Reserva r = new Reserva();
	        model.addAttribute("reserva", r);
	        model.addAttribute("quartos", quartoRepository.findAll());
	        model.addAttribute("statusOptions", sistema.hotelaria.model.StatusReserva.values());
	        return "reservas/form";
	    }

	    @PostMapping("/salvar")
	    public String salvar(@Valid @ModelAttribute("reserva") Reserva reserva, org.springframework.validation.BindingResult br, Model model) {
	        if (br.hasErrors()) {
	            model.addAttribute("quartos", quartoRepository.findAll());
	            model.addAttribute("statusOptions", sistema.hotelaria.model.StatusReserva.values());
	            return "reservas/form";
	        }
	        try {
	            reservaService.save(reserva);
	        } catch (IllegalStateException ex) {
	            br.reject("data.conflict", ex.getMessage());
	            model.addAttribute("quartos", quartoRepository.findAll());
	            model.addAttribute("statusOptions", sistema.hotelaria.model.StatusReserva.values());
	            return "reservas/form";
	        }
	        return "redirect:/reservas";
	    }

	    @GetMapping("/editar/{id}")
	    public String editar(@PathVariable Long id, Model model) {
	        Optional<Reserva> op = reservaRepository.findById(id);
	        if (op.isEmpty()) return "redirect:/reservas";
	        model.addAttribute("reserva", op.get());
	        model.addAttribute("quartos", quartoRepository.findAll());
	        model.addAttribute("statusOptions", sistema.hotelaria.model.StatusReserva.values());
	        return "reservas/form";
	    }

	    @PostMapping("/excluir/{id}")
	    public String excluir(@PathVariable Long id) {
	        reservaRepository.deleteById(id);
	        return "redirect:/reservas";
	    }

}
