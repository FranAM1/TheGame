package DTO;

import Enums.AppFrameType;
import Enums.DataFrameType;

import java.io.Serializable;

public class AppFrame implements Serializable {
    private AppFrameType type;

    private Object object;

    public AppFrame(AppFrameType type, Object object) {
        this.type = type;
        this.object = object;
    }
}
