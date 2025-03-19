package br.com.fiap.challenge.gateways.mvc;

import br.com.fiap.challenge.domains.Cliente;
import br.com.fiap.challenge.gateways.repository.ClienteRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("clientes")
@RequiredArgsConstructor
public class MVCClienteController {

    private static final String PAGINA_LISTAGEM = "cliente/listagem-clientes";
    private static final String PAGINA_CADASTRO = "cliente/formulario-cliente";
    private static final String REDIRECT_LISTAGEM = "redirect:/clientes?sucesso";

    private final ClienteRepository clienteRepository;

    @GetMapping
    public String listarClientes(Model model) {
        var clientes = clienteRepository.findAll();
        model.addAttribute("clientes", clientes);
        return PAGINA_LISTAGEM;
    }

    @GetMapping("formulario")
    public String carregarFormulario(@RequestParam(required = false) Integer id, Model model) {
        if (id != null) {
            model.addAttribute("cliente", clienteRepository.findById(id).orElse(new Cliente()));
        } else {
            model.addAttribute("cliente", new Cliente());
        }
        return PAGINA_CADASTRO;
    }

    @PostMapping
    public String cadastrar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("cliente", cliente);
            return PAGINA_CADASTRO;
        }

        clienteRepository.save(cliente);
        return REDIRECT_LISTAGEM;
    }

    @DeleteMapping
    public String deletar(@RequestParam Integer id) {
        clienteRepository.deleteById(id);
        return REDIRECT_LISTAGEM;
    }
}
