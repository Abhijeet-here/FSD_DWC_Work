// 1. Abstract Base Class
abstract class SpaceVessel {
    protected short shipId;
    protected boolean isMining;
    protected char classification;

    public SpaceVessel(short shipId, boolean isMining, char classification) {
        this.shipId = shipId;
        this.isMining = isMining;
        this.classification = classification;
    }
}

// 2. Derived Class
class MiningShip extends SpaceVessel {
    // 2D Array: Rows = Bays, Columns = Containers
    private float[][] cargoHold;
    private byte numberOfBays;

    public MiningShip(short shipId, boolean isMining, char classification, byte numberOfBays, int containersPerBay) {
        super(shipId, isMining, classification);
        this.numberOfBays = numberOfBays;
        // Initializing the grid of cargo holds
        this.cargoHold = new float[this.numberOfBays][containersPerBay];
    }

    // Helper method to simulate loading ore into the grid
    public void loadContainer(byte bayIndex, int containerIndex, float weight) {
        if (bayIndex >= 0 && bayIndex < cargoHold.length && containerIndex >= 0 && containerIndex < cargoHold[bayIndex].length) {
            cargoHold[bayIndex][containerIndex] = weight;
        }
    }

    // 4. Required Method: Calculate Total Weight
    public float calculateTotalOreWeight() {
        float totalWeight = 0.0f;
        // Traverse the 2D array
        for (int bay = 0; bay < cargoHold.length; bay++) {
            for (int container = 0; container < cargoHold[bay].length; container++) {
                totalWeight += cargoHold[bay][container];
            }
        }
        return totalWeight;
    }

    // 4. Required Method: Find Heaviest Container
    public float findHeaviestContainer() {
        float maxWeight = 0.0f;
        // Traverse the 2D array
        for (int bay = 0; bay < cargoHold.length; bay++) {
            for (int container = 0; container < cargoHold[bay].length; container++) {
                if (cargoHold[bay][container] > maxWeight) {
                    maxWeight = cargoHold[bay][container];
                }
            }
        }
        return maxWeight;
    }
}

// 3. Main System / Fleet Manager
public class OrionFleetManager {
    public static void main(String[] args) {
        // Maintain the entire fleet using a 1D Array of Objects
        SpaceVessel[] fleet = new SpaceVessel[30000];
        
        // Tracking massive currency sums requires a long
        long totalFleetValue = 0L; 

        // --- Simulated Test Run ---
        
        // Instantiate a ship with 100 bays and 10 containers per bay
        MiningShip alphaShip = new MiningShip((short) 1042, true, 'A', (byte) 100, 10);
        
        // Load some ore containers into Bay 0
        alphaShip.loadContainer((byte) 0, 0, 45000.5f);
        alphaShip.loadContainer((byte) 0, 1, 48950.2f);
        alphaShip.loadContainer((byte) 0, 2, 12000.0f);
        
        // Add ship to the fleet array
        fleet[0] = alphaShip;
        
        // Run calculations
        System.out.println("Ship ID " + alphaShip.shipId + " Diagnostics:");
        System.out.println("Total Ore Weight: " + alphaShip.calculateTotalOreWeight() + " kg");
        System.out.println("Heaviest Container: " + alphaShip.findHeaviestContainer() + " kg");
    }
}