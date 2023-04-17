package com.clonect.clothes.domain.clothes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("S")
@Getter @Setter
public class Shoes extends Clothes{
    private Long size;
}
