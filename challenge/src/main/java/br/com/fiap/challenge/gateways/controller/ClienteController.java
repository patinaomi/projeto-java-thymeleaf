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

@Controller
@RequestMapping( "/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    private final ClienteRepository clienteRepository;

    @GetMapping
    public String getClientPage(@RequestParam(required = false, name="login") String login,
                                @RequestParam(required = false) String email,
                                Model model) {
        System.out.println("login: " + login);
        model.addAttribute("clienteLogin", login);
        return "cliente_page";
    }

    @GetMapping("/formulario")
    public ModelAndView carregarFormulario(@RequestParam(required = false) Integer id) {
        Cliente cliente = (id != null) ? clienteService.buscarPorId(id) : new Cliente();
        return new ModelAndView("clientes/formulario-cliente", "cliente", cliente);
    }

    @PostMapping
    public ModelAndView criar(@Valid @ModelAttribute("cliente") ClienteRequest clienteRequest) {
        Cliente cliente = Cliente.builder()
                .nome(clienteRequest.getNome())
                .sobrenome(clienteRequest.getSobrenome())
                .email(clienteRequest.getEmail())
                .telefone(clienteRequest.getTelefone())
                .dataNasc(clienteRequest.getDataNasc())
                .endereco(clienteRequest.getEndereco())
                .build();

        clienteService.criar(cliente);
        clienteRepository.save(cliente);

        return new ModelAndView("redirect:/clientes?sucesso");
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
