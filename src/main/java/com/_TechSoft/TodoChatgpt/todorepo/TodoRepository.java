package com._TechSoft.TodoChatgpt.todorepo;

import com._TechSoft.TodoChatgpt.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}

