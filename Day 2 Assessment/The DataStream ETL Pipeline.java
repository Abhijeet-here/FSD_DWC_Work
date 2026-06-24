// 1. Domain Models
public abstract class DataPayload {
    public abstract String getRawContent();
}

public class JsonPayload extends DataPayload {
    @Override
    public String getRawContent() { return "{ \"status\": \"ok\" }"; }
}

public class XmlPayload extends DataPayload {
    @Override
    public String getRawContent() { return "<status>ok</status>"; }
}

// 2. Generic Processor (Upper Bounded)
// <T extends DataPayload> ensures only valid subclasses are accepted at compile-time.
public class PipelineProcessor<T extends DataPayload> {
    
    public void process(T payload) {
        // Safe to call getRawContent() because compiler guarantees T inherits from DataPayload
        String data = payload.getRawContent();
        System.out.println("ETL Processing: " + data);
    }
}