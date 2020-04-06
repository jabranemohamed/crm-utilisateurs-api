package fr.adservio.crm.utilisateurs.api.domain;

public enum Status {

    /**
     * Active User
     */
    ACTIVE("active"),

    /**
     * INACTIVE USER
     */
    INACTIVE("inactive");


    private final String libelle;

    Status(final String libelle) {
        this.libelle = libelle;
    }

    public String getName() {
        return libelle;
    }
}
