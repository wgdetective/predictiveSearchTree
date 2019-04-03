package com.hematite.predictive.search.dataprovider.converter;

import com.hematite.predictive.search.dataprovider.entity.HotelEntity;
import com.hematite.predictive.search.tree.NodeData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class HotelConverter {

    public List<NodeData<String>> convertToDto(final List<HotelEntity> hotelEntityList) {
        final List<NodeData<String>> dataList = new ArrayList<>();

        for (HotelEntity hotelEntity : hotelEntityList) {
            dataList.add(new NodeData<>(hotelEntity.getName(), hotelEntityList.toString()));
        }
        return dataList;
    }

}
