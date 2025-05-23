package com.project.tontine.controller;

import com.project.tontine.dto.AuthentificationDTO;
import com.project.tontine.model.Member;
import com.project.tontine.service.JwtService;
import com.project.tontine.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("authentication")
public class AuthenticationController
{
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private MemberService memberService;

    @PostMapping(path = "activate")
    public Map<String, String> activateAccount(@RequestBody Map<String, String> activationMap)
    {
        try
        {
            String code = activationMap.get("code");

            if (ControllerTester.notOnlyNumber(code))
            {
                throw new RuntimeException("Code d'activation invalide");
            }

            if (code.length() < 6)
            {
                throw new RuntimeException("Code d'activation trop court");
            }

            Map<String, Integer> map = new HashMap<>(){{
                put("code", Integer.parseInt(code));
            }};

            memberService.activate(map);
            return Map.of("message", "");
        }

        catch(RuntimeException re)
        {
          return Map.of("message", re.getMessage());
        }
    }

    @PostMapping(path = "connect")
    public Map<String, String> connectAccount(@RequestBody AuthentificationDTO authentificationDTO)
    {

        String username = authentificationDTO.username();

        final Authentication authenticate = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, authentificationDTO.password())
        );

        if(authenticate.isAuthenticated())
        {
            Map<String, String > map = jwtService.generate(username);
            map.put("message", "");

            return map;
        }
        
        else
        {
            return Map.of("message", "_____");
        }
    }

    @PostMapping(path = "disconnect")
    public Map<String, String> disconnectMember()
    {
        Member member =  (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        jwtService.disableJwt(member);

        return new HashMap<>(){{
           put("message", "");
        }};
    }
}
