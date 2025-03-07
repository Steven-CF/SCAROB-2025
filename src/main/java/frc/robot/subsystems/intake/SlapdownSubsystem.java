package frc.robot.subsystems.intake;

import au.grapplerobotics.LaserCan;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SlapdownSubsystem extends SubsystemBase {

  private final SparkFlex slapdownAngleMotor =
      new SparkFlex(Constants.Slapdown.SLAPDOWN_ANGLE_ID, MotorType.kBrushless);
  private final SparkFlex slapdownRoller1Motor =
      new SparkFlex(Constants.Slapdown.SLAPDOWN_ROLLER_1_ID, MotorType.kBrushless);
  private final SparkFlex slapdownRoller2Motor =
      new SparkFlex(Constants.Slapdown.SLAPDOWN_ROLLER_2_ID, MotorType.kBrushless);
  private final LaserCan slapdownSensor = new LaserCan(Constants.Slapdown.SLAPDOWN_SENSOR_ID);
  // Initialize the closed loop controller
  private final SparkClosedLoopController slapdown_controller =
      slapdownAngleMotor.getClosedLoopController();

  public SlapdownSubsystem() {}

  public void intakeRollers() {
    slapdownRoller1Motor.set(0.5);
    slapdownRoller2Motor.set(-0.5);
  }

  public void outtakeRollers() {
    slapdownRoller1Motor.set(-0.5);
    slapdownRoller2Motor.set(0.5);
  }

  public void stopRollers() {
    slapdownRoller1Motor.stopMotor();
    slapdownRoller2Motor.stopMotor();
  }

  public void angleIntake(double rotation) {
    slapdown_controller.setReference(rotation, ControlType.kPosition);
  }
  // public void angleDown() {
  //   slapdownAngleMotor.set(0.1);
  // }

  // public void angleUp() {
  //   slapdownAngleMotor.set(-0.1);
  // }

  // public void stopAngle() {
  //   slapdownAngleMotor.stopMotor();
  // }

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
