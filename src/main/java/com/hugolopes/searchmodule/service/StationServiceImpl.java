package com.hugolopes.searchmodule.service;

import com.hugolopes.searchmodule.exception.StationException;
import com.hugolopes.searchmodule.model.Station;
import com.hugolopes.searchmodule.repository.StationRepository;
import com.hugolopes.searchmodule.repository.StationSearchRepository;
import com.hugolopes.searchmodule.service.interfaces.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepository repository;

    @Autowired
    private StationSearchRepository search;

    public Page<Station> findStationsByNamePageable(String name, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC,"name");
        return repository.findAllByNamePageable(name.toLowerCase(), pageRequest);
    }

    @Override
    public List<Station> findStationsByNameSearch(String name, int size) throws StationException {

        try {
            List<Station> results = search.search(name.toLowerCase(), size);
            return results.stream().sorted((s1, s2) -> s1.getName().compareTo(s2.getName())).collect(Collectors.toList());

        } catch (Exception ex) {
            throw new StationException(ex.getMessage());
        }
    }

}
