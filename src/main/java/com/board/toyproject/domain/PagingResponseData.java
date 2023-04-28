package com.board.toyproject.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class PagingResponseData<T> {

    private List<T> list = new ArrayList<>();
    private Pagination pagination;


    public PagingResponseData(List<T> list, Pagination pagination) {
        this.list.addAll(list);
        this.pagination = pagination;
    }
}
