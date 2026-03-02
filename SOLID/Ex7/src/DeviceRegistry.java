import java.util.ArrayList;
import java.util.List;

/**
 * Registry stores devices as Object so it is not tied to the fat SmartClassroomDevice interface.
 * Callers retrieve a device by class name and cast to the specific capability interface they need.
 */
public class DeviceRegistry {
    private final List<Object> devices = new ArrayList<>();

    public void add(Object d) { devices.add(d); }

    public Object getFirstOfType(String simpleName) {
        for (Object d : devices) {
            if (d.getClass().getSimpleName().equals(simpleName)) return d;
        }
        throw new IllegalStateException("Missing: " + simpleName);
    }
}
