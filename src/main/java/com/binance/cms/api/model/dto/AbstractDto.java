package com.binance.cms.api.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractDto {

    private String id;

    private String createdAt;

    private String modifiedAt;

}
