package com.board.toyproject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestDTO {

    private int pageNum;
    private int pageSize;
    private String searchType; // 검색 시 입력 될 type
    private String searchContent; // 검색 시 입력 될 검색어
}
