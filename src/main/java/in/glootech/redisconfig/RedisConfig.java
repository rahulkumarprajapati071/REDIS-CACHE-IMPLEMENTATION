package in.glootech.redisconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import in.glootech.entity.Book;

@Configuration
public class RedisConfig {
	@Bean
	public JedisConnectionFactory getJedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		return factory;
	}
	
	@Bean
	@Primary
	public RedisTemplate<String, Book> getRedisTemplate(JedisConnectionFactory factory){
		RedisTemplate<String, Book> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(factory);
		return redisTemplate;
	}
	
}
