package DTO;

import Enums.DataFrameType;

public class DataFrame {
    private DataFrameType type;

    private Object object;

    public DataFrame(DataFrameType type, Object object) {
        this.type = type;
        this.object = object;
    }
}
