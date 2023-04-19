package com.board.toyproject.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Board {

    @NonNull
    String boardId;
    @NonNull
    String memberId;
    @NonNull
    String title;
    String regDt;
    String modDt;
    String content;


}
