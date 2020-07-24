package com.osagieerhabor.backend.dto;

import com.osagieerhabor.backend.enums.EnabledStatus;
import lombok.Data;

@Data
public class GeneralDto {
    private EnabledStatus status;
}
