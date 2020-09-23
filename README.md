# POC - Sistema Completo

## OBS: Realizei o Deploy dos fontes desatualizados no Heroku e Firebase.
# Fontes Atualizados com sucesso no Heroku e no Firebase.

## Credênciais de Acesso
 - Usuário: Pablo  
 - Senha: Mudar@123

### Link direto para a Aplicação: http://www.ondeployment.com.br/softplan/

# BACK-END
Implementado Spring Data, Mockito, JUnit, AssertJ, Swagger, Tratamento de Exceções etc.

## DOCKER
Caso Queira Executar tal aplicação em um Container Docker, ambiente Ubuntu ou Windows, favor executar os passos abaixo.
- Partindo do pressuposto que já tenha instalado o Docker e o Docker-Compose em seu host S.O Linx como : Ubuntu, CentoOS e derivados ou Windows.
 - Somente  à partir do Arquivo `Docker-Compose.Yml` que se encontra na pasta `Docker-Compose - Imagens oriundas do DOCKER HUB`
 que está na raiz do projeto, é possível gerar uma imagem Docker e por fim criar o Container. Basta simplesmente executar com o comando --> "docker-compose up".

## docker-compose up
* Tudo Será feito de forma automatizada, pacotes seram instalados e ambientado. Lembrando que tal comando deve ser executado pelo terminal na pasta informada acima, exatamente onde se encontra o arquivo Docker-Compose.yml. Com o comando acima realizado, a aplicação inteira já está configurada e se tudo deu certo você deverá conseguir acessar a mesma pelo navegador, através do endereço de IP da qual a máquina host que foi instalado o Docker, na porta 4200, melhores informações para execução do projeto logo abaixo.
* Nota: Lembre-se que quando for executar em ambientes como CentOS tais S.O possuem firewall, logo será necessário realizar a liberação das portas no mesmo para poder acessar a aplicação. 

# Heroku(Somente Back-End)
* Fiz o Deploy desta aplicação no Heroku, abaixo segue o Link de acesso a mesma, tal lhe redirecionará para a página de documentação da API Back-End, feita utilizando o Framework Swagger, que através dela, poderá verificar todos os EndPoints disponíveis, bem como a utilização dos mesmos, assim caso queria testar o funcionamento da mesma sem precisar ambientar nada basta clicar no link abaixo, porém lembrando é somente o Back-End.

    * https://back-and-softplan-poc.herokuapp.com/swagger-ui.html  
    
   OBS: Esta é uma conta gratuita do Heroku, então ao tentar acessar a API pela primeira vez, bem provavelmente que irá demorar responder um pouco, pois por se tratar de conta gratuita o ambiente não fica disponível para acesso imediato, existirá um Delay, como uma inicialização do ambiente de onde a mesma está alocada, após aguardar uns instantes, a aplicação está disponível e com funcionamente similar a uma em produção.
   
OBS: Foi configurado para a aplicação executar um arquivo .sql que se encontra na pasta src/main/resources/db/postgres/ para inserção do usuário no banco de dados.

# Firebase Hosting(Somente Front-End)
* Realizei o Deploy do Front-End no Firebase Hosting.

## Executando e testando o projeto (Back-End)

### Pré-requisitos
* PostgresSQL (O banco que foi testado).
* Java 8
* Sistema Operacional Windows Pois este tutorial não irá abordar instalação em outros SO.

#### 1 - Instalar o PostgresSQL:

A página oficial contém [links para download](https://www.postgresql.org/download/) do SGBD para diversas plataformas.


1.1 Instalar o PostgreSQL:

1.2 É bastante simples, basta dar um duplo clique no executável que foi baixado no passo a cima e seguir os passos do instalador.

#### 2 - Configurar as propriedades da aplicação 

Caminho até o arquivo de propriedades da aplicação:
`src/main/resources/application.properties`

O usuário e a senha cadastrados durante a instalação do BD deveram ser informados neste arquivo.  

Por exemplo, a string de conexão com o banco de dados com nome  `agenda`
seria `jdbc:postgresql://localhost:5432/agenda`. Para configurar usuário `postgres` e senha `postgres`
ou caso a sua própria, utilizar a configuração abaixo: 

`spring.datasource.username=postgres`
`spring.datasource.password=postgres`

#### 3 - Buildar e executar a aplicação

Dentro do diretório root da aplicação executar os passos abaixo.

3.1 Para rodar os testes:

##Obs para rodar os testes lembre-se que o maven precisa estar instalado. 

`$ mvn test #Para rodar os testes`

3.2 Para buildar a aplicação:
`$ mvn clean install #buildando o pacote da aplicação`

3.3 Para executar a aplicação:

O Maven através do `spring-boot-maven-plugin` gera o jar executável.
Então basta entrar no diretório `target/` e executar:

`$ java -jar "softplan-0.0.1-SNAPSHOT.jar"`

OBS: Caso apresente errro no comando acima tente informar todo o Diretório, então supondo que a pasta do projeto esteja em C:\
Logo o Comando Será : 
`$ java -jar "C:\pastadoprojeto\target\softplan-0.0.1-SNAPSHOT.jar"`

A partir deste ponto se tudo ocorrer corretamente a aplicação estará executando normalmente. 

É possível utilizar o client do swagger através da url [http://localhost:8080/swagger-ui.html]

## Principais tecnologias e frameworks utilizados (Back-End)

- Spring Boot
- Spring Data 
- Spring Security
- JUnit 5
- Mockito
- Hibernate
- Swagger
- Lombook
- Bean Validation
- WebSocket

## Design Patterns Envolvidos

### Factory
Foi Utilizado o Pattern Factory, afim de otimizar algumas funcionalidades, o conceito que este Pattern traz consigo em síntese é lhe servir de forma automatizada uma
instância de um Objeto, simples porém muito útil, foi utilizado afim de isolar alguns recursos e auxiliar nas estratégias de regra de negócio.

## Observer
Para não me estender muito na explicação deste Pattern recomendo caso tenha curiosidade ler este artigo da DevMedia https://www.devmedia.com.br/design-patterns-observer/16875.

### WebSocket(Criado Chat em Grupo)
Utilizado o SocketJS afim de implementar o Padrão WebSocket para criação de um Chat na Aplicação, padrão utilizado em grandes projetos como Twitter, Whatsapp e vários outros.
O conceito que o WebSocket traz é oriundo de trocas de mensagens em dupla direção, Cliente e Server o Front-End e Back-End, como queira adotar,
diferentemente dos verbos HTTP, como: GET onde se é necessário realizar tal requisição ao Server afim de obter alguma mensagem e após a obtenção
da mesma a conexão é encerrada, no WebSocket o próprio Server já lhe envia tal através do mesmo que utiliza o protocolo TCP para se comunicar
logo existe uma comunicação bidirecional e o mais importante a conexão uma vez feita não é encerrada, é criado um "tunel" onde as informações são 
trafegadas, desta forma o fluxo de informações fica o mais próximo possível do tempo real.  A capacidade bidirecional é tão boa que se pode tunelar
um protocolo TCP de tempo real, como Remote Desktop ou VNC, sobre um WebSocket.

## Spring Security
Para Segurança da API foi implementado o Spring Security como método de Autenticação `STATELESS`, utilizado o JWT.
O Tempo de Expiração do Token configurei para 30 Minutos para que seja possível o teste mais facilmente por se tratar de uma POC.
A senha ao Efetuar login, no Banco de Dados foi cadastrada utilizando o BCrypt (é um método de criptografia do tipo hash para senhas baseado no Blowfish).
Logo no DB ficaria a Senha Criptografada e ao usuário fazer login no Recurso disponibilizado pela API, será digitado a senha sem Criptografia.

## Bean Validation
Utilizado o mesmo para validações na Entidade People.

## Lombok
Utilizado as anotações do Lombok para geração dos Getters and Setters e Construtores.

# Front-End - Angular
Foi utilizado o Framework Angular no Front-End.

## Implementações no Front-End

- Vários Componentes do Angular Material como: Modal,Table, Paginator, DataPicket, Card dentre vários outros.
- Guarda de Rotas
- Interceptors, pois no Back-End a autenticação é via JWT.
- WebSocket SockJS - Client.
- Página Padrão em Caso de Rotas Nâo encontradas (Page Not Found).

