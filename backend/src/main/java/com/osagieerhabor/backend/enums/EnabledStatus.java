package com.osagieerhabor.backend.enums;

import com.osagieerhabor.backend.exceptions.BuySellException;
import lombok.Getter;

@Getter
public enum EnabledStatus {
    DISABLED(0),
    ENABLED(1),
    PENDING_ENABLED(2),
    PENDING_DISABLED(3),
    BACK_OFFICE_DISABLED(4);

    Integer value;

    EnabledStatus(Integer value) {
        this.value = value;
    }

    public static EnabledStatus validate(String param){
        EnabledStatus status = null;
        for(EnabledStatus value : EnabledStatus.values()){
            if(value == EnabledStatus.valueOf(param.toUpperCase())){
                status = value;
            }
        }
        if(status ==  null){
            throw new BuySellException("Invalid Enum");
        }
        return status;
    }
}
