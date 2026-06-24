interface AuditTracker {
    default void log() { System.out.println("Audit Log"); }
}

interface PerformanceTracker {
    default void log() { System.out.println("Perf Log"); }
}

class MasterService implements AuditTracker, PerformanceTracker {
    // Must override to resolve compiler error (Diamond Problem)
    @Override
    public void log() {
        // Explicitly routing Late Binding to a specific interface implementation
        AuditTracker.super.log(); 
        PerformanceTracker.super.log();
        System.out.println("Master Service Log");
    }
}