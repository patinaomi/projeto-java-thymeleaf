package br.com.fiap.challenge.service;

import br.com.fiap.challenge.domains.*;
import br.com.fiap.challenge.domains.enums.Role;
import br.com.fiap.challenge.gateways.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DBService {

    private final ClienteRepository clienteRepository;
    private final ClinicaRepository clinicaRepository;
    private final EspecialidadeRepository especialidadeRepository;
    private final DentistaRepository dentistaRepository;
    private final ConsultaRepository consultaRepository;
    private final SinistroRepository sinistroRepository;
    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder pe;

    @Transactional
    public void instantiateTestDatabase() {

        User clinicaUser1 = new User(null, "clinica1@email.com", pe.encode("senha123"), true, Role.CLINICA);
        User clinicaUser2 = new User(null, "clinica2@email.com", pe.encode("senha123"), true, Role.CLINICA);
        User clinicaUser3 = new User(null, "clinica3@email.com", pe.encode("senha123"), true, Role.CLINICA);
        User clinicaUser4 = new User(null, "clinica4@email.com", pe.encode("senha123"), true, Role.CLINICA);
        User clinicaUser5 = new User(null, "clinica5@email.com", pe.encode("senha123"), true, Role.CLINICA);
        User clinicaUser6 = new User(null, "clinica6@email.com", pe.encode("senha123"), true, Role.CLINICA);
        User clinicaUser7 = new User(null, "clinica7@email.com", pe.encode("senha123"), true, Role.CLINICA);
        User clinicaUser8 = new User(null, "clinica8@email.com", pe.encode("senha123"), true, Role.CLINICA);
        User clinicaUser9 = new User(null, "clinica9@email.com", pe.encode("senha123"), true, Role.CLINICA);
        User clinicaUser10 = new User(null, "clinica10@email.com", pe.encode("senha123"), true, Role.CLINICA);

        User dentistaUser1 = new User(null, "dentista1@email.com", pe.encode("senha123"), true, Role.DENTISTA);
        User dentistaUser2 = new User(null, "dentista2@email.com", pe.encode("senha123"), true, Role.DENTISTA);
        User dentistaUser3 = new User(null, "dentista3@email.com", pe.encode("senha123"), true, Role.DENTISTA);
        User dentistaUser4 = new User(null, "dentista4@email.com", pe.encode("senha123"), true, Role.DENTISTA);
        User dentistaUser5 = new User(null, "dentista5@email.com", pe.encode("senha123"), true, Role.DENTISTA);
        User dentistaUser6 = new User(null, "dentista6@email.com", pe.encode("senha123"), true, Role.DENTISTA);
        User dentistaUser7 = new User(null, "dentista7@email.com", pe.encode("senha123"), true, Role.DENTISTA);
        User dentistaUser8 = new User(null, "dentista8@email.com", pe.encode("senha123"), true, Role.DENTISTA);
        User dentistaUser9 = new User(null, "dentista9@email.com", pe.encode("senha123"), true, Role.DENTISTA);
        User dentistaUser10 = new User(null, "dentista10@email.com", pe.encode("senha123"), true, Role.DENTISTA);

        User clienteUser1 = new User(null, "cliente1@email.com", pe.encode("senha123"), true, Role.CLIENTE);
        User clienteUser2 = new User(null, "cliente2@email.com", pe.encode("senha123"), true, Role.CLIENTE);
        User clienteUser3 = new User(null, "cliente3@email.com", pe.encode("senha123"), true, Role.CLIENTE);
        User clienteUser4 = new User(null, "cliente4@email.com", pe.encode("senha123"), true, Role.CLIENTE);
        User clienteUser5 = new User(null, "cliente5@email.com", pe.encode("senha123"), true, Role.CLIENTE);
        User clienteUser6 = new User(null, "cliente6@email.com", pe.encode("senha123"), true, Role.CLIENTE);
        User clienteUser7 = new User(null, "cliente7@email.com", pe.encode("senha123"), true, Role.CLIENTE);
        User clienteUser8 = new User(null, "cliente8@email.com", pe.encode("senha123"), true, Role.CLIENTE);
        User clienteUser9 = new User(null, "cliente9@email.com", pe.encode("senha123"), true, Role.CLIENTE);
        User clienteUser10 = new User(null, "cliente10@email.com", pe.encode("senha123"), true, Role.CLIENTE);

        userRepository.saveAll(Arrays.asList(
                clinicaUser1, clinicaUser2, clinicaUser3, clinicaUser4, clinicaUser5, clinicaUser6, clinicaUser7, clinicaUser8, clinicaUser9, clinicaUser10,
                dentistaUser1, dentistaUser2, dentistaUser3, dentistaUser4, dentistaUser5, dentistaUser6, dentistaUser7, dentistaUser8, dentistaUser9, dentistaUser10,
                clienteUser1, clienteUser2, clienteUser3, clienteUser4, clienteUser5, clienteUser6, clienteUser7, clienteUser8, clienteUser9, clienteUser10
        ));


        Cliente cliente1 = new Cliente(null, "Alice", "Silva", "11987654321",
                LocalDate.parse("1990-01-15"), "Rua das Flores, 123", clienteUser1);

        Cliente cliente2 = new Cliente(null, "Bruno", "Santos","11976543210",
                LocalDate.parse("1985-03-20"), "Avenida Central, 456", clienteUser2);

        Cliente cliente3 = new Cliente(null, "Carlos", "Oliveira", "11965432109",
                LocalDate.parse("1992-07-11"), "Rua dos Lírios, 789", clienteUser3);

        Cliente cliente4 = new Cliente(null, "Diana", "Pereira", "11954321098",
                LocalDate.parse("1988-04-22"), "Praça das Árvores, 321", clienteUser4);

        Cliente cliente5 = new Cliente(null, "Eduardo", "Melo", "11943210987",
                LocalDate.parse("1980-09-05"), "Avenida do Sol, 654", clienteUser5);

        Cliente cliente6 = new Cliente(null, "Fernanda", "Costa", "11932109876",
                LocalDate.parse("1995-12-30"), "Rua das Estrelas, 852", clienteUser6);

        Cliente cliente7 = new Cliente(null, "Gabriel", "Santos", "11921098765",
                LocalDate.parse("1991-11-19"), "Rua das Palmeiras, 963", clienteUser7);

        Cliente cliente8 = new Cliente(null, "Helena", "Lima","11910987654",
                LocalDate.parse("1989-06-15"), "Avenida das Rosas, 147", clienteUser8);

        Cliente cliente9 = new Cliente(null, "Igor", "Barros", "11898765432",
                LocalDate.parse("1994-03-29"), "Rua das Acácias, 258", clienteUser9);

        Cliente cliente10 = new Cliente(null, "Juliana", "Martins","11787654321",
                LocalDate.parse("1993-08-10"), "Avenida da Paz, 369", clienteUser10);

        clienteRepository.saveAll(Arrays.asList(cliente1, cliente2, cliente3, cliente4, cliente5, cliente6, cliente7, cliente8, cliente9, cliente10));

        Clinica clinica1 = new Clinica(null, "Clínica Saúde e Sorriso", "Rua das Flores, 123", "11987654321",
                4.5f, 150.0, clinicaUser1);

        Clinica clinica2 = new Clinica(null, "Clínica Odontológica São José", "Avenida Central, 456", "11976543210",
                4.0f, 200.0, clinicaUser2);

        Clinica clinica3 = new Clinica(null, "Clínica Dental Plus", "Rua da Esperança, 789", "11965432109",
                5.0f, 250.0, clinicaUser3);

        Clinica clinica4 = new Clinica(null, "OdontoCare", "Avenida Brasil, 321", "11954321098",
                3.8f, 180.0, clinicaUser4);

        Clinica clinica5 = new Clinica(null, "Clínica do Sorriso", "Rua das Acácias, 654", "11943210987",
                4.2f, 170.0, clinicaUser5);

        Clinica clinica6 = new Clinica(null, "Clínica OdontoMax", "Praça da Saúde, 135", "11932109876",
                4.7f, 220.0, clinicaUser6);

        Clinica clinica7 = new Clinica(null, "Centro Dental", "Alameda dos Anjos, 246", "11921098765",
                4.1f, 160.0, clinicaUser7);

        Clinica clinica8 = new Clinica(null, "Clínica Vida e Sorriso", "Rua do Progresso, 357", "11910987654",
                4.6f, 190.0, clinicaUser8);

        Clinica clinica9 = new Clinica(null, "Consultório Odontológico Esperança", "Beco do Sorriso, 888", "11909876543",
                3.9f, 175.0, clinicaUser9);

        Clinica clinica10 = new Clinica(null, "Clínica Nova Esperança", "Rua do Futuro, 101", "11998765432",
                4.8f, 210.0, clinicaUser10);


        clinicaRepository.saveAll(Arrays.asList(clinica1, clinica2, clinica3, clinica4, clinica5, clinica6, clinica7, clinica8, clinica9, clinica10));

        Especialidade especialidade1 = new Especialidade(null, "Ortodontia");
        Especialidade especialidade2 = new Especialidade(null, "Implantodontia");
        Especialidade especialidade3 = new Especialidade(null, "Odontopediatria");
        Especialidade especialidade4 = new Especialidade(null, "Periodontia");
        Especialidade especialidade5 = new Especialidade(null, "Endodontia");
        Especialidade especialidade6 = new Especialidade(null, "Odontologia Estética");
        Especialidade especialidade7 = new Especialidade(null, "Cirurgia Bucomaxilofacial");
        Especialidade especialidade8 = new Especialidade(null, "Prótese Dentária");
        Especialidade especialidade9 = new Especialidade(null, "Dentística");
        Especialidade especialidade10 = new Especialidade(null, "Higiene Dental");

        especialidadeRepository.saveAll(Arrays.asList(
                especialidade1, especialidade2, especialidade3, especialidade4, especialidade5,
                especialidade6, especialidade7, especialidade8, especialidade9, especialidade10
        ));

        Dentista dentista1 = new Dentista(null, "Carlos", "Menezes", "11987654321", clinica1, especialidade1,
                4.5f, dentistaUser1);

        Dentista dentista2 = new Dentista(null, "Fernanda", "Pereira", "11976543210", clinica1, especialidade2,
                5.0f, dentistaUser2);

        Dentista dentista3 = new Dentista(null, "Roberto", "Almeida", "11965432109", clinica2, especialidade3,
                4.0f, dentistaUser3);

        Dentista dentista4 = new Dentista(null, "Mariana", "Souza", "11954321098", clinica2, especialidade4,
                4.7f, dentistaUser4);

        Dentista dentista5 = new Dentista(null, "André", "Lima", "11943210987", clinica1, especialidade5,
                3.5f, dentistaUser5);

        Dentista dentista6 = new Dentista(null, "Juliana", "Silva", "11932109876", clinica3, especialidade6,
                4.8f, dentistaUser6);

        Dentista dentista7 = new Dentista(null, "Ricardo", "Santos", "11921098765", clinica3, especialidade7,
                4.2f, dentistaUser7);

        Dentista dentista8 = new Dentista(null, "Aline", "Costa", "11910987654", clinica1, especialidade8,
                4.6f, dentistaUser8);

        Dentista dentista9 = new Dentista(null, "Felipe", "Martins", "11909876543", clinica2, especialidade9,
                3.9f, dentistaUser9);

        Dentista dentista10 = new Dentista(null, "Tatiane", "Ferreira", "11998765432", clinica3, especialidade10,
                5.0f, dentistaUser10);


        dentistaRepository.saveAll(Arrays.asList(
                dentista1, dentista2, dentista3, dentista4, dentista5,
                dentista6, dentista7, dentista8, dentista9, dentista10
        ));

        Consulta consulta1 = new Consulta(null, cliente1, clinica1, dentista1, "Check-up", LocalDateTime.parse("2024-10-01T10:00:00"), 'S', "Consulta de rotina", "Nenhum", "Manter higiene bucal", 150.0d, "Fluoretação", LocalDate.parse("2024-10-15"));
        Consulta consulta2 = new Consulta(null, cliente2, clinica1, dentista1, "Limpeza", LocalDateTime.parse("2024-10-02T11:00:00"), 'S', "Limpeza dental regular", "Acúmulo de tártaro", "Limpeza profunda", 200.0d, "Nenhum", LocalDate.parse("2024-10-16"));
        Consulta consulta3 = new Consulta(null, cliente3, clinica2, dentista2, "Consulta de emergência", LocalDateTime.parse("2024-10-03T14:00:00"), 'S', "Dor de dente", "Dor intensa", "Tratamento de canal", 500.0d, "Analgésicos", LocalDate.parse("2024-10-20"));
        Consulta consulta4 = new Consulta(null, cliente4, clinica2, dentista2, "Check-up", LocalDateTime.parse("2024-10-04T09:00:00"), 'S', "Consulta de rotina", "Sem queixas", "Manter cuidados", 150.0d, "Fluoretação", LocalDate.parse("2024-10-18"));
        Consulta consulta5 = new Consulta(null, cliente5, clinica3, dentista3, "Limpeza", LocalDateTime.parse("2024-10-05T16:00:00"), 'N', "Limpeza programada", "Alguma dor", "Limpeza e acompanhamento", 220.0d, "Analgésicos", LocalDate.parse("2024-10-22"));
        Consulta consulta6 = new Consulta(null, cliente6, clinica3, dentista3, "Consulta de emergência", LocalDateTime.parse("2024-10-06T15:00:00"), 'S', "Dor na gengiva", "Dor constante", "Antibióticos", 300.0d, "Antibióticos", LocalDate.parse("2024-10-23"));
        Consulta consulta7 = new Consulta(null, cliente7, clinica1, dentista1, "Check-up", LocalDateTime.parse("2024-10-07T13:00:00"), 'S', "Consulta de rotina", "Nenhum", "Manter higiene bucal", 150.0d, "Fluoretação", LocalDate.parse("2024-10-24"));
        Consulta consulta8 = new Consulta(null, cliente8, clinica1, dentista1, "Limpeza", LocalDateTime.parse("2024-10-08T10:30:00"), 'N', "Limpeza agendada", "Acúmulo de tártaro", "Limpeza profunda", 200.0d, "Nenhum", LocalDate.parse("2024-10-25"));
        Consulta consulta9 = new Consulta(null, cliente9, clinica2, dentista2, "Consulta de emergência", LocalDateTime.parse("2024-10-09T14:00:00"), 'S', "Dor de dente", "Dor intensa", "Tratamento de canal", 500.0d, "Analgésicos", LocalDate.parse("2024-10-30"));
        Consulta consulta10 = new Consulta(null, cliente10, clinica3, dentista3, "Check-up", LocalDateTime.parse("2024-10-10T11:00:00"), 'S', "Consulta de rotina", "Sem queixas", "Manter cuidados", 150.0d, "Fluoretação", LocalDate.parse("2024-10-31"));

        consultaRepository.saveAll(Arrays.asList(
                consulta1, consulta2, consulta3, consulta4, consulta5,
                consulta6, consulta7, consulta8, consulta9, consulta10
        ));

        Sinistro sinistro1 = new Sinistro(null, consulta1, "Sinistro 1", "Quebra de dente durante a consulta", 'S', "Aguardando documentação", 1000.0d, LocalDate.parse("2024-10-01"), null, "documento1.pdf");
        Sinistro sinistro2 = new Sinistro(null, consulta2, "Sinistro 2", "Reação alérgica após tratamento", 'S', "Em análise", 800.0d, LocalDate.parse("2024-10-02"), null, "documento2.pdf");
        Sinistro sinistro3 = new Sinistro(null, consulta3, "Sinistro 3", "Dor intensa após procedimento", 'N', "Resolvido", 500.0d, LocalDate.parse("2024-10-03"), LocalDate.parse("2024-10-10"), "documento3.pdf");
        Sinistro sinistro4 = new Sinistro(null, consulta4, "Sinistro 4", "Infeção após extração", 'S', "Aguardando avaliação", 1200.0d, LocalDate.parse("2024-10-04"), null, "documento4.pdf");
        Sinistro sinistro5 = new Sinistro(null, consulta5, "Sinistro 5", "Lesão na gengiva", 'S', "Aguardando resposta do dentista", 300.0d, LocalDate.parse("2024-10-05"), null, "documento5.pdf");
        Sinistro sinistro6 = new Sinistro(null, consulta6, "Sinistro 6", "Complicação com anestesia", 'N', "Resolvido", 600.0d, LocalDate.parse("2024-10-06"), LocalDate.parse("2024-10-11"), "documento6.pdf");
        Sinistro sinistro7 = new Sinistro(null, consulta7, "Sinistro 7", "Queixa de dor persistente", 'S', "Em avaliação", 400.0d, LocalDate.parse("2024-10-07"), null, "documento7.pdf");
        Sinistro sinistro8 = new Sinistro(null, consulta8, "Sinistro 8", "Tratamento de canal falhou", 'S', "Aguardando revisão", 900.0d, LocalDate.parse("2024-10-08"), null, "documento8.pdf");
        Sinistro sinistro9 = new Sinistro(null, consulta9, "Sinistro 9", "Problema na prótese", 'N', "Resolvido", 700.0d, LocalDate.parse("2024-10-09"), LocalDate.parse("2024-10-15"), "documento9.pdf");
        Sinistro sinistro10 = new Sinistro(null, consulta10, "Sinistro 10", "Queixa de mau hálito persistente", 'S', "Aguardando consulta adicional", 200.0d, LocalDate.parse("2024-10-10"), null, "documento10.pdf");

        sinistroRepository.saveAll(Arrays.asList(
                sinistro1, sinistro2, sinistro3, sinistro4, sinistro5,
                sinistro6, sinistro7, sinistro8, sinistro9, sinistro10
        ));

        Feedback feedback1 = new Feedback(null, cliente1, dentista1, clinica1, 5.0f, "Ótimo atendimento!");
        Feedback feedback2 = new Feedback(null, cliente2, dentista1, clinica1, 4.0f, "A consulta foi muito boa.");
        Feedback feedback3 = new Feedback(null, cliente3, dentista2, clinica2, 5.0f, "Profissional atencioso e cuidadoso.");
        Feedback feedback4 = new Feedback(null, cliente4, dentista2, clinica2, 4.0f, "Ambiente agradável.");
        Feedback feedback5 = new Feedback(null, cliente5, dentista3, clinica3, 3.5f, "Esperava mais, mas foi tudo bem.");
        Feedback feedback6 = new Feedback(null, cliente6, dentista3, clinica3, 4.0f, "Consulta rápida e eficaz.");
        Feedback feedback7 = new Feedback(null, cliente7, dentista1, clinica1, 5.0f, "Recomendo para todos!");
        Feedback feedback8 = new Feedback(null, cliente8, dentista2, clinica2, 4.5f, "Foi uma experiência positiva.");
        Feedback feedback9 = new Feedback(null, cliente9, dentista1, clinica1, 3.5f, "Um pouco de espera, mas no geral bom.");
        Feedback feedback10 = new Feedback(null, cliente10, dentista3, clinica3, 5.0f, "Atendimento de qualidade.");

        feedbackRepository.saveAll(Arrays.asList(
                feedback1, feedback2, feedback3, feedback4, feedback5,
                feedback6, feedback7, feedback8, feedback9, feedback10
        ));
        System.out.println("Banco Criado com Sucesso!");
    }
}
