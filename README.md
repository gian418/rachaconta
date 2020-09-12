[![Build Status](https://travis-ci.org/gian418/rachaconta.svg?branch=master)](https://travis-ci.org/gian418/rachaconta)
# RACHACONTA API 

API REST para resolver a divisão de conta de almoços/lanches entre pessoas.

#### Instruções de execução
API foi desenvolvida utilizando JAVA 8 e Maven. Para rodar a aplicação através do console basta executar o comando na raiz do projeto:
```
mvn spring-boot:run
```

#### Documentação da API
Está sendo utilizado o Swagger para documentar esta API. Para acessar, basta abrir o seguinte link com o projeto rodando:
```
http://localhost:8080/swagger-ui.html
```

#### Integração Continua [![Build Status](https://travis-ci.org/gian418/rachaconta.svg?branch=master)](https://travis-ci.org/gian418/rachaconta)
Está sendo utilizado o Travis CI para realizar o deploy no Heroku.
Travis CI da API:
```
https://travis-ci.org/github/gian418/rachaconta
```
Aplicação rodando no Heroku:
```
https://rachaconta-se.herokuapp.com/swagger-ui.html
```
Obs: é possivel que o primeiro acesso demore um pouco, pois a aplicação "adormece" quando fica um tempo inativa.

