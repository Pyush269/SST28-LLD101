/**
 * DIP abstraction: booking service depends on this, not on DriverAllocator directly.
 */
public interface DriverAllocate {
    String allocate(String studentId);
}
