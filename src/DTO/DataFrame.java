package DTO;

import Enums.DataFrameType;

import java.io.Serializable;


public class DataFrame implements Serializable {
    private DataFrameType type;

    private Object object;

    public DataFrame(DataFrameType type, Object object){
        this.type = type;
        this.object = object;
    }

    public DataFrameType getType() {
        return type;
    }
}
