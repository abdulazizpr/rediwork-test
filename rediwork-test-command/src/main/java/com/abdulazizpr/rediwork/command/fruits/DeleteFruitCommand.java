package com.abdulazizpr.rediwork.command.fruits;

import com.abdulazizpr.rediwork.command.Command;
import com.abdulazizpr.rediwork.command.model.request.fruits.FindFruitByIdCommandRequest;
import com.abdulazizpr.rediwork.model.base.response.BaseResponse;

public interface DeleteFruitCommand extends Command<BaseResponse<Object>, FindFruitByIdCommandRequest> {
}
