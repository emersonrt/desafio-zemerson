# Desafio Zé - Emerson Teixeira 

## Sobre o projeto

Este projeto foi desenvolvido como parte do processo seletivo do Zé Delivery, contemplando uma API REST. Segue abaixo os endpoints.

**GET**
- Buscar parceiro por id - [domínio]/desafio-zemerson/br/partner/{idParceiro}
- Buscar parceiro por coordenadas - [domínio]/desafio-zemerson/br/partner/long={longitude}/lat={latitude}

**POST**
- Criar um Parceiro - [domínio]/desafio-zemerson/br/partner


# Tecnologias utilizadas
- Java 11
- JPA / Hibernate
- Maven
- Wildfly 21
- Banco de dados: PostgreSQL 12.x (com extensão do PostGIS)


# Como executar o projeto
- Pré-requisitos: Java 11, Wildfly 21 e PostgreSQL 12

```bash
# clonar repositório
git clone https://github.com/emersonrt/desafio-zemerson.git

# entrar no diretório do projeto
cd desafio-zemerson

# criar arquivo war
./mvn clean package
```

 - Após configurar o PostgreSQL. Rodar os comandos SQL abaixo:
``` 
CREATE DATABASE desafio_zemerson;

CREATE EXTENSION postgis;

CREATE TABLE partner
(
	id_partner BIGSERIAL PRIMARY KEY,
 	trading_name TEXT NOT NULL,
 	owner_name TEXT NOT NULL,
 	partner_document TEXT NOT NULL,
 	coverage_area geography(MULTIPOLYGON),
 	address geography(POINT),
 	CONSTRAINT unique_id_partner UNIQUE (id_partner),
 	CONSTRAINT unique_partner_document UNIQUE (partner_document)
);
```

- Após configurar o Wildfly, fazer no mesmo:
1. Deploy do driver JDBC do PostgreSQL
2. Criar um data source com o seguinte nome JNDI: *java:/DesafioZemersonDS*, Apontando para o banco de dados configurado e utilizando o driver do passo anterior
3. Deploy do arquivo *desafio-zemerson.war*, localizado no diretório target do projeto (criado pelo comando ```mvn clean package```, anteriormente)


# Autor

Emerson Rosa Teixeira

https://www.linkedin.com/in/emersonrt/
