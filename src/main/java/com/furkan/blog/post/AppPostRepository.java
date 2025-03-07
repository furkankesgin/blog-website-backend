package com.furkan.blog.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppPostRepository extends JpaRepository<AppPost, Long> {
}
