package com.board.toyproject.domain.paging;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Pagination<T> {

    private int totalRecordCount;     // 전체 데이터 수
    private int totalPageCount;       // 전체 페이지 수
    private int limitStart;           // LIMIT 시작 위치

    private List<T> list = new ArrayList<>();

    public void setList(List<T> list) {
        this.list = list;
    }

    public Pagination(int totalRecordCount, PagingRequestData params) {
        if (totalRecordCount > 0) {
            this.totalRecordCount = totalRecordCount;
            calculation(params);
        }
    }

    private void calculation(PagingRequestData params) {

        // 전체 페이지 수 계산
        totalPageCount = ((totalRecordCount - 1) / params.getRecordSize()) + 1;

        // 현재 페이지 번호가 전체 페이지 수보다 큰 경우, 현재 페이지 번호에 전체 페이지 수 저장
        if (params.getPage() > totalPageCount) {
            params.setPage(totalPageCount);
        }

        // LIMIT 시작 위치 계산
        limitStart = (params.getPage() - 1) * params.getRecordSize();
    }

}
