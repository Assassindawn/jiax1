package com.bms.pojo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ElectricMachineryProperties {
    private String ElectricMachineryId;
    private String BicycleId;
    private String rpm;
    private String dateTime;
}
