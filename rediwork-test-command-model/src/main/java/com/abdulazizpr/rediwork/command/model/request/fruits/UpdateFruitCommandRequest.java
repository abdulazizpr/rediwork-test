package com.abdulazizpr.rediwork.command.model.request.fruits;

import com.abdulazizpr.rediwork.command.model.CommandRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFruitCommandRequest implements CommandRequest {
    @NotBlank
    @NotNull
    private UUID id;

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String description;

    @NotBlank
    @NotNull
    private BigInteger stock;
}
