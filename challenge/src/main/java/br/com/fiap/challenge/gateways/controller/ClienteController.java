package br.com.fiap.challenge.gateways.controller;

import br.com.fiap.challenge.domains.Cliente;
import br.com.fiap.challenge.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/editar/{id}")
    public String getEditarCliente(@PathVariable Integer id, Model model) {
        Cliente cliente = clienteService.buscarPorId(id);
        model.addAttribute("clienteEdit", cliente);
        return "edit_cliente_page";
    }

    @PostMapping("/editar")
    public String editarCliente(@RequestParam Integer id, @ModelAttribute Cliente cliente) {
        clienteService.atualizar(id, cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/deletar/{id}")
    public String deletarCliente(@PathVariable Integer id) {
        clienteService.deletar(id);
        return "redirect:/clientes";
    }
}
