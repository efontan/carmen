package com.despegar.hackaton.carmen.domain.mapper;

public interface Mapper<T, M> {
    M map(T object);
}
