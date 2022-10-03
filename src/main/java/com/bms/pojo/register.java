package com.bms.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Api
@ApiModel("user用户实体类")
public class register {

    private String uuid;
    @ApiModelProperty("用户名")
    private String username;

    private String password;

    @ApiModelProperty("版本")
    private int version;

    @ApiModelProperty("用户权限")
    private int Serialnumber;

    @TableField(exist = false)
    private String uerstype;

}
