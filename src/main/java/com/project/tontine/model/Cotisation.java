package com.project.tontine.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(IdCotisation.class)
@Table(schema = "global_schema")
public class Cotisation
{
    @Id
    @ManyToOne
    @JoinColumn
    private Account account;

    @Id
    @ManyToOne
    @JoinColumn(name = "num_meeting")
    private Meeting meeting;

    @Column
    private Float amount;
}
