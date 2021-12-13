package com.bkap.util.base;

import java.util.List;

public interface IBaseReadWrite<O> {

    public abstract void writeFile(String path ,List<O> list);

    public abstract List<O> readFile(String path);

}
