package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SlapdownSubsystem;

public class SlapdownIntake extends Command {

    private final SlapdownSubsystem slapdown;

    public SlapdownIntake(SlapdownSubsystem slapdown) {
        this.slapdown = slapdown;
        addRequirements(slapdown);
    }

    @Override
    public void initialize() {

    }

    @Override 
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }
    
}
