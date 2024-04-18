package ie.janix.whitepapers.openapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Simple demo class representing basic customer information
 */
public class Customer {

  @NotBlank
  @Size(min = 0, max = 20)
  String firstname;

  @NotBlank
  @Size(min = 0, max = 20)
  String surname;

  @Email
  String email;

  public Customer(@JsonProperty("firstname") String firstname, @JsonProperty("surname") String surname, @JsonProperty("email") String email) {
    this.firstname = firstname;
    this.surname = surname;
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getSurname() {
    return surname;
  }

  public String getEmail() {
    return email;
  }
}
