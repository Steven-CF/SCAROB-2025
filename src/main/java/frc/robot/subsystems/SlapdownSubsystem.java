package frc.robot.subsystems;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import au.grapplerobotics.LaserCan;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SlapdownSubsystem extends SubsystemBase {

    private final SparkFlex slapdownAngleMotor = new SparkFlex(Constants.Slapdown.SlAPDOWN_ANGLE_ID, MotorType.kBrushless);
    private final SparkFlex slapdownRoller1Motor = new SparkFlex(Constants.Slapdown.SLAPDOWN_ROLLER_1_ID, MotorType.kBrushless);
    private final SparkFlex slapdownRoller2Motor = new SparkFlex(Constants.Slapdown.SLAPDOWN_ROLLER_2_ID, MotorType.kBrushless);
    private final LaserCan slapdownSensor = new LaserCan(Constants.Slapdown.SLAPDOWN_SENSOR_ID);
    
    

    public SlapdownSubsystem() {
    }
    
    public void startRollers() {
        slapdownRoller1Motor.set(1);
        slapdownRoller2Motor.set(-1);
    }

    public void stopRollers() {
        slapdownRoller1Motor.stopMotor();
        slapdownRoller2Motor.stopMotor();
    }

    public void angleDown() {
        slapdownAngleMotor.set(1);
    }

    public void angleUp() {
        slapdownAngleMotor.set(-1);
    }

    public void stopAngle() {
        slapdownAngleMotor.stopMotor();
    }

    public boolean detectAlgae() {
        if (slapdownSensor.getMeasurement() != null) {
            return true;
        }
        return false;
    }

    public boolean reachedAngle(int rotations) {
        if (slapdownAngleMotor.getEncoder().getPosition() == rotations) {
            return true;
        }
        return false;
    }

}
