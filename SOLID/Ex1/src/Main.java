public class Main {
    public static void main(String[] args) {
        System.out.println("=== Student Onboarding ===");
        FakeDb db = new FakeDb();
        OnboardingService svc = new OnboardingService(
            new InputParser(),
            new StudentValidator(),
            db,
            new OnboardingPrinter()
        );

        // Successful registration
        String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
        svc.registerFromRawInput(raw);

        System.out.println();

        // Stretch goal: failing validation (bad email + invalid program)
        String bad = "name=;email=invalidemail;phone=abc123;program=XYZ";
        svc.registerFromRawInput(bad);

        System.out.println();
        System.out.println("-- DB DUMP --");
        System.out.print(TextTable.render3(db));
    }
}

