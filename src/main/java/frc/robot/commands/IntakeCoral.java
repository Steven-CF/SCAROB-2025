package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralManipulatorSubsystem;
import frc.robot.subsystems.SensorSubsytem;

public class IntakeCoral extends Command {

    private final CoralManipulatorSubsystem coralManipulator;
    private final SensorSubsytem sensor;

    private boolean hasCoral = false;

    public IntakeCoral(CoralManipulatorSubsystem coralManipulator, SensorSubsytem sensor) {
        this.coralManipulator = coralManipulator;
        this.sensor = sensor;
        addRequirements(coralManipulator);
        addRequirements(sensor);
    } 
    
    @Override
    public void initialize() {
        coralManipulator.intake();
    }

    @Override
    public void execute() {
        if (coralManipulator.coralDetected() == true || sensor.commandStop == true) {
            coralManipulator.resetEncoders();
            hasCoral = true;
        }
    }

    @Override 
    public boolean isFinished() {
        if (hasCoral == true && coralManipulator.reachedPosition() == true) {
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interupted) {
        coralManipulator.stopMotors();
        sensor.commandStop = false;
        hasCoral = false;
    }
}
