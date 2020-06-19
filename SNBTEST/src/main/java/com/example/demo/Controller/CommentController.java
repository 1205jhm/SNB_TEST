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
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.SnbComment;
import com.example.demo.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/findAllComment.do", method = RequestMethod.POST)
	public @ResponseBody List<SnbComment> findAllComment(@ModelAttribute SnbComment Snbcomment) {
		return commentService.findAllComment(Snbcomment);
	}

	@RequestMapping(value = "/saveComment.do", method = RequestMethod.POST)
	public @ResponseBody SnbComment saveComment(@ModelAttribute SnbComment snbcomment) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		snbcomment.setInsUser(user.getUsername());
		return commentService.saveComment(snbcomment);
	}

	@RequestMapping(value = "/deleteComment.do", method = RequestMethod.POST)
	public @ResponseBody SnbComment deleteComment(@ModelAttribute SnbComment snbcomment) {
		return commentService.deleteComment(snbcomment);
	}
}
