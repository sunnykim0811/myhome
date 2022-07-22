package com.godcoder.myhome.controller;

import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;


@RestController
@RequestMapping("/api")
class BoardApiController {

    @Autowired
    private BoardRepository Repository;

    @GetMapping("/boards")
    public List<Board> all(@RequestParam(required = false, defaultValue = "") String title,
                           @RequestParam(required = false, defaultValue = "") String content) {
        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(content)){
            return Repository.findAll();
        }else{
            return Repository.findByTitleOrContent(title, content);
        }

    }

    @GetMapping("/boards/{id}")
    public Board findById(@PathVariable("id") Long id) {
        return Repository.findById(id).orElse(null);
    }

    @PostMapping("/boards")
    public Board newBoard(@RequestBody Board newBoard) {
        return Repository.save(newBoard);
    }

    @PutMapping("/boards/{id}")
    public Board update(@PathVariable( "id" ) Long id, @RequestBody Board newBoard) {
        return Repository.findById(id).
                map(board->{
            board.setTitle(newBoard.getTitle());
            board.setContent(newBoard.getContent());
            return Repository.save(board);
        }).orElseGet(()->{
            newBoard.setId(id);
            return Repository.save(newBoard);
        });

        //Repository.update(board);
    }

    @DeleteMapping("/boards/{id}")
    public void delete(@PathVariable("id") Long id) {
        Repository.deleteById(id);
    }

}