# **Challenge OdontoPrev**
<p align="center">
    <img loading="lazy" src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge"/>
</p>

## 📌 **Índice**
1. [Sobre o Projeto](#-sobre-o-projeto)  
2. [Pipeline CI/CD - Azure DevOps](#-pipeline-cicd---azure-devops)  
3. [Configuração das Pipelines](#-configuração-das-pipelines)  
4. [Modelo Relacional (DER)](#-modelo-relacional-der)  
5. [Diagrama de Classes](#-diagrama-de-classes)  
6. [Vídeo Demonstrativo](#-vídeo-demonstrativo)  
7. [Banco de Dados](#-banco-de-dados)  
8. [Equipe](#-equipe)  


## 💡 **Sobre o Projeto**
O sistema é uma aplicação Java com Spring Boot, desenvolvida para gerenciar clínicas odontológicas, com recursos para cadastro de clientes, dentistas, clínicas, consultas e feedbacks.
Foi implementado um ambiente DevOps com CI/CD utilizando **Azure DevOps** e deploy em **na nuvem**. O banco de dados é **PostgreSQL**, também hospedado em container Docker.

### **⚙️ Funcionalidades principais**
✅ Cadastro e gerenciamento de pacientes, dentistas e clínicas.  
✅ Controle de consultas.  
✅ Recebimento e gestão de feedbacks dos atendimentos.  
✅ Dashboard moderno e responsivo para facilitar a navegação.  


[:arrow_up: voltar para o índice :arrow_up:](#-índice)


## 🚀 **Pipeline CI/CD - Azure DevOps**
A pipeline foi configurada no **Azure DevOps** com foco na entrega contínua da aplicação Java em um ambiente Docker hospedado em uma VM na nuvem.

Abaixo, o detalhamento de cada etapa:

-   **Commit da Mudança**  
    Desenvolvedor faz commit/push no repositório GitHub.
    
-   **Disparo da Build**  
    Azure DevOps detecta mudança e inicia a pipeline automaticamente.
    
-   **Build da Aplicação**  
    Executa `mvn clean package` para compilar o projeto Java Spring Boot.
    
-   **Notificação de Build**  
    Azure DevOps registra o sucesso ou falha da etapa de build.
    
-   **Execução dos Testes**  
    Executa testes automatizados (se implementados).
    
-   **Notificação de Testes**  
    Informa se os testes passaram ou falharam.
    
-   **Deploy do Build**  
    A imagem Docker gerada é enviada ao Docker Hub e usada para subir a aplicação na VM da Azure via SSH.
    
-   **Disponibilização do Sistema**  
    Aplicação é exposta via WebApp e conectada ao banco PostgreSQL.


[:arrow_up: voltar para o índice :arrow_up:](#-índice)


---

## 💡 **Configuração das Pipelines**
Arquivo YAML da pipeline no Azure
```
pool: name: Azure Pipelines steps: - task: Docker@0 displayName: 'Docker Build an image' inputs: azureSubscription: 'Azure for Students (a2ca17c1-13e6-4923-baf9-1ee48e8c4ad7)' azureContainerRegistry: '{"loginServer":"acrdelfosmachine.azurecr.io", "id" : "/subscriptions/a2ca17c1-13e6-4923-baf9-1ee48e8c4ad7/resourceGroups/delfos-machine-sprint/providers/Microsoft.ContainerRegistry/registries/acrdelfosmachine"}' dockerFile: challenge/Dockerfile imageName: 'Fiap/delfosmachine:$(Build.BuildNumber)' - task: Docker@0 displayName: 'Push an image' inputs: azureSubscription: 'Azure for Students (a2ca17c1-13e6-4923-baf9-1ee48e8c4ad7)' azureContainerRegistry: '{"loginServer":"acrdelfosmachine.azurecr.io", "id" : "/subscriptions/a2ca17c1-13e6-4923-baf9-1ee48e8c4ad7/resourceGroups/delfos-machine-sprint/providers/Microsoft.ContainerRegistry/registries/acrdelfosmachine"}' action: 'Push an image' imageName: 'Fiap/delfosmachine:$(Build.BuildNumber)'
```


 **docker-compose.yml**
 ```# Etapa de build
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app

# Copia o arquivo de configuração Maven e instala as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código e executa o build
COPY . .
RUN mvn clean install -DskipTests

# Etapa final - Imagem otimizada com JRE 21
FROM eclipse-temurin:21-jre
WORKDIR /app
EXPOSE 8080

# Copia o JAR gerado na etapa de build
COPY --from=build /app/target/challenge-0.0.1-SNAPSHOT.jar app.jar

# Comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]
```

 **dockerfile**
 ```
# Copia o arquivo de configuração Maven e instala as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código e executa o build
COPY . .
RUN mvn clean install -DskipTests

# Etapa final - Imagem otimizada com JRE 21
FROM eclipse-temurin:21-jre
WORKDIR /app
EXPOSE 8080

# Copia o JAR gerado na etapa de build
COPY --from=build /app/target/challenge-0.0.1-SNAPSHOT.jar app.jar

# Comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]

```

[:arrow_up: voltar para o índice :arrow_up:](#-índice)


---
## 📊 **Modelo Relacional (DER)**
A modelagem relacional segue a estrutura abaixo para armazenar os dados de clientes, dentistas e feedbacks.

![Modelo Relacional](Relational_1.png)


[:arrow_up: voltar para o índice :arrow_up:](#-índice)


---

## 📌 **Diagrama de Classes**
O diagrama de classes abaixo representa a arquitetura do projeto e os relacionamentos entre as entidades.

![Diagrama de Classes](diagrama-de-classes.png)  
![Domains](domains.png)


[:arrow_up: voltar para o índice :arrow_up:](#-índice)


---

## 🎥 **Vídeo Demonstrativo**
Disponibilizamos um **vídeo no YouTube** apresentando a aplicação, as funcionalidades implementadas e o fluxo de uso.
O vídeo mostra:
-   Commit em branch
-   Pipeline rodando no Azure DevOps
-   Container sendo executado
-   Acesso ao sistema pela URL pública
-   Inserção de dados e persistência no banco PostgreSQL

📌 **Acesse o vídeo aqui:**  
[▶ Assista no YouTube](https://www.youtube.com/watch?v=A3Tw0jTuy60&ab_channel=PatriciaNaomi)

**Observação**: O sistema foi testado com persistência de dados ativa e acesso ao WebApp funcional. A aplicação é protegida com Spring Security e autenticação via formulário, e o frontend foi customizado com Thymeleaf e CSS.


Credenciais padrão:

-   `clinica1@email.com` / `senha123`
-   `dentista1@email.com` / `senha123`


[:arrow_up: voltar para o índice :arrow_up:](#-índice)


## 💡 **Banco de Dados**
-   Banco relacional PostgreSQL
-   Contêiner gerenciado pelo Docker
-   Persistência dos dados testada via API e visualizada no sistema

O banco de dados utiliza várias tabelas relacionais, incluindo `Cliente`, `Dentista`, `Clínica`, `Consulta`, `Feedback`, `Sinistro`, entre outras. Todas as entidades estão conectadas por meio de relacionamentos com chave estrangeira.

-   Relações implementadas:
    -   Cliente → Consulta → Dentista
    -   Dentista → Clínica
    -   Clínica → Feedback


[:arrow_up: voltar para o índice :arrow_up:](#-índice)


---
## 🧑‍🤝‍🧑 Equipe

| <h3>Claudio Bispo</h3><img src="https://avatars.githubusercontent.com/u/110735259?v=4" width=180px> <h6>RM553472</h6> <a href="https://github.com/claubis"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"></a> <a href="https://www.linkedin.com/in/claudiosbispo"><img src="https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white"></a> <a href="https://www.instagram.com/_claudiobispo/"><img src="https://img.shields.io/badge/Instagram-%23E4405F.svg?style=for-the-badge&logo=Instagram&logoColor=white"></a>|<h3>Patricia Naomi</h3> <img src="https://avatars.githubusercontent.com/u/132932532?v=4" width=180px><h6>RM552981</h6> <a href="https://github.com/patinaomi"><img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white"></a> <a href="https://www.linkedin.com/in/patinaomi/"><img src="https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white"></a> <a href="https://www.instagram.com/naomipati/"><img src="https://img.shields.io/badge/Instagram-%23E4405F.svg?style=for-the-badge&logo=Instagram&logoColor=white"></a>|
|--|--|


[:arrow_up: voltar para o índice :arrow_up:](#-índice)

