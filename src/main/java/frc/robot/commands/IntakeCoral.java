package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralManipulatorSubsystem;

public class IntakeCoral extends Command {

    private final CoralManipulatorSubsystem coralManipulator;

    private boolean hasCoral = false;

    public IntakeCoral(CoralManipulatorSubsystem coralManipulator) {
        this.coralManipulator = coralManipulator;
        addRequirements(coralManipulator);
    } 
    
    @Override
    public void initialize() {
        coralManipulator.intake();
    }

    @Override
    public void execute() {
        if (coralManipulator.coralDetected() == true) {
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
        hasCoral = false;
    }
}
