package ru.netology.config;

import ru.netology.controller.PostController;
import ru.netology.repository.PostRepository;
import ru.netology.repository.PostRepositoryStubImpl;
import ru.netology.service.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    public PostController postController (PostService postService){
        return new PostController(postService);
    }
    @Bean
    public PostService postService (PostRepository postRepository){
        return new PostService(postRepository);
    }
    @Bean
    public PostRepository postRepository(){
        return new PostRepositoryStubImpl();
    }



}