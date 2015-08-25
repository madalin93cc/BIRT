package birt.domain;

import javax.persistence.*;

/**
 * User JPA Entity
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "FIRSTNAME", length = 40, nullable = false)
    private String firstName;

    @Column(name = "LASTNAME", length = 40, nullable = false)
    private String lastName;

    @Column(name = "CIF", length = 40, nullable = false)
    private String cif;

    @Column(name = "ADDRESS", length = 100, nullable = false)
    private String address;

    @Column(name = "IBAN", length = 24, nullable = false)
    private String iban;

    @Column(name = "BANK", length = 40)
    private String bank;

    public User() {
    }

    public User(String firstName, String lastName, String cif, String address, String iban, String bank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cif = cif;
        this.address = address;
        this.iban = iban;
        this.bank = bank;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!cif.equals(user.cif)) return false;
        if (!address.equals(user.address)) return false;
        if (!iban.equals(user.iban)) return false;
        return !(bank != null ? !bank.equals(user.bank) : user.bank != null);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + cif.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + iban.hashCode();
        result = 31 * result + (bank != null ? bank.hashCode() : 0);
        return result;
    }
}
