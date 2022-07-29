package uz.ml.delivering_rest.service;

import lombok.RequiredArgsConstructor;
import uz.ml.delivering_rest.mapper.BaseMapper;
import uz.ml.delivering_rest.repository.BaseRepository;

@RequiredArgsConstructor
public abstract class AbstractService<M extends BaseMapper, R extends BaseRepository> implements BaseService {
    protected final M mapper;
    protected final R repository;
}
