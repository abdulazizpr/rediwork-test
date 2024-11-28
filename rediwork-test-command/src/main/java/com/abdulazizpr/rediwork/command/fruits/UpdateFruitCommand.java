package com.abdulazizpr.rediwork.command.fruits;

import com.abdulazizpr.rediwork.command.Command;
import com.abdulazizpr.rediwork.command.model.request.fruits.UpdateFruitCommandRequest;
import com.abdulazizpr.rediwork.model.base.response.BaseResponse;

public interface UpdateFruitCommand extends Command<BaseResponse<Object>, UpdateFruitCommandRequest> {
}
