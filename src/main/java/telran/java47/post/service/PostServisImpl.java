package telran.java47.post.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java47.exception.PostNotFoundException;
import telran.java47.post.dao.PostRepository;
import telran.java47.post.dto.DatePeriudDto;
import telran.java47.post.dto.MessageDto;
import telran.java47.post.dto.NewPostDto;
import telran.java47.post.dto.PostDto;
import telran.java47.post.model.Comment;
import telran.java47.post.model.Post;

@Service
@RequiredArgsConstructor
public class PostServisImpl implements PostServise {

	final PostRepository postRepository;
	final ModelMapper modelMapper;

	@Override
	public PostDto addNewPost(String author, NewPostDto newPostDto) {
		Post post = modelMapper.map(newPostDto, Post.class);
		post.setAuthor(author);
		postRepository.save(post);
		return modelMapper.map(newPostDto, PostDto.class);
	}

	@Override
	public PostDto findPostById(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto addComment(String id, String author, MessageDto messageDto) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
		Comment comment = modelMapper.map(messageDto, Comment.class);
		comment.setUser(author);
		post.addComment(comment);
		postRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto deletePost(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
		postRepository.delete(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePost(String id, NewPostDto newPostDto) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
		post.setTitle(newPostDto.getTitle());
		post.setContent(newPostDto.getContent());
		if (!newPostDto.getTags().isEmpty()) {
			newPostDto.getTags().stream().forEach(e -> post.addTag(e));
		}
		postRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public void addLike(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException());
		post.addLike();
		postRepository.save(post);
	}

	@Override
	public Iterable<PostDto> findPostByAuthor(String author) {
		
		return postRepository.findAllByAuthor(author)
				.map(p-> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}
// not work
	@Override
	public Iterable<PostDto> findPostByTags(List<String> tags) {
		
		return postRepository.findByTagsInIgnoreCase(tags)
				.map(p -> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());

	}


	@Override
	public Iterable<PostDto> findPostByPeriud(DatePeriudDto datePeriudDto) {
		
		return postRepository.findByDateCreatedBetween(datePeriudDto.getDateFrom(), datePeriudDto.getDateTo())
				.map(p-> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

}
