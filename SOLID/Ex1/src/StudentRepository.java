import java.util.List;

// Abstraction for student persistence. OnboardingService depends on this, not on FakeDb directly.
public interface StudentRepository {
    void save(StudentRecord r);
    int count();
    List<StudentRecord> all();
}
