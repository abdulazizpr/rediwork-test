package com.abdulazizpr.rediwork.model.base.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetChartHistoryRequest {
    @NotBlank
    @Size(min = 1, max = 10)
    private String symbol;

    @NotNull
    private LocalDateTime from;

    @NotNull
    private LocalDateTime to;
}
