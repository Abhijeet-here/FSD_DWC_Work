import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DroneHive {
    
    // REQUIREMENT 1 & 2: Lock-free thread-safe counter.
    // AtomicInteger uses hardware-level Compare-And-Swap (CAS) operations.
    // This prevents the 10,000 threads from corrupting the count without requiring the slow 'synchronized' keyword.
    private AtomicInteger totalDronesReturned = new AtomicInteger(0);

    // REQUIREMENT 3: Instant memory visibility.
    // 'volatile' ensures that threads do not cache this variable in their local CPU cache.
    // Every read/write goes directly to the main memory (RAM), ensuring immediate global awareness.
    private volatile boolean emergencyAbort = false;

    /**
     * Called by each drone thread when attempting to land.
     */
    public void droneLanded(int droneId) {
        // 1. Instantly check the global state (bypasses local CPU cache)
        if (emergencyAbort) {
            // Speed-to-market note: Routing logic would go here. 
            // System.out.println("Drone " + droneId + " aborting landing sequence! Re-routing...");
            return; 
        }

        // 2. Safe increment without locking the entire method
        totalDronesReturned.incrementAndGet();
    }

    /**
     * Called by the Central Radar system.
     */
    public void triggerStorm() {
        // This write is instantly visible to all 10,000 airborne drones
        emergencyAbort = true;
        System.out.println("CRITICAL: Storm detected! Emergency abort broadcasted to all drones.");
    }

    // --- System Status Getters ---
    public int getTotalDrones() {
        return totalDronesReturned.get();
    }

    public boolean isEmergency() {
        return emergencyAbort;
    }

    // --- Simulation / Testing ---
    public static void main(String[] args) throws InterruptedException {
        DroneHive centralHive = new DroneHive();
        int swarmSize = 10000;

        // Simulate massive concurrency with a Thread Pool
        ExecutorService droneSwarm = Executors.newFixedThreadPool(200);

        for (int i = 0; i < swarmSize; i++) {
            final int droneId = i;
            droneSwarm.submit(() -> {
                // Simulate the radar detecting a storm halfway through the returns
                if (droneId == 5000) {
                    centralHive.triggerStorm();
                }
                centralHive.droneLanded(droneId);
            });
        }

        droneSwarm.shutdown();
        droneSwarm.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("--- System Diagnostics ---");
        System.out.println("Emergency Abort Triggered: " + centralHive.isEmergency());
        System.out.println("Total Drones Successfully Landed (Expected < 10000): " + centralHive.getTotalDrones());
    }
}