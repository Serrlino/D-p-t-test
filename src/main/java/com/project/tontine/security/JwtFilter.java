package com.project.tontine.security;

import com.project.tontine.service.JwtService;
import com.project.tontine.service.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Service
public class JwtFilter extends OncePerRequestFilter
{
    private MemberService memberService;
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException 
    {
        String token = null;
        String email = null;
        // Jwt jwt = null;
        boolean tokenExpiration = true;
        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer "))
        {
            token = authorization.substring(7);
            // jwt = jwtService.readJwtByValue(token);
            tokenExpiration = jwtService.isTokenExpired(token);
            email = jwtService.getClaimUsername(token);
        }

        if (!tokenExpiration
            // && jwt.getMember().getMemberEmail() == email
            && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            UserDetails userDetails = memberService.readByEmail(email);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}
