package site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import site.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}