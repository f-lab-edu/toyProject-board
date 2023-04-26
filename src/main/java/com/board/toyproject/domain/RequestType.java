package com.board.toyproject.domain;

public enum RequestType {
    TITLE, CONTENT;

    /**
     * 검색 조건으로 넣어도 되는 타입인지 검증
     * @param requestType
     * @return
     */
    public static boolean isRequestType(String requestType){
        if (!requestType.equals(RequestType.TITLE.toString()) && !requestType.equals(RequestType.CONTENT.toString())) {
            return false;
        }else {
            return true;
        }
    }
}
