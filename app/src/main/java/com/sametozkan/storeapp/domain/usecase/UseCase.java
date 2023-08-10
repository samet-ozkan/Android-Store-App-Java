package com.sametozkan.storeapp.domain.usecase;

public abstract class UseCase<T, Params> {

    public abstract T execute(Params params);

    public abstract T execute();
}
