package com.qsoft.camel.business;

import com.qsoft.camel.persistence.entity.Equipment;

import java.util.List;

/**
 * User: anhnt
 * Date: 9/4/13
 * Time: 11:19 AM
 */


public interface EquipmentService
{
    public Equipment add(String value);

    public void remove(Long equipmentId);

    public void update(Long equipmentId, String newValue);

    public List<Equipment> getAllEquipment();
}
