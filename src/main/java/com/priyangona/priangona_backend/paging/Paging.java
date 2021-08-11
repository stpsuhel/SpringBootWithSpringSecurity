package com.priyangona.priangona_backend.paging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paging<T> {
    private int draw;
    private int recordsFiltered;
    private int recordsTotal;
    private List<T> data;

    private String message;
    private boolean error = false;

    public Paging(Page<T> page, int draw, long recordsTotal){
        this.draw = (Integer) draw;
        this.recordsFiltered = (int) page.getTotalElements();
        this.recordsTotal = (int) recordsTotal;
        this.data = page.getContent();
    }

    public  Paging(int draw){
        if (draw > 0){
            this.draw = (Integer) draw;
        }else {
            this.draw = 1;
        }
        this.message = "Length Should be greater then Zero for paginate";
        this.error = true;
    }
}
