// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.state.sequencer.*;
import frc.robot.state.sequencer.positions.PositionConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Elevator.ElevatorConstants;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.commands.ResetSequenceCommand;
import frc.robot.commands.RunSequenceCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.intake.IntakeSubsystem;
import frc.robot.commands.RunSequenceCommand;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  private final CommandXboxController xboxDriverController = new CommandXboxController(0);
  private final CommandXboxController xboxOperatorController = new CommandXboxController(1);

  /* Driver Buttons */
  private final Trigger dStart = xboxDriverController.start();
  private final Trigger dBack = xboxDriverController.back();
  private final Trigger dY = xboxDriverController.y();
  private final Trigger dB = xboxDriverController.b();
  private final Trigger dA = xboxDriverController.a();
  private final Trigger dX = xboxDriverController.x();
  private final Trigger dLeftBumper = xboxDriverController.leftBumper();
  private final Trigger dRightBumper = xboxDriverController.rightBumper();
  private final Trigger dLeftTrigger = xboxDriverController.leftTrigger();
  private final Trigger dRightTrigger = xboxDriverController.rightTrigger();
  private final Trigger dPOVDown = xboxDriverController.povDown(); 
  private final Trigger dPOVUp = xboxDriverController.povUp(); 
  private final Trigger dPOVLeft = xboxDriverController.povLeft();
  private final Trigger dPOVRight = xboxDriverController.povRight();

  /* Operator Buttons */
  private final Trigger opStart = xboxOperatorController.start();
  private final Trigger opBack = xboxOperatorController.back();
  private final Trigger opY = xboxOperatorController.y();
  private final Trigger opB = xboxOperatorController.b();
  private final Trigger opA = xboxOperatorController.a();
  private final Trigger opX = xboxOperatorController.x();
  private final Trigger opLeftBumper = xboxOperatorController.leftBumper();
  private final Trigger opRightBumper = xboxOperatorController.rightBumper();
  private final Trigger opLeftTrigger = xboxOperatorController.leftTrigger();
  private final Trigger opRightTrigger = xboxOperatorController.rightTrigger();
  private final Trigger opPOVDown = xboxOperatorController.povDown();
  private final Trigger opPOVUp = xboxOperatorController.povUp();
  private final Trigger opPOVLeft = xboxOperatorController.povLeft();
  private final Trigger opPOVRight = xboxOperatorController.povRight();
  
  //Subsystems are declared here 
  private ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem(true);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    // dRightTrigger.onTrue(new SequentialCommandGroup(
    //   new InstantCommand(() -> SequenceManager.setActionSelection(Action.SCORE)),
    //   new RunSequenceCommand(elevatorSubsystem)));
    // Climb up
    //dLeftBumper.whileTrue(new InstantCommand(() -> climbSubsystem.moveClimb(0)))
      //.whileFalse(new InstantCommand(() -> climbSubsystem.stopClimb()));

    // Climb down
    //dRightBumper.whileTrue(new InstantCommand(() -> climbSubsystem.moveClimb(0)))
    //.whileFalse(new InstantCommand(() -> climbSubsystem.stopClimb()));

    // Controls level selection
    dY.whileTrue(new InstantCommand(() -> elevatorSubsystem.moveElevator(36)))
      .onFalse(new InstantCommand(() -> elevatorSubsystem.moveElevator(0.1))); //if not pressed set defualt to Level 2  on
    dB.whileTrue(new InstantCommand(() -> elevatorSubsystem.moveElevator(17))); //if not pressed set defualt to Level 2 on
    dA.whileTrue(new InstantCommand(() -> elevatorSubsystem.moveElevator(9))); //while pressed set to Level 3on
    dX.whileTrue(new InstantCommand(() -> elevatorSubsystem.moveElevator(4))); //while pressed set to Level 1

  //   dLeftBumper.whileTrue(new SequentialCommandGroup(
  //     new InstantCommand(() -> SequenceManager.setActionSelection(Action.INTAKE)),
  //     new ResetSequenceCommand(elevatorSubsystem),
  //     new RunSequenceCommand(elevatorSubsystem)));
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  //public Command getAutonomousCommand() {
    // An example command will be run in autonomous
  //}
}