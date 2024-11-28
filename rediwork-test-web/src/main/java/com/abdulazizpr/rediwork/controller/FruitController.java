package com.abdulazizpr.rediwork.controller;

import com.abdulazizpr.rediwork.command.CommandExecutor;
import com.abdulazizpr.rediwork.command.fruits.*;
import com.abdulazizpr.rediwork.command.model.request.fruits.FindFruitByIdCommandRequest;
import com.abdulazizpr.rediwork.command.model.request.fruits.GetAllFruitsCommandRequest;
import com.abdulazizpr.rediwork.command.model.request.fruits.SaveFruitCommandRequest;
import com.abdulazizpr.rediwork.command.model.request.fruits.UpdateFruitCommandRequest;
import com.abdulazizpr.rediwork.entity.Fruits;
import com.abdulazizpr.rediwork.model.base.request.fruit.SaveFruitRequest;
import com.abdulazizpr.rediwork.model.base.response.BaseListPaginationResponse;
import com.abdulazizpr.rediwork.model.base.response.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Tag(name = "Fruit")
@RestController
@RequestMapping("/api/v1/fruits")
public class FruitController {

    @Autowired
    private CommandExecutor commandExecutor;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<BaseListPaginationResponse<Fruits>> getAllFruitsPaginate(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "name", required=false, defaultValue = "") String name
    ) {
        return commandExecutor.execute(GetAllFruitsPaginationCommand.class,
                new GetAllFruitsCommandRequest(
                        Optional.of(page).orElse(1),
                        Optional.of(size).orElse(10),
                        name));
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    ) Mono<BaseResponse<Fruits>> findFruitById(@PathVariable("id") UUID id) {
        return commandExecutor.execute(FindFruitByIdCommand.class,
                new FindFruitByIdCommandRequest(id));
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    ) Mono<BaseResponse<Object>> updateFruitById(@PathVariable("id") UUID id,
        @RequestBody @Valid SaveFruitRequest request) {
        UpdateFruitCommandRequest commandRequest = new UpdateFruitCommandRequest(
                id,
                request.getName(),
                request.getDescription(),
                request.getStock()
        );

        return commandExecutor.execute(UpdateFruitCommand.class, commandRequest);
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    ) Mono<BaseResponse<Object>> saveFruit(@RequestBody @Valid SaveFruitRequest request) {
        SaveFruitCommandRequest commandRequest = new SaveFruitCommandRequest(
                request.getName(),
                request.getDescription(),
                request.getStock()
        );

        return commandExecutor.execute(SaveFruitCommand.class, commandRequest);
    }

    @DeleteMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    ) Mono<BaseResponse<Object>> deleteFruit(@PathVariable("id") UUID id) {
        return commandExecutor.execute(DeleteFruitCommand.class,
                new FindFruitByIdCommandRequest(id));
    }

}
