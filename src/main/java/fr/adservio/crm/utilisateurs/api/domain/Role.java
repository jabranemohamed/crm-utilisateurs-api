package fr.adservio.crm.utilisateurs.api.domain;

public enum Role {

    RH("rh"),

    MANAGER("manager"),

    CEO("ceo"),

    CONSULTANT("consultant"),

    COMMERCIAL("commercial");

    private final String libelle;

    Role(final String libelle) {
        this.libelle = libelle;
    }

    public String getName() {
        return libelle;
    }
}
