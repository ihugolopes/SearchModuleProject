package com.hugolopes.searchmodule.repository;

import com.hugolopes.searchmodule.model.Station;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    Optional<List<Station>> findAllByName(String name);

    @Query("FROM Station s WHERE LOWER(s.name) like %:name%")
    Page<Station> findAllByNamePageable(@Param("name") String name, Pageable pageable);

}
