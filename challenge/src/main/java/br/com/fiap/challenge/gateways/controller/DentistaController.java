package br.com.fiap.challenge.gateways.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dentista")
@RequiredArgsConstructor
public class DentistaController {

    @GetMapping("/home")
    public String dentistaHome() {
        return "dentista/home";
    }
}
