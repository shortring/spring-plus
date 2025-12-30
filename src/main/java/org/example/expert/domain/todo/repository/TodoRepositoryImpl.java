package org.example.expert.domain.todo.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.example.expert.domain.todo.entity.QTodo.todo;

@Repository
@RequiredArgsConstructor
public class TodoRepositoryImpl implements QTodoRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId) {
        return Optional.ofNullable(
                jpaQueryFactory
                .select(Projections.constructor(
                        Todo.class,
                        todo.user
                ))
                .from(todo)
                .leftJoin(todo.user)
                .where(todo.user.id.eq(todoId))
                .fetchOne()
        );
    }

}