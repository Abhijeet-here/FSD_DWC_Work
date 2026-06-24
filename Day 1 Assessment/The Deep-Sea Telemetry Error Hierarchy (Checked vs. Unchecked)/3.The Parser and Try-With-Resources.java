public class SubmarineParser {

    public void processTelemetry(int tempValue, boolean lockState) {
        System.out.println("--- Initiating Parse Sequence ---");
        
        // Try-With-Resources block ensures stream is ALWAYS closed
        try (TelemetryStream stream = new TelemetryStream()) {
            
            stream.readData(tempValue, lockState);
            
        } catch (HardwareLockException e) {
            // Handling the Checked Exception (Fatal)
            System.err.println("ABORT PROCESS: " + e.getMessage());
            // In a real scenario, we might trigger a sub-system reboot here
            
        } catch (SensorCorruptionException e) {
            // Handling the Unchecked Exception (Recoverable/Loggable)
            System.err.println("LOG NON-FATAL ERROR: " + e.getMessage());
            // We just log this and let the process continue
            
        }
        // No finally block needed! stream.close() is called automatically right here.
    }

    public static void main(String[] args) {
        SubmarineParser sub = new SubmarineParser();

        // Scenario 1: Normal Operation
        sub.processTelemetry(4, false);

        // Scenario 2: Sensor Glitch (Unchecked Exception)
        sub.processTelemetry(500, false);

        // Scenario 3: Hardware Lock (Checked Exception)
        sub.processTelemetry(2, true);
    }
}