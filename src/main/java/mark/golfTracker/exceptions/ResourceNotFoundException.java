package mark.golfTracker.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {super("BEEP BOOP BLEEP BLORP ERROR...ERROR " + message);}
}
