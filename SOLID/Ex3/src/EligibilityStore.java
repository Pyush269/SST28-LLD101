// OCP/DIP: abstraction for eligibility persistence. EligibilityEngine depends on this, not on FakeEligibilityStore.
public interface EligibilityStore {
    void save(String rollNo, String status);
}
