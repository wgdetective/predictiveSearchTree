package com.hematite.predictive.search.rest;

import com.hematite.predictive.search.tree.NodeData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Wladimir Litvinov
 */
@RestController
@RequestMapping("/predictive-search-tree")
public class SearchController {

    @GetMapping("/search")
    public List<NodeData<String>> search(final String text, final Long prevSearchNodeId) {
        return null;
    }

}
