package com.jumper.validation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UserBindParam {

    //用户id
    @NotNull
    @Min(1)
    private Long userId;

    //用户绑定记录来源，请使用枚举UserBindSourceEnum
    private int source;

    //应用id
    private String appId;

    //应用唯一id
    private String thiridId;

    //来源唯一id
    private String unionId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getThiridId() {
        return thiridId;
    }

    public void setThiridId(String thiridId) {
        this.thiridId = thiridId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

}
