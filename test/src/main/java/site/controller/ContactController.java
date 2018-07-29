package site.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.exception.ResourceNotFoundException;
import site.model.Contact;
import site.repository.ContactRepository;

@RestController
@RequestMapping( "/api" )
public class ContactController {

    @Autowired
    ContactRepository contactRepository;

    /**
     * Get all example.  Get all messages.  You would probably want to move to secure or comment out
     * because anyone's messages and email can be viewed here.
     * @return
     */
    @GetMapping( "/contact" )
    public List<Contact> getAllNotes() {
    	
        return contactRepository.findAll();
        
    }

    /**
     * Post example. Insert a new message.
     * @param contact
     * @return
     */
    @PostMapping( "/contact/sendmsg/" )
    public Contact createNote( @Valid @RequestBody Contact contact ) {
    	
        return contactRepository.save( contact );
        
    }

    /**
     * Get one example. Select a message by id. Comment out or move to secure.
     * @param conId
     * @return
     */
    @GetMapping( "/contact/getmsg/{conId}" )
    public Contact getNoteById( @PathVariable( value = "conId" ) Long conId ) {
    	
        return contactRepository.findById( conId ).orElseThrow( () -> new ResourceNotFoundException( "Contact", "conId", conId ) );
        
    }

    /**
     * Put example. Update a contact message by id. Comment out or move to secure.
     * @param conId
     * @param contactDetails
     * @return
     */
    @PutMapping( "/contact/{id}" )
    public Contact updateNote( @PathVariable( value = "conId" ) Long conId, @Valid @RequestBody Contact contactDetails ) {

        Contact contact = contactRepository.findById( conId ).orElseThrow( () -> new ResourceNotFoundException( "Contact", "id", conId ) );
        contact.setConMessage( contactDetails.getConMessage() );
        Contact updatedContactMessage = contactRepository.save( contact );
        return updatedContactMessage;
        
    }

    /**
     * Delete example. Delete by id.  Comment out or move to secure.
     * @param noteId
     * @return
     */
    @DeleteMapping( "/contact/{id}" )
    public ResponseEntity<?> deleteNote( @PathVariable( value = "conId" ) Long conId ) {
        Contact contact = contactRepository.findById( conId ).orElseThrow( () -> new ResourceNotFoundException( "Contact", "conId", conId ) );
        contactRepository.delete( contact );
        return ResponseEntity.ok().build();
        
    }
    
}