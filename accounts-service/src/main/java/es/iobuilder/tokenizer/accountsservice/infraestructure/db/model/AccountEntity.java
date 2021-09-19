package es.iobuilder.tokenizer.accountsservice.infraestructure.db.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


/**
 * Account DBO Object
 */
@Data
@Builder
@Entity
@Table(name = "accounts",uniqueConstraints = {@UniqueConstraint(columnNames = "number")})
public class AccountEntity {

    /**
     * Id
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * Account's Name
     */
    private String name;

    /**
     * Account's Number
     */
    private String number;

    /**
     * Account's User
     */
    private Long user;

    /**
     * Creation Date
     */
    private LocalDate created;


    /**
     * Movement list
     */
    @OneToMany(fetch = FetchType.LAZY)
    private List<MovementEntity> movementList;


}
