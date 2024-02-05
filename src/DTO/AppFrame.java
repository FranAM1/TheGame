package DTO;

import Enums.AppFrameType;
import Enums.DataFrameType;

public class AppFrame {
    private AppFrameType type;

    private Object object;

    public AppFrame(AppFrameType type, Object object) {
        this.type = type;
        this.object = object;
    }
}
