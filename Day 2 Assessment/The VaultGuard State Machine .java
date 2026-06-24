// 1. State Representation
public enum DoorState { OPEN, CLOSED, LOCKED }

// 2. Custom Unchecked Exception
public class IllegalStateTransitionException extends RuntimeException {
    public IllegalStateTransitionException(String message) {
        super(message);
    }
}

// 3. Encapsulated Context
public class VaultDoor {
    // Strictly private state. No getters/setters exposing the mutable reference.
    private DoorState state;

    public VaultDoor() {
        this.state = DoorState.OPEN; // Default physical state
    }

    public void closeDoor() {
        if (state == DoorState.LOCKED) {
            throw new IllegalStateTransitionException("Cannot close a locked door.");
        }
        this.state = DoorState.CLOSED;
        System.out.println("Door is now CLOSED.");
    }

    public void lockDoor() {
        if (state == DoorState.OPEN) {
            // Fail-Fast: Reject invalid domain operations immediately
            throw new IllegalStateTransitionException("SECURITY BREACH ATTEMPT: Cannot lock an OPEN door. Close it first.");
        }
        this.state = DoorState.LOCKED;
        System.out.println("Door is now LOCKED.");
    }

    public void unlockDoor() {
        if (state == DoorState.OPEN) {
            throw new IllegalStateTransitionException("Door is already open.");
        }
        this.state = DoorState.CLOSED; // Unlocking does not open it physically
        System.out.println("Door is now UNLOCKED (but still closed).");
    }
}