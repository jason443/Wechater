package com.jason.wechater.bean;

/**
 * Created by jason on 2017/3/16.
 */

public class ConverBean {

    private String equipmentName;

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public ConverBean(String equipmentName) {

        this.equipmentName = equipmentName;
    }
}
