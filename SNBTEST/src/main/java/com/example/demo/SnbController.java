package com.example.demo;

import java.net.http.HttpRequest;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Snbboard;
import com.example.demo.entity.Snbuser;

@Controller
public class SnbController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SnbService snbService;

	@RequestMapping(value = "/list.do")
	public ModelAndView list() {
		logger.info("야!!!!!!!!!!!!!!!!!!!!!!!!!!");
//		Board board = new Board();
//		board.setTitle("제목");
//		board.setContent("내용");
//		board.setDelyn("N");
//		board.setInsdate(new Date());
//		board.setUpddate(new Date());
//		board.setInsuser("test");
//		snbService.save(board);
		ModelAndView mv = new ModelAndView("boardList");
		mv.addObject("list", snbService.findAllBoard());
		return mv;
	}
	
	@RequestMapping(value = "/write")
	public String write() {
		logger.info("야!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return "boardWrite";
	}
	
	@RequestMapping(value = "/write.do")
	public @ResponseBody String insert(@RequestBody Snbboard snbboard, HttpServletRequest request) {
		logger.info("야!!!!!!!!!!!!!!!!!!!!!!!!!!");
		logger.info(snbboard.getContent() + snbboard.getTitle());
		return "boardWrite";
	}
}
