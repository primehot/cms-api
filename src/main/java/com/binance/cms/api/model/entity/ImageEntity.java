package com.binance.cms.api.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "image")
@Getter
@Setter
public class ImageEntity extends AbstractEntity {
    @Column(updatable = false, nullable = false)
    private Byte[] bytes;

    @Column(nullable = false)
    private Boolean isLinked = false;
}
