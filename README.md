
# Sistema de gestão bancária

Esse projeto foi desenvolvido para um desafio técnico visando apresentar habilidades de desenvolvedor java backend e tem como escopo criar uma conta e realizar operações financeiras.


## Como executar

Clonar repositório git:

```bash
  git clone https://github.com/brunoleitearaujo/objective.git
```

Executar o banco de dados mysql:

```bash
  docker-compose up -d
```

Executar a aplicação Spring Boot.

Acessar api em `http://localhost:8080`.

Acessar a documentação da api via Swagger em `http://localhost:8080/swagger-ui.html`.


## Documentação da API

#### Cadastra nova conta

```http
  POST /conta
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `numeroConta` | `long` | **Obrigatório**. Número da conta |
| `saldo` | `float` | **Obrigatório**. Saldo da conta |

#### Retorna uma conta

```http
  GET /conta/${numeroConta}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `numeroConta`      | `long` | **Obrigatório**. O ID do item que você quer |

#### Cadastra nova transação

```http
  POST /transacao
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `numeroConta` | `long` | **Obrigatório**. Número da conta |
| `formaPagamento` | `string` | **Obrigatório**. Forma de pagamento da transação |
| `valor` | `float` | **Obrigatório**. Valor da transação |

