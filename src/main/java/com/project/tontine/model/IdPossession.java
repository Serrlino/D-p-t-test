package com.project.tontine.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdPossession implements Serializable
{
    private Member member;
    private Role role;
}