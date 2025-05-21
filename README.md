
# **Challenge OdontoPrev**
[![Sync GitHub Repo to Azure DevOps](https://github.com/patinaomi/projeto-java-thymeleaf/actions/workflows/sync-to-azure-devops.yml/badge.svg)](https://github.com/patinaomi/projeto-java-thymeleaf/actions/workflows/sync-to-azure-devops.yml)

## 📌 **Índice**
1. [Sobre o Projeto](#-sobre-o-projeto)
2. [Implementações Java – Sprint 4](#️-implementações-java--sprint-4)
3. [Implementações DevOps – Sprint 4](#️-implementações-devops--sprint-4)
4. [Vídeos Demonstrativos](#-vídeos-demonstrativos)
5. [Arquitetura do Projeto](#-arquitetura-do-projeto)
6. [Configuração das Pipelines](#-configuração-das-pipelines)
7. [Rodando o Projeto com Docker](#-rodando-o-projeto-com-docker)
8. [Equipe](#-equipe)


## 💡 **Sobre o Projeto**
O sistema é uma aplicação Java com Spring Boot, desenvolvida para gerenciar clínicas odontológicas, com recursos para cadastro de dentistas, clínicas e feedbacks. Foi implementado um ambiente DevOps com CI/CD utilizando **Azure DevOps** e deploy em **na nuvem**. O banco de dados é **Oracle**, também hospedado em container Docker.

#### **⚙️ Funcionalidades principais**
✅ Cadastro e gerenciamento de dentistas, clínicas e consultas.  
✅ Recebimento e gestão de feedbacks dos atendimentos.  
✅ Dashboard moderno e responsivo para facilitar a navegação.

[:arrow_up: voltar para o índice :arrow_up:](#-índice)

## ⚙️ Implementações Java – Sprint 4
Nesta 4ª Sprint, a aplicação Java foi finalizada com arquitetura **full MVC**, contemplando os requisitos funcionais propostos:

-   🔐 **Autenticação e Autorização**  
    Implementação do **Spring Security**, com autenticação baseada em formulário e controle de acesso por **perfis de usuário (roles)**, permitindo acesso diferenciado para clínicas e dentistas.

-   🌐 **Internacionalização**  
    A aplicação conta com suporte a múltiplos idiomas por meio do recurso de internacionalização do Spring. Os arquivos `.properties` foram configurados para exibir textos em português e inglês de forma dinâmica.

-   📬 **Mensageria com RabbitMQ**  
    Integração com o **RabbitMQ** para envio e consumo de mensagens assíncronas, como no envio de e-mails de cadastro. A configuração foi realizada com produtores e consumidores devidamente registrados.

-   📊 **Monitoramento com Spring Boot Actuator**  
    Endpoints de monitoramento e métricas foram habilitados com o **Spring Boot Actuator**, permitindo acompanhar a saúde da aplicação, consumo de recursos e estatísticas de uso.

-   🤖 **Inteligência Artificial com Spring AI**  
    Recursos de IA foram adicionados utilizando o **Spring AI** com integração à API da OpenAI (GPT-3.5 Turbo). O sistema é capaz de **gerar resumos automáticos de feedbacks dos pacientes**, proporcionando insights inteligentes para as clínicas.

[:arrow_up: voltar para o índice :arrow_up:](#-índice)

## ⚙️ Implementações DevOps – Sprint 4

Nesta sprint, foi implementada uma esteira completa de **CI/CD** utilizando o **Azure DevOps**, com o objetivo de automatizar o build, push e deploy da aplicação na nuvem.

-   🚀 **Integração Contínua (CI)**  
    A pipeline é disparada automaticamente a cada push no repositório Git. Ela realiza o `build` do projeto com Maven, empacota a aplicação como **imagem Docker** e faz o push para o **Azure Container Registry (ACR)**.

-   📦 **Entrega Contínua (CD)**  
    Após a publicação da imagem no ACR, a pipeline de CD realiza o deploy automático para o **Azure Container Instance (ACI)**, expondo a aplicação com **IP público**, tornando possível acessá-la diretamente pelo navegador.

-   🔄 **Automação e Rastreabilidade**  
    O processo automatizado proporciona **velocidade, confiabilidade e versionamento controlado**, além de facilitar testes e validações em ambiente de produção.

[:arrow_up: voltar para o índice :arrow_up:](#-índice)

## 🎥 **Vídeos Demonstrativos**
Disponibilizamos dois vídeos para apresentar as etapas e funcionalidades do projeto:
### 📦 _1. DevOps: CI/CD com Azure DevOps_

Neste vídeo, mostramos todo o fluxo da esteira de integração e entrega contínua:
-   Push no repositório Git
-   Execução da pipeline de CI (build da imagem Docker e push no Azure Container Registry)
-   Execução da pipeline de CD (deploy automático no Azure Container Instances)
-   Verificação do container em execução e acesso via IP público

📌 **Assista aqui:**  
[▶ CI/CD no Azure DevOps](https://youtu.be/P0mPnDin5T0)

----------
### 💻 _2. Demonstração do Sistema Java_
Este vídeo apresenta as funcionalidades principais da aplicação:
-   Login e navegação com interface desenvolvida em Thymeleaf
-   Arquitetura final da solução
-   Integrações com RabbitMQ e OpenAI

📌 **Assista aqui:**  
[▶ Demonstração do Sistema Java](COLOCAR LINK AQUI)

Credenciais usadas para testes:

-   `clinica1@email.com` / `senha123`
-   `dentista1@email.com` / `senha123`


[:arrow_up: voltar para o índice :arrow_up:](#-índice)

## 🏛️ **Arquitetura do Projeto**

![arquitetura do projeto](https://github.com/patinaomi/projeto-java-thymeleaf/blob/main/diagrama.drawio.png)

A arquitetura do projeto foi desenvolvida com foco em escalabilidade, monitoramento e integração contínua. Utilizando o Azure DevOps, o pipeline de CI/CD automatiza o processo de build e deploy da aplicação. A imagem Docker é gerada a partir do código Java com Spring Boot, enviada para o Azure Container Registry e, em seguida, implantada no Azure Container Instance como um container acessível por IP público. A aplicação se comunica com um banco de dados Oracle, hospedado em container, e com o RabbitMQ como serviço de mensageria para eventos assíncronos, como o envio de e-mails. O monitoramento é realizado com Prometheus e Grafana, também em containers, permitindo o acompanhamento em tempo real do estado e métricas da aplicação. Toda a estrutura é pensada para proporcionar uma experiência DevOps completa e robusta.

[:arrow_up: voltar para o índice :arrow_up:](#-índice)


---

## 💡 **Configuração das Pipelines**
#### 🛠️ Configuração da Pipeline de CI (Continuous Integration)

Na pipeline de **Integração Contínua (CI)**, configurada no Azure DevOps, foi definido o uso do `pool` padrão do Azure Pipelines para execução das tarefas. O processo automatiza a construção da imagem Docker da aplicação Java. Primeiramente, a tarefa `Docker Build an image` é responsável por **compilar o projeto** e gerar a imagem Docker a partir do `Dockerfile` localizado na pasta `challenge/`. Em seguida, a tarefa `Push an image` faz o **envio da imagem criada para o Azure Container Registry (ACR)**, utilizando as credenciais fornecidas na configuração. A imagem recebe a tag correspondente ao número do build, garantindo versionamento e rastreabilidade para futuros deploys.
👉 [`link do código de ci-pipeline.yml`](https://github.com/patinaomi/projeto-java-thymeleaf/blob/main/challenge/ci-pipeline.yml)
#### 🚀 Pipeline de CD (Entrega Contínua)

A pipeline de **Continuous Deployment (CD)** foi configurada no **Azure DevOps** com o objetivo de automatizar o processo de publicação da aplicação Java na nuvem. Após a conclusão da etapa de build e push da imagem Docker (na pipeline de CI), a pipeline de CD é acionada para realizar o deploy da imagem no serviço **Azure Container Instance (ACI)**.

O processo segue os seguintes passos:

1.  **Autenticação na conta Azure** via Azure CLI.

2.  **Criação do container** com base na imagem gerada e armazenada no **Azure Container Registry (ACR)**.

3.  O container é criado com IP público, permitindo o acesso via navegador.

4.  A aplicação roda em ambiente Linux e é configurada para escutar na porta 8080.

    👉 [`link do código de cd-pipeline.yml`](https://github.com/patinaomi/projeto-java-thymeleaf/blob/main/challenge/cd-pipeline.yml)

[:arrow_up: voltar para o índice :arrow_up:](#-índice)

## 🐳 Rodando o Projeto com Docker

Para rodar a aplicação completa com **Spring Boot + Oracle + RabbitMQ + Prometheus + Grafana**, siga os passos abaixo:

### 📁 Pré-requisitos

-   Docker instalado (link de instalação)
-   Docker Compose instalado (link de instalação)

### 🐑 1. Clone o repositório
```
git clone https://github.com/patinaomi/projeto-java-thymeleaf
cd projeto-java-thymeleaf
```

### ⚙️ 2. Configure o arquivo `.env`

Crie um arquivo chamado `.env` na raiz do projeto com o seguinte conteúdo (ajuste os valores conforme suas credenciais):
```ORACLE_USER=seu-usuario
ORACLE_PASS=sua-senha
OPENAI_KEY=sua-chave-openai-aqui
JWT_SECRET_KEY=sua-chave-jwt-aqui
EMAIL_USER=seuemail@gmail.com
EMAIL_PASSWORD=sua-senha-email-smpt-aqui
```
### 🚀 3. Suba os containers
Na raiz do projeto, execute o seguinte comando:
``docker compose up --build
``

Esse comando irá:
-   Construir a imagem do projeto Java.
-   Subir os containers do **RabbitMQ**, **Oracle** (caso esteja local), **Prometheus** e **Grafana**.
-   Inicializar a aplicação acessível em:  
    👉 [`http://localhost:8080`](http://localhost:8080)
### 📊 Interfaces disponíveis
|Serviço|URL|
|--|--|
|🌐 Aplicação|[http://localhost:8080](http://localhost:8080)|
|🐰 RabbitMQ|[http://localhost:15672](http://localhost:15672) _(user: guest / guest)_|
|📊 Prometheus|[http://localhost:9090](http://localhost:9090)|
|📈 Grafana|[http://localhost:3000](http://localhost:3000) _(login padrão: admin / admin)_|

### 🛠️ Inicialização do Banco de Dados
Ao rodar o projeto pela primeira vez, é necessário garantir que o banco de dados seja criado automaticamente. Para isso, o projeto deve ser executado com a propriedade spring.jpa.hibernate.ddl-auto configurada como create. Isso fará com que todas as tabelas definidas pelas entidades JPA sejam geradas automaticamente no banco Oracle. Após a criação inicial, recomenda-se alterar esse modo para update ou none, evitando a recriação acidental do schema em execuções futuras.

[:arrow_up: voltar para o índice :arrow_up:](#-índice)
## 🧑‍🤝‍🧑 Equipe

| <h3>Claudio Bispo</h3><img src="https://avatars.githubusercontent.com/u/110735259?v=4" width=180px> <h6>RM553472</h6> <a href="https://github.com/claubis"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"></a> <a href="https://www.linkedin.com/in/claudiosbispo"><img src="https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white"></a> <a href="https://www.instagram.com/_claudiobispo/"><img src="https://img.shields.io/badge/Instagram-%23E4405F.svg?style=for-the-badge&logo=Instagram&logoColor=white"></a>|<h3>Patricia Naomi</h3> <img src="https://avatars.githubusercontent.com/u/132932532?v=4" width=180px><h6>RM552981</h6> <a href="https://github.com/patinaomi"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"></a> <a href="https://www.linkedin.com/in/patinaomi/"><img src="https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white"></a> <a href="https://www.instagram.com/naomipati/"><img src="https://img.shields.io/badge/Instagram-%23E4405F.svg?style=for-the-badge&logo=Instagram&logoColor=white"></a>|
|--|--|


[:arrow_up: voltar para o índice :arrow_up:](#-índice)

