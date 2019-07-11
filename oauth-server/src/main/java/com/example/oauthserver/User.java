package com.example.oauthserver;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "user_login")
public class User implements Serializable {
    @GeneratedValue
    @Id
    private int userId;
    private String passPhrase;
    @NotEmpty
    @Column(unique = true)
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private boolean locked;
    //@OneToMany(fetch = FetchType.EAGER)
    //private Set<Account> accounts = new HashSet<Account>();

    public User() {

    }

    public User(String fN, String lN, String uN,
                String pwd, String addr, String eml,
                int uID, String phn, String passPh
    ) {
        this.firstName = fN;
        this.lastName = lN;
        this.userName = uN;
        this.userId = uID;
        this.email = eml;
        this.phoneNumber = phn;
        this.address = addr;
        this.password = pwd;
        this.passPhrase = passPh;
    }

    public String getPassPhrase() {
        return passPhrase;
    }

    public void setPassPhrase(String s) {
        passPhrase = s;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String u) {
        userName = u;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String p) {
        phoneNumber = p;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String a) {
        address = a;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String em) {
        email = em;
    }

    public int getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String p) {
        password = p;
    }

//    public void addAccount(Account acc) {
//        accounts.add(acc);
//    }
//
//    public Set<Account> getAccounts() {
//        return accounts;
//    }

    public String getPassPhraseCharAt(int index) {
        if (index >= passPhrase.length() || index < 0) {
            return "";
        } else {
            return new Character(passPhrase.charAt(index)).toString();
        }
    }

    public boolean isLocked() {
        return locked;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", passPhrase='" + passPhrase + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", locked=" + locked +
                // ", accounts=" + accounts +
                '}';
    }
}
