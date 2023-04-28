package com.board.toyproject.domain;

public enum RequestType {
    TITLE, CONTENT,MEMBER_ID;

    /**
     * 검색 조건으로 넣어도 되는 타입인지 검증
     * @param requestType
     * @return
     */
    public static boolean isRequestType(String requestType){
        for(RequestType data : RequestType.values()){
            if(data.toString().equals(requestType)){
                return true;
            }
        }
        return false;
    }
}
