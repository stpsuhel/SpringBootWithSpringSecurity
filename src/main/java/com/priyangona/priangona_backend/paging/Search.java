package com.priyangona.priangona_backend.paging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Search {
    private String value;
    private boolean regex;
    private String regexp;
}
