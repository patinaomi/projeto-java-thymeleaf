package br.com.fiap.challenge.gateways.controller;

import br.com.fiap.challenge.domains.Cliente;
import br.com.fiap.challenge.gateways.repository.ClienteRepository;
import br.com.fiap.challenge.gateways.request.ClienteRequest;
import br.com.fiap.challenge.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public String getClientPage(Model model) {
        List<Cliente> clientes = clienteService.buscarTodos();
        model.addAttribute("clientes", clientes);
        return "cliente_page";
    }

    @GetMapping("/criar")
    public String getCreatClientPage(Model model) {
        model.addAttribute("newCliente", new Cliente());
        return "create_cliente_page";
    }

    @PostMapping("/criar")
    public String createCliente(@ModelAttribute Cliente cliente) {
        clienteService.criar(cliente);
        return "redirect:/clientes";
    }






        @GetMapping("/formulario")
    public ModelAndView carregarFormulario(@RequestParam(required = false) Integer id) {
        Cliente cliente = (id != null) ? clienteService.buscarPorId(id) : new Cliente();
        return new ModelAndView("clientes/formulario-cliente", "cliente", cliente);
    }


    @GetMapping("/editar/{id}")
    public ModelAndView editarCliente(@PathVariable Integer id) {
        Cliente cliente = clienteService.buscarPorId(id);
        return new ModelAndView("clientes/formulario-cliente", "cliente", cliente);
    }

    @GetMapping("/deletar/{id}")
    public ModelAndView deletarCliente(@PathVariable Integer id) {
        clienteService.deletar(id);
        return new ModelAndView("redirect:/clientes?sucesso");
    }
}
