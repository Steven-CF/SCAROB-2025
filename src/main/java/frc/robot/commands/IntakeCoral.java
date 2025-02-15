package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CoralManipulatorSubsystem;

public class IntakeCoral extends Command {

    private final CoralManipulatorSubsystem coralManipulator;

    public IntakeCoral(CoralManipulatorSubsystem coralManipulator) {
        this.coralManipulator = coralManipulator;
        addRequirements(coralManipulator);
    } 
    
    @Override
    public void initialize() {
        coralManipulator.powerIntakeShooter();
    }

    @Override
    public void execute() {
        if (coralManipulator.hasNote() == true) {

        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interupted) {
        coralManipulator.stopIntakeShooter();
    }
}
