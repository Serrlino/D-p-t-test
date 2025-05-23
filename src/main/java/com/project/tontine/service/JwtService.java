package com.project.tontine.service;

import com.project.tontine.model.Jwt;
import com.project.tontine.repository.JwtRepository;

import com.project.tontine.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.*;
import java.util.function.Function;

import com.project.tontine.model.Member;
import javax.crypto.SecretKey;

@AllArgsConstructor
@Service
public class JwtService
{
    private MemberRepository memberRepository;
    private JwtRepository jwtRepository;

//---------------------------------------------------------------CRUD

    public Jwt readById(Integer id)
    {
        return jwtRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
    }

    public Jwt readByValue(String jwtValue)
    {
        return jwtRepository.findByValue(jwtValue).orElseThrow(() -> new RuntimeException(""));
    }

    public ArrayList<Jwt> readAll()
    {
        return new ArrayList<>(jwtRepository.findAll());
    }

    public void deleteById(Integer id)
    {
        readById(id);
        jwtRepository.deleteById(id);
    }

    public void deleteAll()
    {
        jwtRepository.deleteAll();
    }
//---------------------------------------------------------------CRUD


    private Claims getAllClaims(String token)
    {
        return Jwts.parser()
            .verifyWith(getKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    private <T> T getClaim(String token, Function<Claims, T> function)
    {
        Claims claims = getAllClaims(token);
        return function.apply(claims);
    }

    public String getClaimUsername(String token)
    {
        return getClaim(token, Claims::getSubject);
    }
//---------------------------------------------------------------------------------------1
    public Integer getClaimIdMember(String token)
    {
        return getClaim(token, claims -> claims.get("idMember", Integer.class));
    }
//---------------------------------------------------------------------------------------1
    private Date getExpirationDateFromToken(String token)
    {
        return getClaim(token, Claims::getExpiration);
    }
//---------------------------------------------------------------------------------------2
    private Map<String, String> generateJwt(Member member)
    {
        final long currentTime = System.currentTimeMillis();
        final long expirationTime = currentTime + 1000 * 60  * 20;

        final Map<String, Object> claims = new HashMap<>()
        {{
            put("idMember", member.getId());
            put("memberEmail", member.getEmail());
            put("memberNumber", member.getNumber());
            put("memberPassword", member.getPassword());

            // put(Claims.ID, member.getId());
            put(Claims.EXPIRATION, new Date(expirationTime));
            put(Claims.SUBJECT, member.getUsername());
        }};

        final String bearer = Jwts.builder()
            .setIssuedAt(new Date(currentTime))
            .setExpiration(new Date(expirationTime))
            .setSubject(member.getUsername())
            .setClaims(claims)
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();

        return new HashMap<>(Map.of("bearer", bearer));
    }

    public Map<String, String> generate(String email)
    {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Membre n'existe pas présent"));

        disableJwt(member);

        Map<String, String> jwtMap = generateJwt(member);
        Jwt jwt = new Jwt(0, jwtMap.get("bearer"), false, member);

        jwtRepository.save(jwt);

        return jwtMap;
    }
//---------------------------------------------------------------------------------------2
    private SecretKey getKey()
    {
        String KEY = "608f36e92dc66d97d5933f0e6371493cb4fc05b1aa8f8de64014732472303a7c";
        final byte[] decoder = Decoders.BASE64.decode(KEY);
        //KEY.getBytes(StandardCharsets.UTF_8)

        return (SecretKey) Keys.hmacShaKeyFor(decoder);
    }

    public void disableJwt(Member member)
    {
        Optional<Jwt> optionalJwt = jwtRepository.findByMemberAndDeactivation(member, false);

        if(optionalJwt.isPresent())
        {
            Jwt jwt = optionalJwt.get();

            jwt.setDeactivation(true);
            jwtRepository.save(jwt);
        }
    }

    public boolean isTokenExpired(String token)
    {
        Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    // @Transactional
    // @Scheduled(cron = "1 * * * * *")
    // public void clearDatabase()
    // {
    //     jwtRepository.deleteAllByJwtDesactivation(true);
    // }
}