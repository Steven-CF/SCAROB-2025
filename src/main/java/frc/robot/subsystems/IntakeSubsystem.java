package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.reduxrobotics.sensors.canandmag.Canandmag;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
    
    private SparkMax intakeMotor = new SparkMax(Constants.Intake.INTAKE_ID, MotorType.kBrushless);
    private TalonFX pivotMotor = new TalonFX(31);
    private Canandmag intakeencoder = new Canandmag(15);

    public IntakeSubsystem() {

    }

    public void powerIntake() {
        intakeMotor.set(1);
    }

    public void intakePivot(double targetPosition) {
        // Get the current position of the pivot motor
        double position = intakeencoder.getAbsPosition();

        // Calculate the error between the target position and the current position
        double error = targetPosition - position; 

        // Simple proportional control to adjust the intake motor based on the error
        double kP = 0.1; // Proportional gain, you may need to tune this value
        double output = kP * error;

        // Set the intake motor speed based on the control output
        ___.set(output);

        double velocity = intakeencoder.getVelocity(); // Get the velocity of the pivot motor

        intakeencoder.setAbsPosition(0); // Reset the encoder position to 0
        intakeencoder.setAbsPosition(90); // Set the encoder position to 90
    }
    
}
