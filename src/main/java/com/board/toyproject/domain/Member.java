package com.board.toyproject.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Member {

    @NonNull @NotEmpty(message = "memberId는 필수 값입니다.")
    String memberId;
    @NonNull @NotEmpty(message = "name은 필수 값입니다.")
    String name;
    @Pattern(regexp = "^\\d+$", message = "phoneNum는 숫자만 입력가능합니다.")
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
