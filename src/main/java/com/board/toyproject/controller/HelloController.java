package com.board.toyproject.controller;

import com.board.toyproject.repository.MybatisBoardRepository;
import com.board.toyproject.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    private MybatisBoardRepository mybatisBoardRepository;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/test")
    @ResponseBody
    public Map<String, Object> boardApi(){
        Map<String, Object> map = new HashMap<>();
        List<Board> board = mybatisBoardRepository.getListBoard();
        map.put("board_list", board);
        return map;
    }
}
