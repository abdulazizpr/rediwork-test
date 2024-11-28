package com.abdulazizpr.rediwork.command.impl.fruits;

import com.abdulazizpr.rediwork.command.fruits.GetAllFruitsPaginationCommand;
import com.abdulazizpr.rediwork.command.model.request.fruits.GetAllFruitsCommandRequest;
import com.abdulazizpr.rediwork.entity.Fruits;
import com.abdulazizpr.rediwork.helper.BaseListPaginationResponseHelper;
import com.abdulazizpr.rediwork.model.base.response.BaseListPaginationResponse;
import com.abdulazizpr.rediwork.repository.FruitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GetAllFruitsPaginationCommandImpl implements GetAllFruitsPaginationCommand {

    private final FruitsRepository repository;

    @Override
    public Mono<BaseListPaginationResponse<Fruits>> execute(GetAllFruitsCommandRequest request) {
        int page = request.getPage();
        int size = request.getSize();
        int offset = (page > 1) ? (page - 1) * size : 0;

        return repository.countByNameContaining(request.getName())
                .flatMap(totalItems -> repository.findAllByNameContaining(request.getName(), size, offset)
                        .collectList()
                        .map(fruits -> BaseListPaginationResponseHelper.success(fruits, page, size, totalItems)));
    }
}