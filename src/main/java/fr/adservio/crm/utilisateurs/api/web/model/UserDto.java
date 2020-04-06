package fr.adservio.crm.utilisateurs.api.web.model;

import fr.adservio.crm.utilisateurs.api.domain.Role;
import fr.adservio.crm.utilisateurs.api.domain.Status;
import fr.adservio.crm.utilisateurs.api.domain.User;

public class UserDto {

    private String email;

    private String firstName;

    private String secondName;

    private String lastName;

    private String userName;

    private String suffixe;

    private String mobile;

    private Status status;

    private Role role;

    private boolean enabled;

    private String street;

    private String city;

    private String province;

    private Long postalCode;

    private String country;

    private String company;

    private String service;

    private String division;

    private String matricule;

    private Long buisnessUnitCode;

    private User manager;
}
