package com.example.swt_kwt_aleksandar_simikic.Repository;

import com.example.swt_kwt_aleksandar_simikic.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCreatedBy(Long userId);
    List<Post> findByContainedById(Long groupId);
    Post findPostByContainedById(Long groupId);
}
