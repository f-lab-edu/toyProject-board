package com.board.toyproject.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Member {
    @NonNull
    String memberId;
    @NonNull
    String name;

    String phoneNum;
    String regDt;

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", regDt='" + regDt + '\'' +
                '}';
    }


}
