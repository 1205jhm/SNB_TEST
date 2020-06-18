package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Snbboard;
import com.example.demo.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/list")
	public String list() {
		return "boardList";
	}

	@RequestMapping(value = "/findAllBoard.do", method = RequestMethod.POST)
	public @ResponseBody List<Snbboard> findAllBoard(@ModelAttribute Snbboard snbboard) {
		return boardService.findAllBoard(snbboard);
	}

	@RequestMapping(value = "/write")
	public String write() {
		return "boardWrite";
	}

	@RequestMapping(value = "/saveBoard.do", method = RequestMethod.POST)
	public @ResponseBody Snbboard saveBoard(@ModelAttribute Snbboard snbboard) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		snbboard.setInsuser(user.getUsername());
		return boardService.saveBoard(snbboard);
	}

	@RequestMapping(value = "/deleteBoard.do", method = RequestMethod.POST)
	public @ResponseBody Snbboard deleteBoard(@ModelAttribute Snbboard snbboard) {
		return boardService.deleteBoard(snbboard);
	}

	@RequestMapping(value = "/view")
	public ModelAndView view(@RequestParam String boardseq) {
		ModelAndView mv = new ModelAndView("boardView");
		Snbboard snbboard = boardService.findByIdBoard(Integer.parseInt(boardseq));
		mv.addObject("row", snbboard);
		return mv;
	}
}
