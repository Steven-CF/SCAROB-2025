package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.reduxrobotics.sensors.canandmag.Canandmag;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkMax;

import au.grapplerobotics.LaserCan;

import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.CoralManipulator;

public class CoralManipulatorSubsystem extends SubsystemBase {
    
    private final SparkFlex coralManipulator1 = new SparkFlex(Constants.CoralManipulator.CORAL_MANIPULATOR_1_ID, MotorType.kBrushless);
    private final SparkFlex coralManipulator2 = new SparkFlex(Constants.CoralManipulator.CORAL_MANIPULATOR_2_ID, MotorType.kBrushless);
    private final LaserCan coralSensor = new LaserCan(Constants.CoralManipulator.CORAL_SENSOR);
    

    public CoralManipulatorSubsystem() {
        
    }

    public void powerIntakeShooter() {
        coralManipulator1.set(1);
        coralManipulator2.set(-1);
    }

    public void stopIntakeShooter() {
        coralManipulator1.stopMotor();
        coralManipulator2.stopMotor();
    }

    public boolean hasNote() {
        if (coralSensor.getMeasurement() != null) {
            return true;
        }
        return false;
    }
    
}
