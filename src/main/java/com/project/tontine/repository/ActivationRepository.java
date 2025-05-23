package com.project.tontine.repository;

import com.project.tontine.model.Activation;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivationRepository extends JpaRepository<Activation, Integer> {

    Optional<Activation> findByCode(Integer code);
}
