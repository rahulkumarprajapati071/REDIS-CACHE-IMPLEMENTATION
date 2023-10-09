package in.glootech.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.glootech.entity.Book;

@RestController
public class BookController {
	private static final String HASH_KEY = "BOOKS";
	
	private HashOperations<String, Integer, Book> hashOperations;
	
	public BookController(RedisTemplate<String, Book> redisTemplate) {
		hashOperations = redisTemplate.opsForHash();
	}
	
	@PostMapping("/add")
	public String addBook(@RequestBody Book book) {
		hashOperations.put(HASH_KEY, book.getBookId(), book);
		return "Book Added Succesfuly";
	}
	
	@GetMapping("/get/{id}")
	public Book getBook(@PathVariable Integer id) {
		Book book = hashOperations.get(HASH_KEY, id);
		return book;
	}
	
	@GetMapping("/get")
	public List<Book> getBooks(){
		List<Book> book = new ArrayList<>();
	    Map<Integer, Book> entries = hashOperations.entries(HASH_KEY);

	    for (Object value : entries.values()) {
	        book.add((Book) value);
	    }

	    return book;
	}
	
}
