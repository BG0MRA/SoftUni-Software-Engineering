package softuni.exam.models.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MechanicImportDto {
    //"email": "lrann0@t-online.de",
    //    "firstName": "Lorna",
    //    "lastName": "Rann",
    //    "phone": "462-463-0432"
    //•	first name – accepts char sequences as values where their character length value higher than or equal to 2.
    //•	last name – accepts char sequences as values where their character length value higher than or equal to 2.
    //•	email – an email – (must contains ‘@’ and ‘.’ – dot).
    //•	phone – accepts char sequences as values where their character length value higher than or equal to 2.

    @NotNull
    @Size(min = 2)
    private String firstName;
    @NotNull
    @Size(min = 2)
    private String lastName;
    @NotNull
    @Email
    private String email;
    @Size(min = 2)
    private String phone;

    public MechanicImportDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
