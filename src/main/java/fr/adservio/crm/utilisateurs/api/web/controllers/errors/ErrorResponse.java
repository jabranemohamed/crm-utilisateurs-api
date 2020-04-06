package fr.adservio.crm.utilisateurs.api.web.controllers.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    //General error message about nature of error
    private String errorMessage;

    private String location;

    private LocationType locationType;

    //permissions,validationError
    private String reasonCode;

}
