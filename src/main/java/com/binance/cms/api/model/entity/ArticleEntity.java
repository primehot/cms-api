package com.binance.cms.api.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "article")
public class ArticleEntity extends AbstractEntity {
}
