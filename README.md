# Pro Nutrition

Projeto *em andamento* sendo construído com base no modelo Admin-Starter(https://github.com/adminfaces/admin-starter), consiste em um sistema de nutrição que irá possibilitar o cadastro de pacientes, dietas, geração de relatórios e esquema de permissões.

## To-Do
No período de 04/03/2023 - 07/04/2023 foram realizados

- Cadastro de Pacientes
- Visualização de Pacientes
- Remoção de pacientes
- Modelagem cadastro de dietas


Para as próximas sprints, o backlog consiste em:

- Cadastro de Dietas;
- Edição de pacientes;
- Inserção de novos alimentos e informações nutricionais;
- Autenticação e Autorização para usuários;
- Criação de novos usuários;
- Geração de relatórios;
- I18n;

## Instalação do Projeto

1. **Pré-requisitos:** Certifique-se de ter o Java JDK (https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) e Maven(https://maven.apache.org/download.cgi) instalados.

Esse projeto utiliza a Wildfly (https://www.wildfly.org/downloads/) como servidor, versão 31.0.1 Final, garanta que ao instalar essa versão, possuir versão jdk +11.

2. **Clonar o repositório:** Execute o seguinte comando em seu terminal para clonar o repositório:

   git clone https://github.com/maviifreitass/pro-nutrition.git

3. **Compilar o projet:** Execute o seguinte comando na pasta do projeto para compilar:

   mvn clean install

## Inicialização do Projeto 
1. **Banco de Dados:** este projeto possui persistência com o banco de dados Postgres, para ter as mesmas configurações na sua máquina local, crie um usuário e senha de acordo com os parâmetros que estão no arquivo *persistence.xml*
2. O projeto ainda não possui criação de sqls ao realizar o deploy, dessa forma acesse o arquivo *create.sql* na pasta META-INF, copie as querys e execute na sua plataforma de banco de dados.
3. Para carregar os alimentos e suas respectivas informações nutricionais, utilizamos a API Nutrition do API-ninjas(https://api-ninjas.com/api/nutrition), acesse o site e gere uma API-Key. 
  Ao gerar a API-key, substitua ela na classe *InsertAlimentList*, localizada em com.pro.nutrition.util, e em seguida execute para ter as informações dos alimentos.
Você também pode alterar a lista de alimentos pré definida. 
4. Faça o clean-build do seu projeto e realize o deploy dele em *localhost:9990*, com seu servidor wildfly ativo. 
5. Acesse *localhost:8080/pro-nutrition* para visualizar o projeto. 



 
