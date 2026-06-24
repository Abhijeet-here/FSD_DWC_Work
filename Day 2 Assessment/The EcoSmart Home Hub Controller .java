// 1. Base Abstract Class
public abstract class SmartDevice {
    protected String deviceId;
    protected String deviceName;

    public SmartDevice(String deviceId, String deviceName) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
    }

    public abstract void runDiagnostic();
}

// 2. Capability Interface
public interface BatteryOperated {
    int getBatteryLevel();
    void triggerRechargeAlert();
}

// 3. Concrete Implementations
public class SmartLight extends SmartDevice {
    public SmartLight(String id, String name) { super(id, name); }
    
    @Override
    public void runDiagnostic() {
        System.out.println(deviceName + " (Light): Testing bulb filament... OK.");
    }
}

public class SmartCamera extends SmartDevice implements BatteryOperated {
    private int batteryLevel;

    public SmartCamera(String id, String name, int batteryLevel) {
        super(id, name);
        this.batteryLevel = batteryLevel;
    }

    @Override
    public void runDiagnostic() {
        System.out.println(deviceName + " (Camera): Testing lens motor... OK.");
    }

    @Override
    public int getBatteryLevel() { return batteryLevel; }

    @Override
    public void triggerRechargeAlert() {
        System.out.println("ALERT: " + deviceName + " battery low (" + batteryLevel + "%).");
    }
}

public class SmartLock extends SmartDevice implements BatteryOperated {
    private int batteryLevel;

    public SmartLock(String id, String name, int batteryLevel) {
        super(id, name);
        this.batteryLevel = batteryLevel;
    }

    @Override
    public void runDiagnostic() {
        System.out.println(deviceName + " (Lock): Testing deadbolt actuator... OK.");
    }

    @Override
    public int getBatteryLevel() { return batteryLevel; }

    @Override
    public void triggerRechargeAlert() {
        System.out.println("CRITICAL ALERT: " + deviceName + " lock battery low (" + batteryLevel + "%).");
    }
}

// 4. The Hub Manager (Polymorphism & Safe Downcasting)
public class HomeHub {
    public void executeNightlyRoutine(SmartDevice[] devices) {
        for (SmartDevice device : devices) {
            // Polymorphic dispatch - Late Binding
            device.runDiagnostic();
            
            // Safe Downcasting utilizing Java 16+ Pattern Matching
            if (device instanceof BatteryOperated batteryDevice) {
                if (batteryDevice.getBatteryLevel() < 20) {
                    batteryDevice.triggerRechargeAlert();
                }
            }
        }
    }
}