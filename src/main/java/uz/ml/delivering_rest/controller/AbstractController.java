package uz.ml.delivering_rest.controller;

import lombok.RequiredArgsConstructor;
import uz.ml.delivering_rest.service.BaseService;

@RequiredArgsConstructor
public abstract class AbstractController<S extends BaseService> {
    protected final S service;
}
