package com.TaskDeveloper.Entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Transactional
@Entity
@Table(name = "receiver")
public class Receiver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "receiver_id")
    private Long receiverId;
    @Length(min = 3)
    @NotEmpty(message = "Cannot be empty")
    @NotNull(message = "Cannot be nullish")
    @Column
    private String name;
    @CPF(message = "should be cpf")
    @NotEmpty(message = "Cannot be empty")
    @NotNull(message = "Cannot be nullish")
    @Column
    private String cpf;
    @Email(message = "should be Email")
    @NotEmpty(message = "Cannot be empty")
    @NotNull(message = "Cannot be nullish")
    @Column
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="fk_receiver_id",referencedColumnName = "receiver_id")
    private List<Pix> pix;

    public Receiver(String name, Long receiverId, String cpf, String email, List<Pix> pix) {
        this.name = name;
        this.receiverId = receiverId;
        this.cpf = cpf;
        this.email = email;
        this.pix = pix;
    }

    public Receiver(){

    }

    public List<Pix> getPix() {
        return pix;
    }

    public void setPix(List<Pix> pix) {
        this.pix = pix;
    }

    public void setReceiver(Long receiver_id) {
        this.receiverId = receiver_id;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
