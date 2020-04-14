package com.tuanzhang.ad.search;

import com.tuanzhang.ad.search.vo.SearchRequest;
import com.tuanzhang.ad.search.vo.SearchResponse;
import io.micrometer.core.instrument.search.Search;

public interface ISearch {

    SearchResponse fetch(SearchRequest request);
}
