package com.qsoft.camel.persistence.dao;

import com.qsoft.camel.persistence.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: anhnt
 * Date: 9/4/13
 * Time: 11:02 AM
 */

public interface EquipmentDAO extends JpaRepository<Equipment, Long>
{
}
