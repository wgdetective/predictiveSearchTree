package com.hematite.predictive.search.dataprovider.converter;

import com.hematite.predictive.search.dataprovider.entity.HotelEntity;
import com.hematite.predictive.search.tree.NodeData;

import java.util.ArrayList;
import java.util.List;

public class HotelConverter {

    public static List<NodeData> convertToDto(final List<HotelEntity> hotelEntityList) {
        final List<NodeData> dataList = new ArrayList<>();

        for (HotelEntity hotelEntity : hotelEntityList) {
            dataList.add(new NodeData<>(hotelEntity.getName().toLowerCase(), hotelEntity.getName()));
        }
        return dataList;
    }

}
