package com.board.toyproject.domain.paging;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Pagination<T> {

    private int totalRecordCount;     // 전체 데이터 수
    private int limitStart;           // LIMIT 시작 위치

    private List<T> list = new ArrayList<>();

    public void setList(List<T> list) {
        this.list = list;
    }

    public Pagination(int totalRecordCount) {
        if (totalRecordCount > 0) {
            this.totalRecordCount = totalRecordCount;
        }
    }

    /*private void calculate(PagingRequestData params) {
        // LIMIT 시작 위치 계산
        limitStart = (params.getPage() - 1) * params.getRecordSize();
    }*/

}
