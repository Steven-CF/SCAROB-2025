package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SensorSubsytem;
import frc.robot.subsystems.SlapdownSubsystem;

public class SlapdownIntake extends Command {

    private final SlapdownSubsystem slapdown;
    private final SensorSubsytem sensor;

    private boolean hasAlgae = false;

    public SlapdownIntake(SlapdownSubsystem slapdown, SensorSubsytem sensor) {
        this.slapdown = slapdown;
        this.sensor = sensor;
        addRequirements(slapdown);
        addRequirements(sensor);
    }

    @Override
    public void initialize() {
        slapdown.angleDown();
    }

    @Override 
    public void execute() {

        if (slapdown.reachedAngle(24)) {
            slapdown.stopAngle();
            slapdown.startRollers();
        }

        if (slapdown.detectAlgae() == true || sensor.commandStop == true) {
            slapdown.stopRollers();
            slapdown.angleUp();
            hasAlgae = true;
        }

    }

    @Override
    public boolean isFinished() {
        if (hasAlgae == true && slapdown.reachedAngle(0)) {
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        slapdown.stopAngle();
        sensor.commandStop = false;
        hasAlgae = false;
    }
    
}
