package sistema.hotelaria.controller;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sistema.hotelaria.model.Quarto;
import sistema.hotelaria.model.DetalheQuarto;
import sistema.hotelaria.repository.QuartoRepository;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/quartos")
public class QuartoController {
	private final QuartoRepository quartoRepository;

    public QuartoController(QuartoRepository quartoRepository) {
        this.quartoRepository = quartoRepository;
    }

    @GetMapping
    public String listar(Model model) {
        List<Quarto> lista = quartoRepository.findAll();
        model.addAttribute("quartos", lista);
        return "quartos/list";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        Quarto q = new Quarto();
        q.setDetalheQuarto(new DetalheQuarto());
        model.addAttribute("quarto", q);
        model.addAttribute("tipos", sistema.hotelaria.model.TipoQuarto.values());
        model.addAttribute("statusOptions", sistema.hotelaria.model.StatusQuarto.values());
        return "quartos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("quarto") Quarto quarto, org.springframework.validation.BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("tipos", sistema.hotelaria.model.TipoQuarto.values());
            model.addAttribute("statusOptions", sistema.hotelaria.model.StatusQuarto.values());
            return "quartos/form";
        }

        quartoRepository.save(quarto);
        return "redirect:/quartos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Optional<Quarto> op = quartoRepository.findById(id);
        if (op.isEmpty()) return "redirect:/quartos";
        model.addAttribute("quarto", op.get());
        model.addAttribute("tipos", sistema.hotelaria.model.TipoQuarto.values());
        model.addAttribute("statusOptions", sistema.hotelaria.model.StatusQuarto.values());
        return "quartos/form";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        quartoRepository.deleteById(id);
        return "redirect:/quartos";
    }

}
