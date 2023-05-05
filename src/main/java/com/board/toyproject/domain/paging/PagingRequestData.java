package com.board.toyproject.domain.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PagingRequestData {

    private int page;                 // 현재 페이지 번호
    private int recordSize;           // 페이지당 출력할 데이터 개수
    private String searchContent;           // 검색 키워드
    private String searchType;        // 검색 유형
    private String orderBy;
    private Pagination pagination;    // 페이지네이션 정보

    public PagingRequestData() {
        this.page = 1;
        this.recordSize = 10;
    }
}
