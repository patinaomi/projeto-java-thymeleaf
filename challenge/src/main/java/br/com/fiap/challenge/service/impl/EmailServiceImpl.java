package br.com.fiap.challenge.service.impl;

import br.com.fiap.challenge.domains.Dentista;
import br.com.fiap.challenge.gateways.repository.DentistaRepository;
import br.com.fiap.challenge.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final DentistaRepository dentistaRepository;
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public EmailServiceImpl(DentistaRepository dentistaRepository, JavaMailSender javaMailSender) {
        this.dentistaRepository = dentistaRepository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void enviarEmail(String destinatario, String assunto, String msg) {
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setFrom(remetente);
            email.setTo(destinatario);
            email.setSubject(assunto);
            email.setText(msg);
            javaMailSender.send(email);
            System.out.println("Email enviado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao enviar email: " + e.getMessage());
        }
    }

    public void enviarEmailErroTransacao(Integer dentistaId, String mensagemErro) {
        Dentista dentista = dentistaRepository.findById(dentistaId).orElse(null);

        if (dentista != null && dentista.getUser() != null) {
            String destinatario = dentista.getUser().getUsername();
            String mensagem = String.format(
                    "Olá, %s! Não foi possível concluir a operação devido ao seguinte erro:\n\n%s\n\nPor favor, tente novamente mais tarde.",
                    dentista.getNome(),
                    mensagemErro
            );
            enviarEmail(destinatario, "Erro na Transação", mensagem);
        } else {
            enviarEmail(remetente, "Erro na Transação", "Erro ao localizar dentista ou e-mail: " + mensagemErro);
        }
    }
}
