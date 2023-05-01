package com.board.toyproject.domain;

import javax.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Board {

    int boardId;
    @NonNull
    @NotEmpty(message = "memberId는 필수 값입니다.")
    String memberId;

    @NonNull
    @NotEmpty(message = "title은 필수 값입니다.")
    String title;
    String regDt;
    String modDt;
    String content;

}
