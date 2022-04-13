package com.example.labproject.data.mail;

import com.example.labproject.domain.model.Mail;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MailRepository extends CrudRepository<Mail, Long> {
    long countByMailName(String name);

    Optional<Mail> findByMailName(String mailName);
    Optional<Mail> findAllByMailDescription(String mailDescription);
}
