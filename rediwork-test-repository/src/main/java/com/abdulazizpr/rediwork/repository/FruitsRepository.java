package com.abdulazizpr.rediwork.repository;

import com.abdulazizpr.rediwork.entity.Fruits;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.util.UUID;

@Repository
public interface FruitsRepository extends R2dbcRepository<Fruits, UUID> {
    Mono<Fruits> findByIdAndDeletedAtIsNull(UUID id);

    @Query("SELECT COUNT(*) FROM fruits WHERE name ILIKE CONCAT('%', :name, '%') AND deleted_at IS NULL")
    Mono<Long> countByNameContaining(String name);

    @Query("SELECT * FROM fruits WHERE name ILIKE CONCAT('%', :name, '%') AND deleted_at IS NULL ORDER BY created_at DESC LIMIT :limit OFFSET :offset")
    Flux<Fruits> findAllByNameContaining(String name, int limit, int offset);

    @Modifying
    @Query("UPDATE fruits SET deleted_at = NOW() WHERE id = :id AND deleted_at IS NULL")
    Mono<Void> softDeleteById(UUID id);

    @Modifying
    @Query("UPDATE fruits SET id = :id, name = :name, description = :description, stock = :stock, updated_at = NOW() WHERE id = :id AND deleted_at IS NULL")
    Mono<Integer> updateFruitById(UUID id, String name, String description, BigInteger stock);
}
