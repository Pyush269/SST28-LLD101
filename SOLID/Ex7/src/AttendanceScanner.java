/**
 * AttendanceScanner: only scans attendance.
 * ISP: does NOT implement Powerable, Dimmable, TemperatureControllable, or InputConnectable.
 */
public class AttendanceScanner implements AttendanceScannable {
    @Override public int scanAttendance() { return 3; }
}
