# API para Consultancy

## URL Base

A URL base para acessar os endpoints relacionados às consultorias é `/consultancy`.

## Endpoints

### Registrar uma Nova Consultoria

    URL: /consultancy/register
    Método: POST
    Parâmetros:
        cost (BigDecimal): O custo da consultoria. (Obrigatório)
        endDate (LocalDate): A data de término da consultoria. (Obrigatório)
        description (String): A descrição da consultoria. (Obrigatório)
        clientId (Long): O ID do cliente associado à consultoria. (Obrigatório)
        workerId (Long): O ID do trabalhador associado à consultoria. (Obrigatório)
    Respostas:
        201 Created: A consultoria foi registrada com sucesso. Retorna o objeto Consultancy registrado.
        404 Not Found: A consultoria não pôde ser registrada.

### Listar Todas as Consultorias

    URL: /consultancy/list
    Método: GET
    Respostas:
        200 OK: Lista de consultorias recuperada com sucesso. Retorna uma lista de objetos Consultancy.

### Buscar uma Consultoria por ID

    URL: /consultancy/search/{id}
    Método: GET
    Variáveis de Caminho:
        id (Long): O ID da consultoria a ser buscada. (Obrigatório)
    Respostas:
        200 OK: Consultoria encontrada com sucesso. Retorna o objeto Consultancy.
        404 Not Found: Nenhuma consultoria encontrada com o ID especificado.

### Excluir uma Consultoria por ID

    URL: /consultancy/delete/{id}
    Método: DELETE
    Variáveis de Caminho:
        id (Long): O ID da consultoria a ser excluída. (Obrigatório)
    Respostas:
        200 OK: Consultoria excluída com sucesso. Retorna true.
        404 Not Found: Nenhuma consultoria encontrada com o ID especificado. Retorna false.

### Editar as Informações de uma Consultoria

    URL: /consultancy/edit/{id}
    Método: PUT
    Variáveis de Caminho:
        id (Long): O ID da consultoria a ser editada. (Obrigatório)
    Parâmetros:
        cost (BigDecimal): O novo custo da consultoria. (Obrigatório)
        endDate (LocalDate): A nova data de término da consultoria. (Obrigatório)
        description (String): A nova descrição da consultoria. (Obrigatório)
        clientId (Long): O novo ID do cliente associado à consultoria. (Opcional)
        workerId (Long): O novo ID do trabalhador associado à consultoria. (Opcional)
    Respostas:
        200 OK: Informações da consultoria editadas com sucesso. Retorna true.
        404 Not Found: Nenhuma consultoria encontrada com o ID especificado. Retorna false.

## Consultancy

    id (Long): O identificador único da consultoria.
    cost (BigDecimal): O custo da consultoria.
    endDate (LocalDate): A data de término da consultoria.
    description (String): A descrição da consultoria.
    clientId (Long): O ID do cliente associado à consultoria.
    workerId (Long): O ID do trabalhador associado à consultoria.