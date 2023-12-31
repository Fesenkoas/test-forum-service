package telran.java47.post.service;

import java.util.List;

import telran.java47.post.dto.DatePeriudDto;
import telran.java47.post.dto.MessageDto;
import telran.java47.post.dto.NewPostDto;
import telran.java47.post.dto.PostDto;

public interface PostServise {
	PostDto addNewPost(String author, NewPostDto newPostDto);

	PostDto findPostById(String id);

	PostDto addComment(String id, String author, MessageDto messageDto);

	PostDto deletePost(String id);

	PostDto updatePost(String id, NewPostDto newPostDto);

	void addLike(String id);

	Iterable<PostDto> findPostByAuthor(String author);

	Iterable<PostDto> findPostByTags(List<String> tags);

	Iterable<PostDto> findPostByPeriud(DatePeriudDto datePeriudDto);

}
