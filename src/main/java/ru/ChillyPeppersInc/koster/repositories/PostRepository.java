package ru.ChillyPeppersInc.koster.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ChillyPeppersInc.koster.models.Post;
import ru.ChillyPeppersInc.koster.models.User;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    // Найти все посты пользователя
    List<Post> findByUser(User user);

    // Найти все посты пользователя с пагинацией
    List<Post> findByUserOrderByCreatedAtDesc(User user, org.springframework.data.domain.Pageable pageable);

    // Найти все публичные посты
    List<Post> findByIsPublicTrueOrderByCreatedAtDesc();

    // Найти посты по статусу
    List<Post> findByStatusOrderByCreatedAtDesc(String status);

    // Найти посты, созданные после указанной даты
    List<Post> findByCreatedAtAfterOrderByCreatedAtDesc(Date date);

    // Найти посты по местоположению
    List<Post> findByGeolocationContainingIgnoreCaseOrderByCreatedAtDesc(String location);

    // Найти посты, содержащие определенный текст
    List<Post> findByContentContainingIgnoreCaseOrderByCreatedAtDesc(String keyword);

    // Обновить статус поста
    @Modifying
    @Query("UPDATE Post p SET p.status = :status WHERE p.id = :id")
    void updatePostStatus(@Param("id") Long id, @Param("status") String status);

    // Мягкое удаление поста (установка deletedAt)
    @Modifying
    @Query("UPDATE Post p SET p.deletedAt = CURRENT_DATE WHERE p.id = :id")
    void softDelete(@Param("id") Long id);

    // Проверить, принадлежит ли пост пользователю
    boolean existsByIdAndUser(Long postId, User user);
}