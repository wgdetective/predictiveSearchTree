package com.hematite.predictive.search.rest;

import com.hematite.predictive.search.service.PredictiveSearchService;
import com.hematite.predictive.search.tree.NodeData;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Wladimir Litvinov
 */
@RestController
@AllArgsConstructor
@RequestMapping("/predictive-search-tree")
public class SearchController {

    private final PredictiveSearchService searchService;

    @GetMapping("/search")
    public List<NodeData> search(final String text, final Long prevSearchNodeId) {
        return searchService.search(text);
    }

}
