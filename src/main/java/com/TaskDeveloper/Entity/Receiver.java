package com.TaskDeveloper.Entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import com.TaskDeveloper.TypesStatus.TypeStatus;
import org.springframework.boot.context.properties.bind.DefaultValue;

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

    @NotEmpty(message = "Cannot be empty")
    @NotNull(message = "Cannot be nullish")
    @Column
    private String cpfOrCnpj;

    @Email(message = "should be Email")
    @NotEmpty(message = "Cannot be empty")
    @NotNull(message = "Cannot be nullish")
    @Column
    private String email;

    @NotNull(message = "Cannot be nullish")
    @Column
    @Enumerated(EnumType.STRING)
    private TypeStatus status = TypeStatus.SKETCH;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="fk_receiver_id",referencedColumnName = "receiver_id")
    private List<Pix> pix;

    public Receiver(String name, Long receiverId, String cpfOrCnpj, String email, List<Pix> pix,TypeStatus status) {
        this.name = name;
        this.receiverId = receiverId;
        this.cpfOrCnpj = cpfOrCnpj;
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

    public void setReceiverId(Long receiver_id) {
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

    public void setCpfOrCnpj(String cpfOrCnpj){
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public String getCpfOrCnpj(){
        return this.cpfOrCnpj;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setStatus(TypeStatus status) {
        this.status = status;
    }

    public TypeStatus getStatus() {return this.status;}


}
