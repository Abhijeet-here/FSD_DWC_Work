/**
 * Checked Exception: Represents a fatal or lock-level hardware issue.
 * The compiler will force the calling method to catch this or declare it.
 */
class HardwareLockException extends Exception {
    public HardwareLockException(String message) {
        super("CRITICAL HARDWARE LOCK: " + message);
    }
}

/**
 * Unchecked Exception: Represents impossible physical values.
 * This extends RuntimeException, so method signatures don't require a throws clause.
 */
class SensorCorruptionException extends RuntimeException {
    public SensorCorruptionException(String message) {
        super("SENSOR GLITCH: " + message);
    }
}