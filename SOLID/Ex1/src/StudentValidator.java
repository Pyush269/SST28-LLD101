import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// Single responsibility: validate a StudentData and return a list of error messages.
public class StudentValidator {
    private static final Set<String> ALLOWED_PROGRAMS = Set.of("CSE", "AI", "SWE");

    public List<String> validate(StudentData d) {
        List<String> errors = new ArrayList<>();
        if (d.name.isBlank()) errors.add("name is required");
        if (d.email.isBlank() || !d.email.contains("@")) errors.add("email is invalid");
        if (d.phone.isBlank() || !d.phone.chars().allMatch(Character::isDigit)) errors.add("phone is invalid");
        if (!ALLOWED_PROGRAMS.contains(d.program)) errors.add("program is invalid");
        return errors;
    }
}
