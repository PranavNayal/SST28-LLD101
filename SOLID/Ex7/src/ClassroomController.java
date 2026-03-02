public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        InputConnector pj = reg.getFirstOfType(InputConnector.class);
        PowerControl pjPower = reg.getFirstOfType(Projector.class);
        pjPower.powerOn();
        pj.connectInput("HDMI-1");

        BrightnessControl lights = reg.getFirstOfType(BrightnessControl.class);
        lights.setBrightness(60);

        TemperatureControl ac = reg.getFirstOfType(TemperatureControl.class);
        ac.setTemperatureC(24);

        AttendanceCapable scan = reg.getFirstOfType(AttendanceCapable.class);
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        PowerControl pj = reg.getFirstOfType(Projector.class);
        PowerControl lights = reg.getFirstOfType(LightsPanel.class);
        PowerControl ac = reg.getFirstOfType(AirConditioner.class);
        pj.powerOff();
        lights.powerOff();
        ac.powerOff();
    }
}
