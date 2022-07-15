package com.godcoder.myhome.controller;

import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.repository.BoardRepository;
import com.godcoder.myhome.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

//    @Autowired
//    private Board board;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model){
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping ("/form")
    public String formGet(Model model, @RequestParam(required = false) Long id){
        if(id == null){
            model.addAttribute("board", new Board());

        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        //model.addAttribute("board", new Board()); //여기서 Model model 매개변수 주고 model.addAttribute 해줘도 되고
        return "board/form";
    }
    @PostMapping("/form")
    public String formPost(@Valid @ModelAttribute Board board, BindingResult bindingResult){   // 여기서 Model model 매개변수 함께 주고 model.addAttribute 해줘도 됨
        //model.addAttribute("board", board);

        boardValidator.validate(board, bindingResult);

        if (bindingResult.hasErrors()) {
            return "board/form";
        }


        boardRepository.save(board);
        return "redirect:/board/list";
    }

}
