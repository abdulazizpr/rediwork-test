package com.abdulazizpr.rediwork.command.model.request.fruits;

import com.abdulazizpr.rediwork.command.model.CommandRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindFruitByIdCommandRequest implements CommandRequest {
    @NotBlank
    @NotNull
        private UUID id;
}
