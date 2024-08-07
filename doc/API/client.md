# API para Client

## URL Base

A URL base para acessar os endpoints relacionados aos clientes é /clients.

## Endpoints

### Registrar um Novo Cliente

    URL: /clients/register
    Método: POST
    Parâmetros:
        name (String): O nome do cliente. (Obrigatório)
        email (String): O endereço de e-mail do cliente. (Obrigatório)
        consultancyId (Long): O ID da consultoria associada ao cliente. (Opcional)
        courseId (Long): O ID do curso associado ao cliente. (Opcional)
    Respostas:
        201 Created: O cliente foi registrado com sucesso. Retorna o objeto Client registrado.
        404 Not Found: O cliente não pôde ser registrado.

### Listar Todos os Clientes

    URL: /clients/list
    Método: GET
    Respostas:
        200 OK: Lista de clientes recuperada com sucesso. Retorna uma lista de objetos Client.

### Buscar um Cliente por ID

    URL: /clients/search/{id}
    Método: GET
    Variáveis de Caminho:
        id (Long): O ID do cliente a ser buscado. (Obrigatório)
    Respostas:
        200 OK: Cliente encontrado com sucesso. Retorna o objeto Client.
        404 Not Found: Nenhum cliente encontrado com o ID especificado.

### Excluir um Cliente por ID

    URL: /clients/delete/{id}
    Método: DELETE
    Variáveis de Caminho:
        id (Long): O ID do cliente a ser excluído. (Obrigatório)
    Respostas:
        200 OK: Cliente excluído com sucesso. Retorna true.
        404 Not Found: Nenhum cliente encontrado com o ID especificado. Retorna false.

### Editar as Informações de um Cliente

    URL: /clients/edit/{id}
    Método: PUT
    Variáveis de Caminho:
        id (Long): O ID do cliente a ser editado. (Obrigatório)
    Parâmetros:
        name (String): O novo nome do cliente. (Obrigatório)
        email (String): O novo endereço de e-mail do cliente. (Obrigatório)
        consultancyId (Long): O novo ID da consultoria associada ao cliente. (Opcional)
        courseId (Long): O novo ID do curso associado ao cliente. (Opcional)
    Respostas:
        200 OK: Informações do cliente editadas com sucesso. Retorna true.
        404 Not Found: Nenhum cliente encontrado com o ID especificado. Retorna false.

## Client

    id (Long): O identificador único do cliente.
    name (String): O nome do cliente.
    email (String): O endereço de e-mail do cliente.
    consultancyId (Long): O ID da consultoria associada ao cliente.
    courseId (Long): O ID do curso associado ao cliente.