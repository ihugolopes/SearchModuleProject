# SearchModule

-----------------------------------------------------------------------------------------------------
ENGLISH VERSION

Backend module made for a train ticket machine.

Technologies:

- Springboot v2.5.5.
- Java 11
- Swagger2
- Apache Lucene (For search engine)
- Hibernate/JPA
- H2 Database (In memory)


The app has two endpoints for searching.

Endpoint V1: Implements pageable for search using only JPA for search.

Endpoint V2: Implements Hibernate Search with Apache Lucene to perform search for stations.

Why use some sophisticated full-text search feature like this? Why do I need this additional thing in my application instead of using the default approach, which is the SQL LIKE syntax?

Hibernate Search integrates Apache Lucene, a high-performance, extensible full-text search engine library written in Java. This combines the power of Lucene with the simplicity of Hibernate and JPA.

Simply put, we just need to add some additional annotations to our domain classes, and the tool will take care of things like database/index synchronization.

To access Swagger, use: http://localhost:8080/swagger-ui.html




----------------------------------------------------------------------------------------------------
PORTUGUESE VERSION

Módulo back-end feito para uma máquina de bilhetes de trem.

Tecnologias:

- Springboot v2.5.5.
- Java 11
- Swagger2
- Apache Lucene (Para mecanismo de pesquisa)
- Hibernate/JPA
- Banco de Dados H2 (Em memória)


A app tem dois endpoints para pesquisa.

Endpoint V1: Implementa pageable para pesquisa utilizando apenas o JPA para pesquisa.

Endpoint V2: Implementa Hibernate Search com Apache Lucene para realizar a busca de estações.

Por que usar algum recurso sofisticado de pesquisa de texto completo como este? Por que preciso dessa coisa adicional em meu aplicativo, em vez de usar a abordagem padrão, que é a sintax SQL LIKE ?

O Hibernate Search integra o Apache Lucene, uma biblioteca de mecanismo de pesquisa de texto completo extensível e de alto desempenho escrita em Java. Isso combina o poder do Lucene com a simplicidade do Hibernate e JPA.

Simplificando, só precisamos adicionar algumas anotações adicionais às nossas classes de domínio, e a ferramenta cuidará de coisas como sincronização de banco de dados / índice.

Para acessar o Swagger, utilize: http://localhost:8080/swagger-ui.html


