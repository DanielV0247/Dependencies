package ru.netology.repository;

import ru.netology.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository {
    List<Post> all();
    Optional<Post> getById(long id);
    Post save(Post post);
    void removeById(long id);
}