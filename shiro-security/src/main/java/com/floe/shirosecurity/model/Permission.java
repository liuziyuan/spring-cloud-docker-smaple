package com.floe.shirosecurity.model;

import javax.persistence.Entity;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Permissions")
public class Permission extends IdEntity  {
    private String code;
    private String name;
    private String uri;
    private String type;
}
