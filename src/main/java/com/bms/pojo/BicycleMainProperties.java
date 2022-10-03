package com.bms.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BicycleMainProperties {


    private String BicycleId;
    private String BatteryId;
    private String BicycleName;
}
