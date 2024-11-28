package com.abdulazizpr.rediwork.command.impl.fruits;

import com.abdulazizpr.rediwork.command.fruits.DeleteFruitCommand;
import com.abdulazizpr.rediwork.command.model.request.fruits.FindFruitByIdCommandRequest;
import com.abdulazizpr.rediwork.helper.BaseResponseHelper;
import com.abdulazizpr.rediwork.model.base.response.BaseResponse;
import com.abdulazizpr.rediwork.repository.FruitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DeleteFruitCommandImpl implements DeleteFruitCommand {
    private static final String SUCCESS_MESSAGE = "Fruit was deleted";
    private static final String ERROR_MESSAGE = "Fruit not found";

    private final FruitsRepository repository;

    @Override
    public Mono<BaseResponse<Object>> execute(FindFruitByIdCommandRequest request) {
        return repository.findByIdAndDeletedAtIsNull(request.getId())
                .flatMap(fruit -> {
                    fruit.setDeletedAt(LocalDateTime.now());
                    return repository.softDeleteById(request.getId())
                            .then(Mono.fromCallable(() -> BaseResponseHelper.success(SUCCESS_MESSAGE)));
                })
                .switchIfEmpty(Mono.fromCallable(() -> BaseResponseHelper.badRequest(ERROR_MESSAGE)));
    }
}