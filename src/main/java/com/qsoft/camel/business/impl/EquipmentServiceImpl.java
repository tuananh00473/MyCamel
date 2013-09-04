package com.qsoft.camel.business.impl;

import com.qsoft.camel.business.EquipmentService;
import com.qsoft.camel.persistence.dao.EquipmentDAO;
import com.qsoft.camel.persistence.entity.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: anhnt
 * Date: 9/4/13
 * Time: 11:20 AM
 */
@Component("equipmentService")
public class EquipmentServiceImpl implements EquipmentService
{
    @Autowired
    EquipmentDAO equipmentDAO;

    @Override
    public Equipment add(String value)
    {
        Equipment equipment = new Equipment(value);
        equipmentDAO.save(equipment);
        return equipment;
    }

    @Override
    public void remove(Long equipmentId)
    {
        equipmentDAO.delete(equipmentId);
    }

    @Override
    public void update(Long equipmentId, String newValue)
    {
        Equipment equipment = equipmentDAO.findOne(equipmentId);
        equipment.setValue(newValue);
        equipmentDAO.saveAndFlush(equipment);
    }

    @Override
    public List<Equipment> getAllEquipment()
    {
        return equipmentDAO.findAll();
    }
}
