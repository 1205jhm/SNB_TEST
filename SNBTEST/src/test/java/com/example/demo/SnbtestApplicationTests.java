package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.Controller.BoardController;
import com.example.demo.Controller.CommentController;
import com.example.demo.Controller.UserController;
import com.example.demo.entity.SnbBoard;
import com.example.demo.entity.SnbComment;
import com.example.demo.entity.SnbUser;

@SpringBootTest
class SnbtestApplicationTests {

	@Autowired
	BoardController boardContoller;
	@Autowired
	CommentController commentContoller;
	@Autowired
	UserController userContoller;

	@Test
	void contextLoads() {
	}

	@Test
	void findAllBoard() {
		SnbBoard snbBoard = new SnbBoard();
		snbBoard.setPageNum(1);
		snbBoard.setLimit(10);
		List<SnbBoard> resultList = boardContoller.findAllBoard(snbBoard);
		for (SnbBoard result : resultList) {
			System.out.println(result.toString());
		}
	}

//	@Test
//	void saveBoard()
//	{
//		SnbBoard snbBoard = new SnbBoard();
//		snbBoard.setContent("test");
//		snbBoard.setTitle("test");
//		SnbBoard result = boardContoller.saveBoard(snbBoard);
//		System.out.println(result.toString());
//	}

	@Test
	void deleteBoard() {
		SnbBoard snbBoard = new SnbBoard();
		snbBoard.setBoardSeq(1);
		snbBoard.setContent("test");
		snbBoard.setTitle("test");
		SnbBoard result = boardContoller.deleteBoard(snbBoard);
		System.out.println(result.toString());
	}

	@Test
	void findAllComment() {
		SnbComment snbComment = new SnbComment();
		snbComment.setBoardSeq(1);
		List<SnbComment> resultList = commentContoller.findAllComment(snbComment);
		for (SnbComment result : resultList) {
			System.out.println(result.toString());
		}
	}

//	@Test
//	void saveComment()
//	{
//		SnbComment snbComment = new SnbComment();
//		snbComment.setComment("test");
//		snbComment result = commentContoller.saveComment(snbComment);
//		System.out.println(result.toString());
//	}

	@Test
	void deleteComment() {
		SnbComment snbComment = new SnbComment();
		snbComment.setCommentSeq(1);
		snbComment.setComment("test");
		SnbComment result = commentContoller.deleteComment(snbComment);
		System.out.println(result.toString());
	}

	@Test
	void join() {
		SnbUser snbUser = new SnbUser();
		snbUser.setUsername("username");
		snbUser.setPassword("password");
		int result = userContoller.join(snbUser);
		System.out.println(result);
	}
}
