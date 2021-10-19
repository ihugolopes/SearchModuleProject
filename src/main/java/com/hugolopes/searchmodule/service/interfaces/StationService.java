package com.hugolopes.searchmodule.service.interfaces;

import com.hugolopes.searchmodule.exception.StationException;
import com.hugolopes.searchmodule.model.Station;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StationService {

    List<Station> findStationsByNameSearch(String name, int size) throws StationException;

    Page<Station> findStationsByNamePageable(String searchTerm, int page, int size);
}
