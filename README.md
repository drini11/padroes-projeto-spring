# Lab Padrões de Projeto - Spring Boot

Projeto desenvolvido durante o curso **Santander Java AI Back-end**, com melhorias implementadas por conta própria para fins de aprendizado e entrega.

## 📖 Sobre o projeto

API REST para cadastro de clientes, que consulta automaticamente o endereço a partir do CEP informado, utilizando a API pública [ViaCEP](https://viacep.com.br/).

O projeto aplica os padrões de projeto:
- **Strategy** — abstração da consulta de CEP via `ViaCepService`
- **Facade** — `ClienteService` centraliza a orquestração entre repositórios e o serviço de CEP

## 🚀 Melhorias implementadas

Além do conteúdo original do curso, adicionei:

- **Validação de dados de entrada** com Bean Validation (`@NotBlank`, `@Pattern`), incluindo validação em cascata (`@Valid`) no objeto `Endereco` aninhado dentro de `Cliente`.
- **Tratamento global de exceções** com `@RestControllerAdvice`, retornando uma lista estruturada de erros (campo + mensagem) em vez da resposta genérica padrão do Spring.

## 🛠️ Tecnologias

- Java 11
- Spring Boot 2.5.4
- Spring Data JPA
- Spring Web
- OpenFeign
- H2 Database
- Springdoc OpenAPI (Swagger)

## ▶️ Como executar

\`\`\`bash
./mvnw spring-boot:run
\`\`\`

A aplicação sobe em `http://localhost:8080`.

Documentação interativa (Swagger UI) disponível em:
`http://localhost:8080/swagger-ui.html`

## 📋 Endpoints principais

| Método | Rota            | Descrição                  |
|--------|-----------------|-----------------------------|
| GET    | /clientes        | Lista todos os clientes     |
| GET    | /clientes/{id}   | Busca cliente por ID        |
| POST   | /clientes        | Cadastra um novo cliente    |
| PUT    | /clientes/{id}   | Atualiza um cliente         |
| DELETE | /clientes/{id}   | Remove um cliente           |

## 📌 Exemplo de requisição (POST /clientes)

\`\`\`json
{
  "nome": "João da Silva",
  "endereco": {
    "cep": "01001000"
  }
}
\`\`\`

## ⚠️ Exemplo de resposta de erro de validação

\`\`\`json
[
  {
    "campo": "nome",
    "mensagem": "O nome é obrigatório!"
  },
  {
    "campo": "endereco.cep",
    "mensagem": "O CEP deve conter 8 dígitos numéricos, sem hífen"
  }
]
\`\`\`
