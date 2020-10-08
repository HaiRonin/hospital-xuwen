package com.ruoyi.common.enums;

/**
 * Android_客户端标识
 *
 */
public enum AppClientType
{
    /** 用户状态 */
    PATIENT("1","patient_","android.patient.version"),
    DOC("2","doc_","android.doc.version");

    private String type;
    private String name;
    private final String info;

    private AppClientType(String type,String name,String info)
    {
        this.type = type;
        this.name = name;
        this.info = info;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getInfo()
    {
        return info;
    }

    public static AppClientType getByType(String type){
        AppClientType[] values = AppClientType.values();
        for(AppClientType appClientType : values  ){
            if(appClientType.type.equals(type)){
                return appClientType;
            }
        }
        return null;
    }
}
