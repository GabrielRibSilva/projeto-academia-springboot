# API de Academia

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![Status](https://img.shields.io/badge/Status-Concluído-brightgreen)

API RESTful desenvolvida em Spring Boot para o gerenciamento completo de uma academia, permitindo o controle de alunos, planos, treinos e pagamentos. Este projeto foi criado como uma solução robusta para aplicar conceitos de arquitetura em camadas, relacionamentos JPA, boas práticas de API REST e documentação automática.

## Funcionalidades

-   ✅ **Gerenciamento de Alunos:** Cadastro, consulta, atualização e inativação (exclusão lógica) de alunos.
-   ✅ **Gerenciamento de Planos:** CRUD completo para os planos oferecidos pela academia.
-   ✅ **Gerenciamento de Treinos:** CRUD para treinos, com associação flexível a múltiplos alunos (`ManyToMany`).
-   ✅ **Registro de Pagamentos:** Sistema para registrar pagamentos de alunos, com data automática e status.
-   ✅ **Regras de Negócio:** Validações implementadas, como a proibição de excluir treinos associados a alunos.
-   ✅ **Documentação:** API totalmente documentada com Swagger/OpenAPI para fácil visualização e teste dos endpoints.

## Tecnologias Utilizadas

-   **Java 17**
-   **Spring Boot 3.x**
-   **Spring Data JPA** (com Hibernate)
-   **H2 Database** (Banco de dados em memória/arquivo)
-   **Maven** (Gerenciador de dependências)
-   **Springdoc OpenAPI** (Para geração da documentação Swagger UI)
-   **Bean Validation** (Para validação dos dados de entrada)

## Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:
* [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior
* [Maven 3.8](https://maven.apache.org/download.cgi) ou superior
* [Git](https://git-scm.com/downloads)

## Como Rodar o Projeto

Siga os passos abaixo para executar o projeto localmente:

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/GabrielRibSilva/projeto-academia-springboot.git](https://github.com/GabrielRibSilva/projeto-academia-springboot.git)
    ```

2.  **Acesse a pasta do projeto:**
    ```bash
    cd projeto-academia-springboot
    ```

3.  **Execute a aplicação com o Maven:**
    ```bash
    mvn spring-boot:run
    ```

A aplicação será iniciada e estará disponível em `http://localhost:8080`.

## Documentação da API (Swagger)

Após iniciar a aplicação, a documentação interativa da API estará disponível no seu navegador através do Swagger UI.

-   **URL:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Você pode usar essa interface para visualizar todos os endpoints, seus parâmetros, e testá-los diretamente.

https://prnt.sc/tMzkiN-Wm4l1
https://prnt.sc/rYlchlS046Y3
https://prnt.sc/si3cjtToWaYr
https://prnt.sc/PMUy1OML096l
https://prnt.sc/yyTx4ejf5XNb
https://prnt.sc/sC4QjwHoGYwU
https://prnt.sc/ybBbvt6xQA1k
https://prnt.sc/U4sObrHIwxCM
https://prnt.sc/QdsHgt6M6xZe
https://prnt.sc/AEAwEcZLiNd1
https://prnt.sc/zmd1RI1Xdj2_
https://prnt.sc/kj2Jys1BGrcT

## Endpoints da API

A API está versionada sob o prefixo `/api/v1`.

### Alunos (`/api/v1/alunos`)

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/` | Cria um novo aluno. |
| `GET` | `/` | Lista todos os alunos. |
| `GET` | `/{id}` | Busca um aluno por ID. |
| `PUT` | `/{id}` | Atualiza os dados de um aluno. |
| `DELETE` | `/{id}` | Inativa um aluno (exclusão lógica). |
| `POST` | `/{alunoId}/treinos` | Associa um treino a um aluno. |

**Exemplo de corpo para `POST /`:**
```json
{
  "nome": "João da Silva",
  "cpf": "12345678901",
  "planoId": 1
}
