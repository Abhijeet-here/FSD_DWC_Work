/**
 * Dummy resource that simulates reading deep-sea telemetry.
 */
class TelemetryStream implements AutoCloseable {
    
    public TelemetryStream() {
        System.out.println("[SYSTEM] Opening connection to local telemetry file...");
    }

    // Method declares the checked exception, but not the unchecked one
    public void readData(int simulatedWaterTemp, boolean isOsLocked) throws HardwareLockException {
        if (isOsLocked) {
            throw new HardwareLockException("OS has locked the telemetry file. Cannot read.");
        }
        
        if (simulatedWaterTemp > 100) {
            // Unchecked exception thrown without being in the method signature
            throw new SensorCorruptionException("Water temp reported at " + simulatedWaterTemp + "°C. Impossible value.");
        }
        
        System.out.println("[SYSTEM] Telemetry data parsed successfully. Temp: " + simulatedWaterTemp + "°C");
    }

    @Override
    public void close() {
        // This acts as our guaranteed cleanup mechanism
        System.out.println("[SYSTEM] TelemetryStream closed safely. Hardware resources released.\n");
    }
}