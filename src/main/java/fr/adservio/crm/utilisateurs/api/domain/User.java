package fr.adservio.crm.utilisateurs.api.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * The user object represents one registered user in CRM
 *
 * @author adservio
 * @version 0.1
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "user_table")
public class User {
    @Id
    @Column(unique = true, nullable = false)
    private String id;

    /******************************************************************************************************
     *                                      Personal Informations
     ******************************************************************************************************/

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column
    private String secondName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String userName;

    @Column
    private String suffixe;

    @Column
    private String mobile;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    private boolean enabled;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date updatedAt;

    /********************************************************************************************************
     *                                      Information Adress
     ********************************************************************************************************/
    @Column
    private String street;

    @Column
    private String city;

    @Column
    private String province;

    @Column
    private Long postalCode;

    @Column
    private String country;

    /********************************************************************************************************
     *                                      Professionel informations
     ********************************************************************************************************/

    @Column
    private String company;

    @Column
    private String service;

    @Column
    private String division;

    @Column
    private String matricule;

    @Column
    private Long buisnessUnitCode;

    @OneToOne
    @JoinColumn(name = "id")
    private User manager;


}
