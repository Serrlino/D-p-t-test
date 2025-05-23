package com.project.tontine.dto;

import com.project.tontine.model.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO
{
    private String name;
    private String firstname;
    private String number;
    private String email;
    private String password;
    private Boolean enable = false;

    public Member createMember()
    {
        Integer number = 0;

        if(this.number != null) {
            number = Integer.parseInt(this.number);
        }
        else {
            number = null;
        }

        return new Member(null, name, firstname, number, email, password, enable, null, null, null, null, null, null, null, null, null);
    }
}