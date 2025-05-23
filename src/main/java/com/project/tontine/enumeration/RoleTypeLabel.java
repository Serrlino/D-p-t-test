package com.project.tontine.enumeration;

import lombok.Getter;

import java.util.List;

@Getter
public enum RoleTypeLabel
{
    SUPER_ADMIN(
        merge(PermissionLabel.ALL_GROUP.getPermissionLabels(),
            merge(PermissionLabel.ALL_MEETING.getPermissionLabels(),
                merge(PermissionLabel.ALL_PLANNED_MEETING.getPermissionLabels(), 
                    merge(PermissionLabel.ALL_COTISATION.getPermissionLabels(),
                        merge(PermissionLabel.ALL_POSSESSION.getPermissionLabels(),
                                PermissionLabel.ALL_MEMBER.getPermissionLabels()
        )))))),

    ADMIN(
        merge(PermissionLabel.ALL_GROUP.getPermissionLabels(),
            merge(PermissionLabel.ALL_MEETING.getPermissionLabels(),
                merge(PermissionLabel.ALL_PLANNED_MEETING.getPermissionLabels(), 
                    merge(PermissionLabel.ALL_COTISATION.getPermissionLabels(),
                        add(PermissionLabel.ALL_POSSESSION.getPermissionLabels(),
                            PermissionLabel.SELF
    )))))),

    PRESIDENT(
        merge(PermissionLabel.ALL_GROUP.getPermissionLabels(),
            merge(PermissionLabel.ALL_MEETING.getPermissionLabels(),
                merge(PermissionLabel.ALL_PLANNED_MEETING.getPermissionLabels(),
                    add(PermissionLabel.ALL_POSSESSION.getPermissionLabels(),
                            PermissionLabel.SELF
    ))))),

    SECRETARY(
        merge(PermissionLabel.ALL_MEETING.getPermissionLabels(),
            merge(PermissionLabel.ALL_PLANNED_MEETING.getPermissionLabels(),
                add(PermissionLabel.ALL_POSSESSION.getPermissionLabels(),
                        PermissionLabel.SELF
    )))),

    TREASURER(
        merge(PermissionLabel.ALL_PLANNED_MEETING.getPermissionLabels(),
            add(PermissionLabel.ALL_COTISATION.getPermissionLabels(),
                    PermissionLabel.SELF
    ))),

    MEMBER(List.of(PermissionLabel.SELF)),

    LOCKED;


    private List<PermissionLabel> permissionLabels;

    public static List<PermissionLabel> merge(List<PermissionLabel> permissionLabels1, List<PermissionLabel> permissionLabels2)
    {
        permissionLabels1.addAll(permissionLabels2);
        return permissionLabels1;
    }

    public static List<PermissionLabel> add(List<PermissionLabel> permissionLabels1, PermissionLabel permissionLabel)
    {
        permissionLabels1.add(permissionLabel);
        return permissionLabels1;
    }

    RoleTypeLabel(List<PermissionLabel> permissionLabels)
    {
        this.permissionLabels = permissionLabels;
    }

    RoleTypeLabel()
    {
    }
}
