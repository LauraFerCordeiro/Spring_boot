# API para Departament

## URL Base
A URL base para acessar os endpoints relacionados aos departamentos é `/departament`.

## Endpoints

### Registrar um Novo Departamento

    URL: /departament/register
    Método: POST
    Parâmetros:
        address (String): O endereço do departamento. (Obrigatório)
        name (String): O nome do departamento. (Obrigatório)
        description (String): A descrição do departamento. (Obrigatório)
    Respostas:
        201 Created: O departamento foi registrado com sucesso. Retorna o objeto Departament registrado.

### Listar Todos os Departamentos

    URL: /departament/list
    Método: GET
    Respostas:
        200 OK: Lista de departamentos recuperada com sucesso. Retorna uma lista de objetos Departament.

### Buscar um Departamento por ID

    URL: /departament/search/{id}
    Método: GET
    Variáveis de Caminho:
        id (Long): O ID do departamento a ser buscado. (Obrigatório)
    Respostas:
        200 OK: Departamento encontrado com sucesso. Retorna o objeto Departament.
        404 Not Found: Nenhum departamento encontrado com o ID especificado.

### Excluir um Departamento por ID

    URL: /departament/delete/{id}
    Método: DELETE
    Variáveis de Caminho:
        id (Long): O ID do departamento a ser excluído. (Obrigatório)
    Respostas:
        200 OK: Departamento excluído com sucesso. Retorna true.
        404 Not Found: Nenhum departamento encontrado com o ID especificado. Retorna false.

### Editar as Informações de um Departamento

    URL: /departament/edit/{id}
    Método: PUT
    Variáveis de Caminho:
        id (Long): O ID do departamento a ser editado. (Obrigatório)
    Parâmetros:
        address (String): O novo endereço do departamento. (Obrigatório)
        name (String): O novo nome do departamento. (Obrigatório)
        description (String): A nova descrição do departamento. (Obrigatório)
    Respostas:
        200 OK: Informações do departamento editadas com sucesso. Retorna true.
        404 Not Found: Nenhum departamento encontrado com o ID especificado. Retorna false.

### Buscar Departamentos por Nome

    URL: /departament/searchName
    Método: GET
    Parâmetros:
        name (String): O nome do departamento a ser buscado. (Obrigatório)
    Respostas:
        200 OK: Departamentos encontrados com sucesso. Retorna uma lista de objetos Departament.

### Buscar Trabalhadores de um Departamento por ID

    URL: /departament/searchWorkers/{id}
    Método: GET
    Variáveis de Caminho:
        id (Long): O ID do departamento cujos trabalhadores serão listados. (Obrigatório)
    Respostas:
        200 OK: Lista de trabalhadores recuperada com sucesso. Retorna uma lista de arrays de objetos.

### Buscar Departamentos por Descrição

    URL: /departament/searchDescription
    Método: GET
    Parâmetros:
        description (String): A descrição do departamento a ser buscado. (Obrigatório)
    Respostas:
        200 OK: Departamentos encontrados com sucesso. Retorna uma lista de objetos Departament.

## Modelos

### Departament

    id (Long): O identificador único do departamento.
    address (String): O endereço do departamento.
    name (String): O nome do departamento.
    description (String): A descrição do departamento.