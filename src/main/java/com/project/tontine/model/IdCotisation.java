package com.project.tontine.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdCotisation implements Serializable
{
    private Account account;
    private Meeting meeting;
}