package com.clonect.clothes.domain.clothes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Bottom")
@Getter @Setter
public class Bottom extends Clothes{
    private Long size;
}
