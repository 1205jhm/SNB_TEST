package com.example.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Snbboard;

@Controller
public class SnbController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SnbService snbService;

	@RequestMapping(value = "/list")
	public String list() {
		return "boardList";
	}
	
	@RequestMapping(value = "/findAllBoard.do", method=RequestMethod.POST)
	public @ResponseBody List<Snbboard> findAllBoard(@ModelAttribute Snbboard snbboard) {
		return snbService.findAllBoard(snbboard);
	}
	
	@RequestMapping(value = "/write")
	public String write() {
		return "boardWrite";
	}
	
	@RequestMapping(value = "/saveBoard.do", method=RequestMethod.POST)
	public @ResponseBody Snbboard saveBoard(@ModelAttribute Snbboard snbboard) {
		snbboard.setInsuser("하이");
		return snbService.saveBoard(snbboard);
	}
	
	@RequestMapping(value = "/deleteBoard.do", method=RequestMethod.POST)
	public @ResponseBody Snbboard deleteBoard(@ModelAttribute Snbboard snbboard) {
		return snbService.deleteBoard(snbboard);
	}
	
	@RequestMapping(value = "/view")
	public ModelAndView view(@RequestParam String boardseq) {
		ModelAndView mv = new ModelAndView("boardView");
		Snbboard snbboard = snbService.findByIdBoard(Integer.parseInt(boardseq));
		mv.addObject("row", snbboard);
		return mv;
	}
}
