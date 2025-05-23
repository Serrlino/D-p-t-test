package com.project.tontine.service;

import com.project.tontine.model.Activation;
import com.project.tontine.model.Member;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.type.BasicCollectionType;
import org.springframework.stereotype.Service;
import com.project.tontine.repository.ActivationRepository;

import java.time.Instant;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MINUTES;

@Slf4j
@AllArgsConstructor
@Service
public class ActivationService {

    private ActivationRepository activationRepository;
    private EmailService emailService;

    public void create(Member member)
    {
        Instant creationInstant = Instant.now();

        Instant expirationInstant = creationInstant.plus(5, MINUTES);

        Random random = new Random();
        Integer code = random.nextInt(999999);

        Activation activation = new Activation(0, creationInstant, expirationInstant, code, member);

        //this.emailService.sendActivationCodeEmail(activation);

        activationRepository.save(activation);
    }

    public Activation readByCode(Integer activationCode)
    {
        return activationRepository.findByCode(activationCode).orElseThrow(() -> new RuntimeException("Votre code est invalide"));
    }
}
