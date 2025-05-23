package com.project.tontine.enumeration;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum PermissionLabel
{
    SELF,

    CREATE_GROUP,
    READ_GROUP,
    UPDATE_GROUP,
    DELETE_GROUP,
    EXECUTE_GROUP,

    CREATE_MEETING,
    READ_MEETING,
    DELETE_MEETING,
    EXECUTE_MEETING,

    CREATE_PLANNED_MEETING,
    READ_PLANNED_MEETING,
    // UPDATE_PLANNED_MEETING,
    DELETE_PLANNED_MEETING,
    EXECUTE_PLANNED_MEETING,

    CREATE_COTISATION,
    READ_COTISATION,
    // DELETE_COTISATION,
    EXECUTE_COTISATION,

    CREATE_POSSESSION,
    READ_POSSESSION,
    DELETE_POSSESSION,
    EXECUTE_POSSESSION,

    CREATE_MEMBER,
    READ_MEMBER,
    UPDATE_MEMBER,
    DELETE_MEMBER,
    EXECUTE_MEMBER,

    ALL_GROUP(new ArrayList<>(List.of(
            CREATE_GROUP,
            READ_GROUP,
            UPDATE_GROUP,
            DELETE_GROUP,
            EXECUTE_GROUP
    ))),
    ALL_MEETING(new ArrayList<>(List.of(
        CREATE_MEETING,
        READ_MEETING,
        DELETE_MEETING,
        EXECUTE_MEETING
    ))),
    ALL_PLANNED_MEETING(new ArrayList<>(List.of(
        CREATE_PLANNED_MEETING,
        READ_PLANNED_MEETING,
        DELETE_PLANNED_MEETING,
        EXECUTE_PLANNED_MEETING
    ))),
    ALL_COTISATION(new ArrayList<>(List.of(
        CREATE_COTISATION,
        READ_COTISATION,
        EXECUTE_COTISATION
    ))),
    ALL_POSSESSION(new ArrayList<>(List.of(
        CREATE_POSSESSION,
        READ_POSSESSION,
        DELETE_POSSESSION,
        EXECUTE_POSSESSION
    ))),
    ALL_MEMBER(new ArrayList<>(List.of(
        CREATE_MEMBER,
        READ_MEMBER,
        UPDATE_MEMBER,
        DELETE_MEMBER,
        EXECUTE_MEMBER
    ))),

    ALL(new ArrayList<>(List.of(
        ALL_GROUP,
        ALL_MEETING,
        ALL_PLANNED_MEETING,
        ALL_COTISATION,
        ALL_POSSESSION,
        ALL_MEMBER
    )));


    private List<PermissionLabel> permissionLabels;

    PermissionLabel()
    {
    }

    PermissionLabel(List<PermissionLabel> permissionLabels)
    {
        this.permissionLabels = permissionLabels;
    }
}