package com.abdulazizpr.rediwork.command.impl.fruits;

import com.abdulazizpr.rediwork.command.fruits.FindFruitByIdCommand;
import com.abdulazizpr.rediwork.command.model.request.fruits.FindFruitByIdCommandRequest;
import com.abdulazizpr.rediwork.entity.Fruits;
import com.abdulazizpr.rediwork.helper.BaseResponseHelper;
import com.abdulazizpr.rediwork.model.base.response.BaseResponse;
import com.abdulazizpr.rediwork.repository.FruitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FindFruitByIdCommandImpl implements FindFruitByIdCommand {
    private static final String ERROR_MESSAGE = "Fruit not found";

    private final FruitsRepository repository;

    @Override
    public Mono<BaseResponse<Fruits>> execute(FindFruitByIdCommandRequest request) {
        return repository.findByIdAndDeletedAtIsNull(request.getId())
                .map(BaseResponseHelper::success)
                .switchIfEmpty(Mono.fromCallable(() ->
                        BaseResponseHelper.badRequest(ERROR_MESSAGE)));
    }
}
