package com.hugolopes.searchmodule.config;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class InitializrIndexesJob implements ApplicationListener<ContextRefreshedEvent> {


	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		try {

			System.out.println("Starting Lucene Indexes...");

			fullTextEntityManager.createIndexer().start();

			System.out.println("Lucene Indexed with success!");


		} catch (Exception e) {
			System.out.println("Lucene Indexes error: " + e.getMessage());
			e.printStackTrace();
		}

	}



}
