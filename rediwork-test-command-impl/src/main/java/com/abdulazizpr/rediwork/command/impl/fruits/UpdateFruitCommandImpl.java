package com.abdulazizpr.rediwork.command.impl.fruits;

import com.abdulazizpr.rediwork.command.fruits.UpdateFruitCommand;
import com.abdulazizpr.rediwork.command.model.request.fruits.UpdateFruitCommandRequest;
import com.abdulazizpr.rediwork.entity.Fruits;
import com.abdulazizpr.rediwork.helper.BaseResponseHelper;
import com.abdulazizpr.rediwork.model.base.response.BaseResponse;
import com.abdulazizpr.rediwork.repository.FruitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UpdateFruitCommandImpl implements UpdateFruitCommand {
    private static final String SUCCESS_MESSAGE = "Fruit success updated";
    private static final String ERROR_MESSAGE = "Fruit not found";

    private final FruitsRepository repository;

    @Override
    public Mono<BaseResponse<Object>> execute(UpdateFruitCommandRequest request) {
        return repository.findByIdAndDeletedAtIsNull(request.getId())
                .map(fruit -> updateDataFruit(fruit, request))
                .flatMap(fruit -> repository.updateFruitById(
                        fruit.getId(),
                        fruit.getName(),
                        fruit.getDescription(),
                        fruit.getStock()
                ))
                .map(fruit -> BaseResponseHelper.success(SUCCESS_MESSAGE))
                .switchIfEmpty(Mono.fromCallable(() -> BaseResponseHelper.badRequest(ERROR_MESSAGE)));
    }

    private static Fruits updateDataFruit(Fruits fruits, UpdateFruitCommandRequest request) {
        fruits.setName(request.getName());
        fruits.setDescription(request.getDescription());
        fruits.setStock(request.getStock());

        return fruits;
    }
}
