package com.example.isw.BookManagement.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "member")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "*Please select member type")
    @NotNull(message = "*Please select member type")
    @Column(name = "type")
    private String type;

    @NotEmpty(message = "*Please enter fisrt name")
    @NotNull(message = "*Please enter fisrt name")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "*Please enter middle name")
    @NotNull(message = "*Please enter middle name")
    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "*Please select gender")
    @NotNull(message = "*Please select gender")
    @Column(name = "gender")
    private String gender;

    @NotNull(message = "*Please enter birth date")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "joining_date")
    @DateTimeFormat(pattern = "dd-MMM-yyyy")
    private Date joiningDate;

    @Column(name = "contact")
    private String contact;

    @NotEmpty(message = "Email cannot be empty")
    @NotNull(message = "Email cannot be empty")
    @Column(name = "email")
    private String email;

    public Member(@NotNull String type, @NotNull String firstName, @NotNull String middleName, @NotNull String lastName,
                  @NotNull String gender, @NotNull Date dateOfBirth, @NotNull Date joiningDate) {
        super();
        this.type = type;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.joiningDate = joiningDate;
    }

    public Member() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
