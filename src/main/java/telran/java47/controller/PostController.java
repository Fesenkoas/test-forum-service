package telran.java47.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java47.post.dto.DatePeriudDto;
import telran.java47.post.dto.MessageDto;
import telran.java47.post.dto.NewPostDto;
import telran.java47.post.dto.PostDto;
import telran.java47.post.service.PostServise;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class PostController {
	final PostServise postServise;

	@PostMapping("/post/{author}")
	public PostDto addNewPost(@PathVariable String author, @RequestBody NewPostDto newPostDto) {
		return postServise.addNewPost(author, newPostDto);
	}

	@GetMapping("/post/{id}")
	public PostDto findPostById(@PathVariable String id) {
		return postServise.findPostById(id);
	}

	@PutMapping("/post/{id}/comment/{author}")
	public PostDto addComment(@PathVariable String id, @PathVariable String author,
			@RequestBody MessageDto messageDto) {
		return postServise.addComment(id, author, messageDto);
	}

	@DeleteMapping("/post/{id}")
	public PostDto deletePost(@PathVariable String id) {
		return postServise.deletePost(id);
	}

	@PutMapping("/post/{id}")
	public PostDto updatePost(@PathVariable String id, @RequestBody NewPostDto newPostDto) {
		return postServise.updatePost(id, newPostDto);
	}

	@PutMapping("/post/{id}/like")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addLike(@PathVariable String id) {
		postServise.addLike(id);

	}

	@GetMapping("/posts/author/{author}")
	public Iterable<PostDto> findPostByAuthor(@PathVariable String author) {

		return postServise.findPostByAuthor(author);
	}

	@PostMapping("/poss/tags")
	public Iterable<PostDto> findPostByTags(@RequestBody List<String> tags) {

		return postServise.findPostByTags(tags);
	}

	@PostMapping("/posts/period")
	public Iterable<PostDto> findPostByPeriud(@RequestBody DatePeriudDto datePeriudDto) {
		return postServise.findPostByPeriud(datePeriudDto);
	}

}
