package com.priyangona.priangona_backend.common;

import com.priyangona.priangona_backend.paging.Order;
import com.priyangona.priangona_backend.paging.PagingRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;

import java.util.ArrayList;
import java.util.List;

public class PagingUtil {

    public static boolean isPagingRequestDataNotValid(PagingRequest pagingRequest){
        return pagingRequest.getLength() <= 0;
    }

    public static Pageable getPageable(PagingRequest pagingRequest){
        JpaSort orders = JpaSort.unsafe(Sort.Direction.ASC, "id");

        if (pagingRequest.getOrder() != null && !pagingRequest.getOrder().isEmpty()){
            List<String> columnName = new ArrayList<>();
            Sort.Direction direction = Sort.Direction.ASC;

            for (Order order : pagingRequest.getOrder()) {
                direction = Sort.Direction.fromString(order.getDir());
                columnName.add(pagingRequest.getColumns().get(order.getColumn()).getData());
            }
            orders = JpaSort.unsafe(direction, columnName);
        }
        return PageRequest.of(pagingRequest.getStart()/pagingRequest.getLength(), pagingRequest.getLength(), orders);
    }
    public static boolean isIdNotValid(Long id) {
        return id == null || id <= 0;
    }
}
