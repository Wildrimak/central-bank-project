# Cash Machine Project

Conforme mostrado no TESTE BANCO-DEV_BACK.pdf os requisitos para o projeto dessa api foram retirados daí.

O cenário que imaginei foi, num sistema de caixa eletronico de um cliente qualquer, haveria a possibilidade de se simular a criação de um banco, agencia, cliente e conta, além disso o extrato seria visto através das movimentações na conta. Logo criei esses endpoints para representar cada uma dessa ações e para preencher os requisitos solicitados:

Method  | Endpoint                                  | Description
--------|-------------------------------------------|--------------------------------------------------------------------------
POST | /banks | Cria um banco
POST | /banks/{id}/agencies | Cria uma agencia
POST | /customer/accounts | Cria uma conta
GET  | /accounts/{id}/movements | Ver as movimentações de uma conta
POST | /accounts/{id}/withdrawal | Realiza um saque
POST | /accounts/{id}/deposit | Realiza um deposito
POST | /accounts/{id}/transfer | Realiza uma transferencia
**POST**  | localhost:8080/oauth/token | Obtem o token de acesso necessário para poder chamar qualquer endpoint
GET  | /customer/accounts/{id} | Ver a conta de um usuário especifico, independente de quem veja

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


A aplicação foi testada utilizando-se o postman, onde numa aba authorization, deve-se selecionar uma aba type e escolher a opção basic auth, ao lado irá aparecer um formulario onde se deve preencher com username e password, onde deve-se colocar neles os valores de wildrimak e kamirdliw, por fim clicando em preview request, ele irar adicionar automaticamente nos headers uma key do tipo authorization com valor Basic [token-gerado-ao-fazer-login-com-username-e-password], assim será possivel solicitar o token. A request deve ser feita ao endpoint com um POST em localhost:8080/oauth/token

Após o passo anterior tiver sido concluido deve-se ir na aba body, selecionar a opção form-data, e preencher com os seguintes valores:

Key        | Value
-----------|---------------
grant_type | password
username   | admin
password   | nimda

Após essas configurações feitas, quando se pedir o token, ele irá mandar uma resposta em json com diversos campos, um deles é o access_token, onde nele irá vim, o token que deve ser passado em todas as requests para ter acesso a elas.

Ao se abrir o projeto, no eclipse, deve-se selecionar a opção import maven project, selecionar o projeto e após carregar as  dependencias, adicionar ao build path, o junit 4.

Após isso, abra o aplication.properties localizado em src/main/java, lá mostra as propriedas de conexão com o banco de dados, ou seja, espera-se para executar a aplicação um banco do tipo postgres, com username postgres, senha postgres e nome do banco cash-machine. Logo se não há um banco do configurado, crie um banco no postgree com o nome de cash-machine, ou mude para um banco já existente vazio, mudando as propriedades de conexão.