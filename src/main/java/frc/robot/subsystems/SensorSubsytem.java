package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SensorSubsytem extends SubsystemBase{
    
    public boolean commandStop = false;

    public SensorSubsytem() {
    }

    public void stopSensorBasedCommads() {
        commandStop = true;
    }

}
