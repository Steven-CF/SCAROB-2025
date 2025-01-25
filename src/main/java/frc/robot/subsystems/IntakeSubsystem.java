package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
    
    private SparkMax intakeMotor = new SparkMax(Constants.Intake.INTAKE_ID, MotorType.kBrushless);
    private TalonFX pivotMoter = new TalonFX(31);

    public IntakeSubsystem() {

    }

    public void powerIntake() {
        intakeMotor.set(1);
    }

    public void intakePivot() {
        boolean X;
        boolean A;
        if (X) {
            pivotMoter.set(1);
        }else if(A) {
            pivotMoter.set(1);
        }
    }
    
}
