package com.abdulazizpr.rediwork.command.model.request.fruits;

import com.abdulazizpr.rediwork.command.model.CommandRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveFruitCommandRequest implements CommandRequest {
    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name is required")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Stock is required")
    @Positive(message = "Stock must be a positive number")
    @NumberFormat
    private BigInteger stock;
}