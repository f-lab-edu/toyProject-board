create table reply(
                      reply_id VARCHAR(20) PRIMARY KEY,
                      member_id VARCHAR(20) not null,
                      board_id VARCHAR(20) not null,
                      reg_dt datetime,
                      re_content text not null,
                      foreign key(member_id)
                          references member(member_id) on update cascade,
                      foreign key(board_id)
                          references board(board_id) on update cascade
);
CREATE TABLE member (
                        member_id VARCHAR(20) PRIMARY KEY, -- 사용자 ID
                        name VARCHAR(20),
                        phone_num varchar(30)
);

CREATE TABLE board (
                       board_id VARCHAR(20) PRIMARY KEY,
                       member_id VARCHAR(20) not null,
                       title VARCHAR(100) not null,
                       reg_dt datetime,
                       content text not null,
                       foreign key(member_id)
                           references member(member_id) on update cascade
);