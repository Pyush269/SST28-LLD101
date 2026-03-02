/**
 * ClassroomController depends only on specific capability interfaces
 * (ISP-compliant).
 * It no longer depends on the fat SmartClassroomDevice interface.
 * Each retrieval casts to the exact capability actually needed.
 */
public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) {
        this.reg = reg;
    }

    public void startClass() {
        // Projector: needs power on + input connection
        Powerable pj = (Powerable) reg.getFirstOfType("Projector");
        pj.powerOn();
        InputConnectable pjInput = (InputConnectable) reg.getFirstOfType("Projector");
        pjInput.connectInput("HDMI-1");

        // Lights: needs brightness control only
        Dimmable lights = (Dimmable) reg.getFirstOfType("LightsPanel");
        lights.setBrightness(60);

        // AC: needs temperature control only
        TemperatureControllable ac = (TemperatureControllable) reg.getFirstOfType("AirConditioner");
        ac.setTemperatureC(24);

        // Scanner: needs attendance scanning only
        AttendanceScannable scan = (AttendanceScannable) reg.getFirstOfType("AttendanceScanner");
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        ((Powerable) reg.getFirstOfType("Projector")).powerOff();
        ((Powerable) reg.getFirstOfType("LightsPanel")).powerOff();
        ((Powerable) reg.getFirstOfType("AirConditioner")).powerOff();
    }
}
