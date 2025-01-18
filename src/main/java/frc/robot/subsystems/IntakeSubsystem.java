package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
    
    private SparkMax intakeMotor = new SparkMax(Constants.Intake.INTAKE_ID, MotorType.kBrushless);

    public IntakeSubsystem() {

    }

    public void powerIntake() {
        intakeMotor.set(1);
    }
}
