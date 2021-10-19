package com.hugolopes.searchmodule.repository;

import com.hugolopes.searchmodule.model.Station;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class StationSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Station> search(String text, int size) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder =  fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Station.class).get();

        FullTextQuery jpaQuery = this.fullTextWithLike(text, queryBuilder, fullTextEntityManager);
        jpaQuery.setMaxResults(size);

        List<Station> results = jpaQuery.getResultList();

        if (results.size() == 0) {
            FullTextQuery jpaQueryFullText = this.fullText(text, queryBuilder, fullTextEntityManager);
            return jpaQueryFullText.setMaxResults(size).getResultList();
        }

        return results;
    }

    private FullTextQuery fullTextWithLike(String text, QueryBuilder queryBuilder, FullTextEntityManager fullTextEntityManager) {

        Query query = queryBuilder
                        .keyword()
                        .wildcard()
                        .onField("name")
                        .matching("*" + text + "*")
                        .createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Station.class);
        return jpaQuery;
    }

    private FullTextQuery fullText(String text, QueryBuilder queryBuilder, FullTextEntityManager fullTextEntityManager) {

        Query query = queryBuilder
                        .keyword()
                        .onFields("name")
                        .matching(text)
                        .createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Station.class);

        return jpaQuery;
    }


}
