// 1. Base Structure
public abstract class DeliveryDrone {
    protected String droneId;
    public DeliveryDrone(String droneId) { this.droneId = droneId; }
    public abstract void deliverPackage();
}

// 2. Segregated Interfaces
public interface Airborne {
    void flyToDestination();
    
    // Default method ensures backward compatibility
    default void requestAirTrafficClearance() {
        System.out.println("Aviation Authority: Clearance granted for airspace entry.");
    }
}

public interface GroundBased {
    void navigateSidewalks();
}

// 3. Concrete Implementations
public class Quadcopter extends DeliveryDrone implements Airborne {
    public Quadcopter(String id) { super(id); }
    
    @Override
    public void deliverPackage() { System.out.println("Dropping package from air."); }
    @Override
    public void flyToDestination() { System.out.println("Flying via waypoints."); }
}

public class CityRover extends DeliveryDrone implements GroundBased {
    public CityRover(String id) { super(id); }
    
    @Override
    public void deliverPackage() { System.out.println("Dispensing package on ground."); }
    @Override
    public void navigateSidewalks() { System.out.println("Rolling on pedestrian path."); }
}

// Hybrid represents multiple inheritance of behavior
public class HybridVTOL extends DeliveryDrone implements Airborne, GroundBased {
    public HybridVTOL(String id) { super(id); }
    
    @Override
    public void deliverPackage() { System.out.println("Landing to dispense package."); }
    @Override
    public void flyToDestination() { System.out.println("Transitioning to forward flight."); }
    @Override
    public void navigateSidewalks() { System.out.println("Engaging wheel drive."); }
}