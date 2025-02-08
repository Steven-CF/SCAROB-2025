package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.reduxrobotics.sensors.canandmag.Canandmag;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
    
    private SparkMax intakeMotor = new SparkMax(Constants.Intake.INTAKE_ID, MotorType.kBrushless);
    private TalonFX pivotMotor = new TalonFX(31);

    public IntakeSubsystem() {
        
    }

    public void powerIntake() {
        intakeMotor.set(1);
    }

    public void intakePivot() {
        
    }

    public void testSubystem() {
        pivotMotor.setPosition(1);
    }
    
}
