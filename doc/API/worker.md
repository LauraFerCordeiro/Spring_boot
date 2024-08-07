# API para Worker

## URL Base
A URL base para acessar os endpoints relacionados aos trabalhadores é `/workers`.

## Endpoints

### Registrar um Novo Trabalhador

    URL: /workers/register
    Método: POST
    Parâmetros:
        name (String): O nome do trabalhador. (Obrigatório)
        email (String): O endereço de e-mail do trabalhador. (Obrigatório)
        birthDate (LocalDate): A data de nascimento do trabalhador. (Obrigatório)
        salary (BigDecimal): O salário do trabalhador. (Obrigatório)
        role (String): O cargo do trabalhador. (Obrigatório)
        departamentId (Long): O ID do departamento associado ao trabalhador. (Obrigatório)
    Respostas:
        201 Created: O trabalhador foi registrado com sucesso. Retorna o objeto Worker registrado.
        404 Not Found: O trabalhador não pôde ser registrado.

### Listar Todos os Trabalhadores

    URL: /workers/list
    Método: GET
    Respostas:
        200 OK: Lista de trabalhadores recuperada com sucesso. Retorna uma lista de objetos Worker.

### Buscar um Trabalhador por ID

    URL: /workers/search/{id}
    Método: GET
    Variáveis de Caminho:
        id (Long): O ID do trabalhador a ser buscado. (Obrigatório)
    Respostas:
        200 OK: Trabalhador encontrado com sucesso. Retorna o objeto Worker.
        404 Not Found: Nenhum trabalhador encontrado com o ID especificado.

### Excluir um Trabalhador por ID

    URL: /workers/delete/{id}
    Método: DELETE
    Variáveis de Caminho:
        id (Long): O ID do trabalhador a ser excluído. (Obrigatório)
    Respostas:
        200 OK: Trabalhador excluído com sucesso. Retorna true.
        404 Not Found: Nenhum trabalhador encontrado com o ID especificado. Retorna false.

### Editar as Informações de um Trabalhador

    URL: /workers/edit/{id}
    Método: PUT
    Variáveis de Caminho:
        id (Long): O ID do trabalhador a ser editado. (Obrigatório)
    Parâmetros:
        name (String): O novo nome do trabalhador. (Obrigatório)
        email (String): O novo endereço de e-mail do trabalhador. (Obrigatório)
        birthDate (LocalDate): A nova data de nascimento do trabalhador. (Obrigatório)
        salary (BigDecimal): O novo salário do trabalhador. (Obrigatório)
        role (String): O novo cargo do trabalhador. (Obrigatório)
        departamentId (Long): O novo ID do departamento associado ao trabalhador. (Obrigatório)
    Respostas:
        200 OK: Informações do trabalhador editadas com sucesso. Retorna true.
        404 Not Found: Nenhum trabalhador encontrado com o ID especificado. Retorna false.

### Buscar Trabalhadores por Nome

    URL: /workers/searchName
    Método: GET
    Parâmetros:
        name (String): O nome do trabalhador a ser buscado. (Obrigatório)
    Respostas:
        200 OK: Trabalhadores encontrados com sucesso. Retorna uma lista de objetos Worker.

### Buscar Trabalhadores por Salário

    URL: /workers/searchSalary
    Método: GET
    Respostas:
        200 OK: Lista de trabalhadores recuperada com sucesso. Retorna uma lista de objetos Worker.

### Buscar Trabalhadores por Cargo

    URL: /workers/searchRole
    Método: GET
    Parâmetros:
        role (String): O cargo do trabalhador a ser buscado. (Obrigatório)
    Respostas:
        200 OK: Trabalhadores encontrados com sucesso. Retorna uma lista de objetos Worker.

## Worker

    id (Long): O identificador único do trabalhador.
    name (String): O nome do trabalhador.
    email (String): O endereço de e-mail do trabalhador.
    birthDate (LocalDate): A data de nascimento do trabalhador.
    salary (BigDecimal): O salário do trabalhador.
    role (String): O cargo do trabalhador.
    departamentId (Long): O ID do departamento associado ao trabalhador.

