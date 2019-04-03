package com.hematite.predictive.search.dataprovider.service;

import com.hematite.predictive.search.dataprovider.DataProvider;
import com.hematite.predictive.search.dataprovider.converter.HotelConverter;
import com.hematite.predictive.search.dataprovider.repository.HotelRepository;
import com.hematite.predictive.search.tree.NodeData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SqlDataProvider implements DataProvider {

    private final HotelRepository hotelRepository;

    private HotelConverter hotelConverter;

    @Override
    public List<NodeData<String>> getAllData() {
        return hotelConverter.convertToDto(hotelRepository.findAll());
    }
}
