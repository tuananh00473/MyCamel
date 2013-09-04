package com.qsoft.camel.persistence.entity;

import javax.persistence.*;

/**
 * User: anhnt
 * Date: 9/4/13
 * Time: 8:42 AM
 */

@Entity
@Table(name = "equipment")
public class Equipment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "web_id")
    private Long webId;

    @Column(name = "value")
    private String value;

    public Equipment(String value)
    {
        this.value = value;
    }

    public Equipment()
    {
    }

    public Long getWebId()
    {
        return webId;
    }

    public void setWebId(Long webId)
    {
        this.webId = webId;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return value;
    }
}
