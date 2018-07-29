package site.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table( name = "contact" )
@JsonIgnoreProperties( value = {"conCreatedDate"}, allowGetters = true )
public class Contact {
	
	/**
	 * Maps to table column 'con_id'.
	 */
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long conId;
	
	/**
	 * Maps to table column 'con_email'.
	 */
	@NotBlank
	private String conEmail;
	
	/**
	 * Maps to table column 'con_message'.
	 */
	@NotBlank
	private String conMessage;	
	
	/**
	 * Maps to table column 'con_created_date'.
	 */
	@CreationTimestamp
	private Date conCreatedDate;	

	public Long getConId() {
		return this.conId;
	}
	
	public void setConId( Long conId ) {
		this.conId = conId;
	}
	
	public String getConEmail() {
		return this.conEmail; 
	}
	
	public void setConEmail( String conEmail ) {
		this.conEmail = conEmail; 
	}
	
	public String getConMessage() {
		return this.conMessage; 
	}
	
	public void setConMessage( String conMessage ) {
		this.conMessage = conMessage; 
	}
	
	public Date getConCreatedDate() {
		return this.conCreatedDate;		
	}
	
	public void setConCreatedDate( Date conCreatedDate ) {
		this.conCreatedDate = conCreatedDate;
	}
	
}
