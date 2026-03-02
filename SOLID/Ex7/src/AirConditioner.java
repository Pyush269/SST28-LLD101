/**
 * AirConditioner: supports power control and temperature control.
 * ISP: does NOT implement Dimmable, AttendanceScannable, or InputConnectable.
 */
public class AirConditioner implements Powerable, TemperatureControllable {
    @Override public void powerOn()  { /* start up */ }
    @Override public void powerOff() { System.out.println("AC OFF"); }

    @Override public void setTemperatureC(int c) {
        System.out.println("AC set to " + c + "C");
    }
}
