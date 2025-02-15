package frc.robot.subsystems.arm;

import com.ctre.phoenix6.signals.InvertedValue;

public final class ArmConstants {
    //Arm Can ID
 //   public final static int armCanId = 72;

    // Motor Direction
    public final static InvertedValue armMotorDirection = InvertedValue.Clockwise_Positive;

    //Outputs
    public final static double idleOutput = 0;

    // Positions
    public final static double armHomePosition = 0;
    public final static double minArmPosition = 0;
    public final static double maxArmPosition = 20;
}
