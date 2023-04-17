package com.clonect.clothes.domain.clothes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("T")
@Getter @Setter
public class Top extends Clothes{
    private Long size;
}
