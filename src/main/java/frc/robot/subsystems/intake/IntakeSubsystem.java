package frc.robot.subsystems.intake;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {

  private SparkMax intakeMotor = new SparkMax(Constants.Intake.INTAKE_ID, MotorType.kBrushless);
  private TalonFX pivotMotor = new TalonFX(60);

  public IntakeSubsystem() {}

  public void powerIntake() {
    intakeMotor.set(1);
  }

  public void intakePivot() {}

  public void testSubystem() {
    pivotMotor.setPosition(1);
  }
}
