package com.abdulazizpr.rediwork.command.fruits;

import com.abdulazizpr.rediwork.command.Command;
import com.abdulazizpr.rediwork.command.model.request.fruits.SaveFruitCommandRequest;
import com.abdulazizpr.rediwork.model.base.response.BaseResponse;

public interface SaveFruitCommand extends Command<BaseResponse<Object>, SaveFruitCommandRequest> {
}
