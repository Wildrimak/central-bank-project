# Cash Machine Project

Conforme mostrado no TESTE BANCO-DEV_BACK.pdf os requisitos para o projeto dessa api foram retirados daí.

O cenário que imaginei foi, num sistema de caixa eletronico de um cliente qualquer, haveria a possibilidade de se simular a criação de um banco, agencia, cliente e conta, além disso o extrato seria visto através das movimentações na conta. Logo criei esses endpoints para representar cada uma dessa ações e para preencher os requisitos solicitados:

Method  | Endpoint                                  | Description
--------|-------------------------------------------|--------------------------------------------------------------------------
POST | /banks ------------------------------------> | Cria um banco
POST | /banks/{id}/agencies ----------------------> | Cria uma agencia
POST | /customer/accounts ------------------------> | Cria uma conta
GET  | /accounts/{id}/movements ------------------> | Ver as movimentações de uma conta
POST | /accounts/{id}/withdrawal -----------------> | Realiza um saque
POST | /accounts/{id}/deposit --------------------> | Realiza um deposito
POST | /accounts/{id}/transfer -------------------> | Realiza uma transferencia
GET  | localhost:8080/oauth/token ----------------> | Obtem o token de acesso necessário para poder chamar qualquer endpoint
GET  | /customer/accounts/{id} -------------------> | Ver a conta de um usuário especifico, independente de quem veja

Cenários:

A aplicação que consumir a API deve ter que solicitar um token de acesso, passando as seguintes credenciais:

	* grant_type = password
	* username = admin
	* password = nimda 

Onde irá receber como resposta um token de acesso, no qual deverá passar no campo autorizathion de cada request,
da seguinte forma:

	* Bearer [token-de-acesso-gerado]

Para conseguir fazer a request do token, ele vai ter que enviar uma autorizathion na request da sequinte forma:

	* Basic [token-gerado-ao-fazer-login-com-username-e-password]  

Esse username e password se consegue no aplication.properties na configuração do spring