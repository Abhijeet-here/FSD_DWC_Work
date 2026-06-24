abstract class Vehicle {
    public void drive() { System.out.println("Driving..."); }
}

class GasCar extends Vehicle {}

class ElectricCar extends Vehicle {
    public void updateFirmware() { System.out.println("Flashing ECU..."); }
}

public class BatchWorker {
    public static void main(String[] args) {
        Vehicle[] fleet = { new GasCar(), new ElectricCar() };
        
        for (Vehicle v : fleet) {
            v.drive(); // Always safe, verified at compile-time
            
            // JVM Heap memory inspection before downcasting
            if (v instanceof ElectricCar) {
                // Safe cast: repointing Stack reference to specific Heap object
                ElectricCar ev = (ElectricCar) v;
                ev.updateFirmware();
            }
        }
    }
}