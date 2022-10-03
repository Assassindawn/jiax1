package com.bms.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BmsProperties {

    @TableId(value = "way", type = IdType.AUTO)
    private String way;
    private String BicycleId;
    private String Temperature;
    private String Humidity;
    private String ElectricQuantity;
    private String Light;
    private String dateTime;




    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getBicycleId() {
        return BicycleId;
    }

    public void setBicycleId(String bicycleId) {
        BicycleId = bicycleId;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }

    public String getElectricQuantity() {
        return ElectricQuantity;
    }

    public void setElectricQuantity(String electricQuantity) {
        ElectricQuantity = electricQuantity;
    }

    public String getLight() {
        return Light;
    }

    public void setLight(String light) {
        Light = light;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
