# API para Course

## URL Base
A URL base para acessar os endpoints relacionados aos cursos é `/course`.

## Endpoints

### Registrar um Novo Curso

    URL: /course/register
    Método: POST
    Parâmetros:
        name (String): O nome do curso. (Obrigatório)
        cost (BigDecimal): O custo do curso. (Obrigatório)
        description (String): A descrição do curso. (Obrigatório)
        endDate (LocalDate): A data de término do curso. (Obrigatório)
    Respostas:
        201 Created: O curso foi registrado com sucesso. Retorna o objeto Course registrado.

### Listar Todos os Cursos

    URL: /course/list
    Método: GET
    Respostas:
        200 OK: Lista de cursos recuperada com sucesso. Retorna uma lista de objetos Course.

### Buscar um Curso por ID

    URL: /course/search/{id}
    Método: GET
    Variáveis de Caminho:
        id (Long): O ID do curso a ser buscado. (Obrigatório)
    Respostas:
        200 OK: Curso encontrado com sucesso. Retorna o objeto Course.
        404 Not Found: Nenhum curso encontrado com o ID especificado.

### Excluir um Curso por ID

    URL: /course/delete/{id}
    Método: DELETE
    Variáveis de Caminho:
        id (Long): O ID do curso a ser excluído. (Obrigatório)
    Respostas:
        200 OK: Curso excluído com sucesso. Retorna true.
        404 Not Found: Nenhum curso encontrado com o ID especificado. Retorna false.

### Editar as Informações de um Curso

    URL: /course/edit/{id}
    Método: PUT
    Variáveis de Caminho:
        id (Long): O ID do curso a ser editado. (Obrigatório)
    Parâmetros:
        name (String): O novo nome do curso. (Obrigatório)
        cost (BigDecimal): O novo custo do curso. (Obrigatório)
        description (String): A nova descrição do curso. (Obrigatório)
        endDate (LocalDate): A nova data de término do curso. (Obrigatório)
    Respostas:
        200 OK: Informações do curso editadas com sucesso. Retorna true.
        404 Not Found: Nenhum curso encontrado com o ID especificado. Retorna false.

### Listar Clientes de um Curso por ID

    URL: /course/listClients/{id}
    Método: GET
    Variáveis de Caminho:
        id (Long): O ID do curso cujos clientes serão listados. (Obrigatório)
    Respostas:
        200 OK: Lista de clientes recuperada com sucesso. Retorna uma lista de arrays de objetos.

### Course

    id (Long): O identificador único do curso.
    name (String): O nome do curso.
    cost (BigDecimal): O custo do curso.
    description (String): A descrição do curso.
    endDate (LocalDate): A data de término do curso.