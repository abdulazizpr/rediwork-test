package com.abdulazizpr.rediwork.command.impl.fruits;

import com.abdulazizpr.rediwork.command.fruits.SaveFruitCommand;
import com.abdulazizpr.rediwork.command.model.request.fruits.SaveFruitCommandRequest;
import com.abdulazizpr.rediwork.entity.Fruits;
import com.abdulazizpr.rediwork.helper.BaseResponseHelper;
import com.abdulazizpr.rediwork.model.base.response.BaseResponse;
import com.abdulazizpr.rediwork.repository.FruitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SaveFruitCommandImpl implements SaveFruitCommand {
    private static final String SUCCESS_MESSAGE = "Fruit success saved";

    private final FruitsRepository repository;

    @Override
    public Mono<BaseResponse<Object>> execute(SaveFruitCommandRequest request) {
        return repository.save(createFruitData(request))
                .map(fruit -> BaseResponseHelper.success(SUCCESS_MESSAGE));
    }

    private static Fruits createFruitData(SaveFruitCommandRequest request) {
        Fruits fruit = new Fruits();

        fruit.setName(request.getName());
        fruit.setDescription(request.getDescription());
        fruit.setStock(request.getStock());

        return fruit;
    }
}
