package com.mirus.movieland.repository.jdbc.util;

import com.mirus.movieland.data.SortParameters;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QueryBuilder {
    public static String addSortParameters(String sql, SortParameters sortParameters) {
        String result = sql + " order by " + sortParameters.getField() + " " + sortParameters.getSortDirection();
        log.info(result);
        return result;
    }
}
