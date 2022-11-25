package com.xcx.test;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Supplier;

@Getter
@Setter
public abstract class BaseTreeNode<T> {
    private Supplier<Long> idAction;
    private Supplier<Long> parentIdAction;
    private List<T> Children;

    public abstract void init();
}
