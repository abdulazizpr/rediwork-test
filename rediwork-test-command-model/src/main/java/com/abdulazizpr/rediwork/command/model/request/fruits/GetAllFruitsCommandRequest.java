package com.abdulazizpr.rediwork.command.model.request.fruits;

import com.abdulazizpr.rediwork.command.model.CommandRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAllFruitsCommandRequest implements CommandRequest {
    private Integer page;
    private Integer size;
    private String name;
}
