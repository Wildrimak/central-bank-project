# Central Bank Project

Desafio – Projeto banco central

O desafio envolve a implementação de uma API Restful genérica para múltiplos serviços bancários.
Dentre os serviços, a API terá:

    • Cadastro de uma instituição financeira - Banco
    • Cadastro de uma agência bancária pertencente a uma instituição
    • Cadastro de Clientes juntamente com uma conta bancária
    • Extrato bancário demonstrando a movimentação financeira de uma conta
    • Saques
    • Depósitos
    • Transferências

Observações:

    • Todas as requisições à API precisam de uma autorização seguindo o padrão OAuth 2.0.
      
    • As operações de cada conta devem ser isoladas e não influenciar as informações da outra conta logada.
      
    • As transferências podem ocorrer entre contas de mesmo banco ou de bancos diferentes.
      
    • Os erros devem ser tratados e enviados como retorno da requisição. 
      
    • A API deve ser desenvolvida no framework Spring Boot
      
    • Deve haver testes unitários, suíte de testes bem organizados
      
    • Deve haver recursos do Java 8.
      
    • Deve seguir as boas práticas de programação.
      
    • Deve utilizar o git com commits pequenos e bem descritos. 

Method  | Endpoint                                  | Description
--------|-------------------------------------------|--------------------------------------------------------------------------
POST | /banks | Cria um banco
POST | /banks/{id}/agencies | Cria uma agencia
POST | /customer/accounts | Cria uma conta
GET  | /accounts/{id}/movements | Ver as movimentações de uma conta
POST | /accounts/{id}/withdrawal | Realiza um saque
POST | /accounts/{id}/deposit | Realiza um deposito
POST | /accounts/{id}/transfer | Realiza uma transferencia
POST  | localhost:8080/oauth/token | Obtem o token de acesso necessário para poder chamar qualquer endpoint
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