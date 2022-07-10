package mark.golfTracker.exceptions;

public class ResourceFoundException extends RuntimeException{
    public ResourceFoundException(String message) {super("BEEP BOOP BLEEP BLORP ERROR...ERROR " + message);}
}
