package com.abdulazizpr.rediwork.command.fruits;

import com.abdulazizpr.rediwork.command.Command;
import com.abdulazizpr.rediwork.command.model.request.fruits.GetAllFruitsCommandRequest;
import com.abdulazizpr.rediwork.entity.Fruits;
import com.abdulazizpr.rediwork.model.base.response.BaseListPaginationResponse;

public interface GetAllFruitsPaginationCommand extends Command<
        BaseListPaginationResponse<Fruits>, GetAllFruitsCommandRequest> {
}
