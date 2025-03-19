# Microsserviço | Vendas Online

## Descrição
Microsserviço de vendas online que simula o funcionamento de um carrinho de compras. O sistema é composto por três serviços independentes, mas interconectados:

- **Produto Service**: Gerencia o catálogo de produtos disponíveis para compra.
- **Cliente Service**: Gerencia os dados dos clientes, como cadastro, consulta, atualização e exclusão.
- **Vendas Service**: Gerencia o carrinho de compras, realizando a adição de produtos, a finalização da compra e o cálculo do total.

<p align="center" >
     <img width="600" heigth="600" src="https://github.com/user-attachments/assets/bf1d567a-2d6f-4059-b364-49bb5182a0f9">
</p>

## Tecnologias
- **Java**: JDK 17.
- **OpenAPI Swagger**: API para ajudar no desing, construção, documentação e consumo de APIs REST.
- **MongoDB**: Banco de dados NoSQL.
- **Config Server**: Oferece suporte no lado do cliente para configuração externalizada no sistema distribuído.

## Funcionalidades
- **Produto Service**: Cadastro, consulta e atualização de produtos.
- **Cliente Service**: Cadastro, consulta, atualização e exclusão de clientes.
- **Vendas Service**: Criação e gerenciamento de carrinhos de compras, cálculo do total e finalização da compra.

## Estrutura do Projeto
- `produto-service/`: Serviço responsável pela gestão de produtos.
- `cliente-service/`: Serviço responsável pela gestão de clientes.
- `vendas-service/`: Serviço responsável pela gestão de carrinhos de compras e finalização de vendas.
<p align="left" >
     <img width="800" heigth="600" src="https://github.com/user-attachments/assets/7bdf42cc-2226-4a2d-ae6d-1074e5133791">
</p>

### Pré-requisitos para rodar os projetos
- [Java] (versão 17 ou superior)
- [MongoDB] (para rodar localmente)

## Contribuindo

Contribuições são bem-vindas! Para contribuir com o projeto, por favor siga estes passos:

1. Faça um fork do repositório.
2. Crie uma branch para sua feature ou correção (`git checkout -b feature/nova-feature`).
3. Faça commit das suas mudanças (`git commit -am 'Adiciona nova feature'`).
4. Envie suas alterações para o repositório (`git push origin feature/nova-feature`).
5. Abra um pull request.

## Contato
E-mail: wellscostaofc@gmail.com.

LinkedIn: https://www.linkedin.com/in/wellington-santos-backend-java/

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
