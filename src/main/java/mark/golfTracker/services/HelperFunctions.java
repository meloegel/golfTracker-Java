package mark.golfTracker.services;

import mark.golfTracker.models.ValidationError;
import java.util.List;


public interface HelperFunctions {
    List<ValidationError> getConstraintViolation(Throwable cause);

    boolean isAuthorizedToMakeChange(String username);
}
