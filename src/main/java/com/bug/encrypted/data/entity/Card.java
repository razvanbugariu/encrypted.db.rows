package com.bug.encrypted.data.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    @Convert(converter = StringAttributeConverter.class)
    private String cardNumber;

    @Column
    @Convert(converter = StringAttributeConverter.class)
    private String name;

    @Column
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate expiryDate;
}
