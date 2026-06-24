abstract class DatabaseConfig {
    public DatabaseConfig() {
        // DANGER: Calling an overridden method from a constructor
        setup(); 
    }
    public abstract void setup();
}

class SecureConfig extends DatabaseConfig {
    private String authToken;
    
    public SecureConfig() {
        super(); // Implicitly called first
        this.authToken = "Bearer123"; // Initialized AFTER super() finishes
    }
    
    @Override
    public void setup() {
        // Output: null. Heap allocation happened, but constructor hasn't assigned "Bearer123" yet.
        System.out.println("Auth Token: " + authToken); 
    }
}

public class StartupExecution {
    public static void main(String[] args) {
        new SecureConfig(); 
    }
}