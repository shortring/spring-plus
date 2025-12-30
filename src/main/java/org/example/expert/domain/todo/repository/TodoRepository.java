package org.example.expert.domain.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long>, QTodoRepository {

    @Query("""
            SELECT t FROM Todo t 
            LEFT JOIN FETCH t.user u
            WHERE :weather is null or t.weather = :weather
            ORDER BY t.modifiedAt DESC
            """)
    Page<Todo> findAllByOrderByModifiedAtDescAndWeather(Pageable pageable, String weather);

//    @Query("SELECT t FROM Todo t " +
//            "LEFT JOIN t.user " +
//            "WHERE t.id = :todoId")
//    Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);

}
