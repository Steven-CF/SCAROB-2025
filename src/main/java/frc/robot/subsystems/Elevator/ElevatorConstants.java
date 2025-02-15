package frc.robot.subsystems.Elevator;

import com.ctre.phoenix6.signals.InvertedValue;

public final class ElevatorConstants {
    public final static int elevatorCanId1 = 41;
    public final static int elevatorCanId2 = 42;
    public final static double gearRatioModifier = (12/20);
     //Outputs
    public final static double idleOutput = 0;

    //Motor Direction
    public final static InvertedValue elevatorMotor1Direction = InvertedValue.CounterClockwise_Positive;
    public final static InvertedValue elevatorMotor2Direction = InvertedValue.CounterClockwise_Positive;

    // Positions
    public final static double elevatorHomePosition = 0;
    public final static double minElevatorPosition = 0;
    public final static double maxElevatorPosition = 97 * gearRatioModifier;

}