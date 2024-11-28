package com.abdulazizpr.rediwork.command.model.request;

import com.abdulazizpr.rediwork.command.model.CommandRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GetChartHistoryCommandRequest implements CommandRequest {
    @NotBlank
    @Size(min = 1, max = 10)
    private String symbol;

    @NotNull
    private LocalDateTime from;

    @NotNull
    private LocalDateTime to;
}