-----

# API Voll.med

### Projeto desenvolvido durante os cursos da Alura

-----

## 📋 Sobre o Projeto

**Voll.med** é uma API RESTful desenvolvida em Java com Spring Boot, que simula o back-end de uma clínica médica. A aplicação gerencia o cadastro de médicos e pacientes e permite o agendamento e cancelamento de consultas, aplicando diversas regras de negócio para garantir a integridade e a consistência dos dados.

Este projeto foi construído como parte da formação back-end da Alura, aplicando conceitos de desenvolvimento de APIs, validações, persistência de dados com JPA/Hibernate e boas práticas de programação.

-----

## ✨ Funcionalidades Principais

  - **CRUD de Médicos:** Cadastro, listagem, atualização e exclusão lógica de médicos.
  - **CRUD de Pacientes:** Cadastro, listagem, atualização e exclusão lógica de pacientes.
  - **Agendamento de Consultas:** Endpoint para agendar novas consultas, aplicando todas as regras de negócio.
  - **Cancelamento de Consultas:** Endpoint para cancelar consultas já registradas, aplicando todas as regras de negócio.
  - **Validações de Negócio:** Sistema robusto de validações para garantir a qualidade dos agendamentos.

-----

## 🛠️ Tecnologias Utilizadas

  - **Java 17:** Linguagem de programação principal.
  - **Spring Boot 3:** Framework para criação da API.
  - **Spring Web:** Módulo para criação de APIs REST.
  - **Spring Data JPA:** Para persistência de dados e comunicação com o banco de dados.
  - **Lombok:** Para reduzir o código boilerplate nas entidades e DTOs.
  - **PostgreSQL:** Banco de dados relacional para produção.
  - **Flyway:** Ferramenta para versionamento e migração de banco de dados.
  - **Maven:** Gerenciador de dependências do projeto.

-----

## 🚀 Como Executar o Projeto

**1. Clone o repositório:**

```bash
git clone https://seurepositorio.com/voll-med-api.git
```

**2. Configure o Banco de Dados:**

  - Crie um banco de dados PostgreSQL.
  - Configure as variáveis de ambiente ou altere o arquivo `src/main/resources/application.properties` com as suas credenciais de acesso:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost/vollmed_api
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    ```

**3. Execute a aplicação:**

  - Abra o projeto em sua IDE preferida (IntelliJ, VSCode, etc).
  - Execute a classe principal `ApiApplication.java` ou utilize o Maven no terminal:
    ```bash
    mvn spring-boot:run
    ```

-----

## 📄 Regras de Negócio Implementadas

A funcionalidade de agendamento de consultas valida as seguintes regras:

1.  **Horário de Funcionamento:** A clínica funciona de segunda a sábado, das 07:00 às 19:00.
2.  **Duração da Consulta:** As consultas têm duração fixa de 1 hora.
3.  **Antecedência Mínima:** O agendamento deve ser feito com no mínimo 30 minutos de antecedência.
4.  **Paciente Ativo:** Não é permitido agendar consultas para pacientes inativos.
5.  **Médico Ativo:** Não é permitido agendar consultas com médicos inativos.
6.  **Disponibilidade do Paciente:** Um paciente não pode agendar mais de uma consulta no mesmo dia.
7.  **Disponibilidade do Médico:** Um médico não pode ter mais de uma consulta agendada no mesmo horário.
8.  **Escolha Opcional do Médico:** Se o médico não for especificado, o sistema escolhe aleatoriamente um médico que esteja disponível no horário solicitado.

-----
