// Copyright 2021-2025 FRC 6328
// http://github.com/Mechanical-Advantage
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// version 3 as published by the Free Software Foundation or
// available in the root directory of this project.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.

package frc.robot;

import com.ctre.phoenix6.SignalLogger;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ScheduleCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.robot.commands.DriveCommands;
import frc.robot.commands.IntakeCoral;
import frc.robot.commands.SlapdownIntake;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.Elevator.ElevatorSubsystem;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.GyroIO;
import frc.robot.subsystems.drive.GyroIOPigeon2;
import frc.robot.subsystems.drive.ModuleIO;
import frc.robot.subsystems.drive.ModuleIOSim;
import frc.robot.subsystems.drive.ModuleIOTalonFX;
import frc.robot.subsystems.intake.CoralManipulatorSubsystem;
import frc.robot.subsystems.intake.SensorSubsytem;
import frc.robot.subsystems.intake.SlapdownSubsystem;
import org.littletonrobotics.junction.networktables.LoggedDashboardChooser;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Subsystems
  private final Drive drive;

  private final CommandXboxController xboxDriverController = new CommandXboxController(0);
  private final CommandXboxController xboxOperatorController = new CommandXboxController(1);
  // Dashboard inputs
  private final LoggedDashboardChooser<Command> autoChooser;

  private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem(true);

  private final CoralManipulatorSubsystem coralManipulatorSubsystem =
      new CoralManipulatorSubsystem();
  private final SlapdownSubsystem slapdownSubsystem = new SlapdownSubsystem();
  private final SensorSubsytem sensorSubsytem = new SensorSubsytem();

  private final IntakeCoral intakeCoralCommand =
      new IntakeCoral(coralManipulatorSubsystem, sensorSubsytem);
  private final SlapdownIntake slapdownIntake =
      new SlapdownIntake(slapdownSubsystem, sensorSubsytem);

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

  private double desiredCoralPosition = Constants.ScorePositions.ElevatorL2;
  private double desiredAlgaePosition = Constants.ScorePositions.SlapdownOut;
  // TODO: (Evens) there might be an error if the desired_possition changes before the elevator got there
    // Fix would be to have 2 more variables for the desired_possition and a last_set_possition which would be 
    // set to the last_set_possition = desired_possition at the start of the sequential command group and use
    // last_set_possition instead of desired_possition in the wait until command


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    switch (Constants.currentMode) {
      case REAL:
        // Real robot, instantiate hardware IO implementations
        drive =
            new Drive(
                new GyroIOPigeon2(),
                new ModuleIOTalonFX(TunerConstants.FrontLeft),
                new ModuleIOTalonFX(TunerConstants.FrontRight),
                new ModuleIOTalonFX(TunerConstants.BackLeft),
                new ModuleIOTalonFX(TunerConstants.BackRight));
        break;

      case SIM:
        // Sim robot, instantiate physics sim IO implementations
        drive =
            new Drive(
                new GyroIO() {},
                new ModuleIOSim(TunerConstants.FrontLeft),
                new ModuleIOSim(TunerConstants.FrontRight),
                new ModuleIOSim(TunerConstants.BackLeft),
                new ModuleIOSim(TunerConstants.BackRight));
        break;

      default:
        // Replayed robot, disable IO implementations
        drive =
            new Drive(
                new GyroIO() {},
                new ModuleIO() {},
                new ModuleIO() {},
                new ModuleIO() {},
                new ModuleIO() {});
        break;
    }

    registerNamedCommands();
    // Set up auto routines
    autoChooser = new LoggedDashboardChooser<>("Auto Choices", AutoBuilder.buildAutoChooser());

    // Set up SysId routines
    autoChooser.addOption(
        "Drive Wheel Radius Characterization", DriveCommands.wheelRadiusCharacterization(drive));
    autoChooser.addOption(
        "Drive Simple FF Characterization", DriveCommands.feedforwardCharacterization(drive));
    autoChooser.addOption(
        "Drive SysId (Quasistatic Forward)",
        drive.sysIdQuasistatic(SysIdRoutine.Direction.kForward));
    autoChooser.addOption(
        "Drive SysId (Quasistatic Reverse)",
        drive.sysIdQuasistatic(SysIdRoutine.Direction.kReverse));
    autoChooser.addOption(
        "Drive SysId (Dynamic Forward)", drive.sysIdDynamic(SysIdRoutine.Direction.kForward));
    autoChooser.addOption(
        "Drive SysId (Dynamic Reverse)", drive.sysIdDynamic(SysIdRoutine.Direction.kReverse));
    autoChooser.addOption(
        "Temp",
        Commands.deadline(
            new WaitCommand(1), DriveCommands.joystickDrive(drive, () -> 1, () -> 0, () -> 0)));
    // Configure the button bindings
    configureButtonBindings();
  }

  private void registerNamedCommands() {
    // Register the Named Commands
    NamedCommands.registerCommand(
        "MoveElevator-L4", new InstantCommand(() -> elevatorSubsystem.moveElevator(31)));
    NamedCommands.registerCommand(
        "Coral-outtake", new InstantCommand(() -> coralManipulatorSubsystem.intake()));
    NamedCommands.registerCommand(
        "MoveSlapDown", new InstantCommand(() -> slapdownSubsystem.angleIntake(-0.6)));
    NamedCommands.registerCommand(
        "StopCoral-outtake", new InstantCommand(() -> coralManipulatorSubsystem.stopMotors()));
    NamedCommands.registerCommand(
        "MoveElevator-0", new InstantCommand(() -> elevatorSubsystem.moveElevator(0)));
    NamedCommands.registerCommand(
        "Score-Coral",
        new SequentialCommandGroup(
            new InstantCommand(
                () -> slapdownSubsystem.angleIntake(Constants.ScorePositions.SlapdownOut)),
            new InstantCommand(
                () -> elevatorSubsystem.moveElevator(Constants.ScorePositions.ElevatorL4)),
            new WaitUntilCommand(
                () ->
                    elevatorSubsystem.getElevatorPosition()
                        == Constants.ScorePositions.ElevatorL4), // Needs testing
            new ParallelCommandGroup(
                new InstantCommand(() -> coralManipulatorSubsystem.intake()), new WaitCommand(0.5)),
            new InstantCommand(() -> coralManipulatorSubsystem.stopMotors()),
            new InstantCommand(
                () -> elevatorSubsystem.moveElevator(Constants.ScorePositions.ElevatorHome))));
    NamedCommands.registerCommand(
        "Coral-Intake",
        new SequentialCommandGroup(
            new ParallelCommandGroup(
                new InstantCommand(() -> coralManipulatorSubsystem.intake()),
                new WaitCommand(0.25)),
            new InstantCommand(() -> coralManipulatorSubsystem.stopMotors())));
  }
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

  private void configureButtonBindings() {

    // score coral
dLeftTrigger.onTrue(    //TODO: (Evens) fix the confliting button bindings
    new SequentialCommandGroup(  
        new InstantCommand(() -> slapdownSubsystem.angleIntake(Constants.ScorePositions.SlapdownOut)),              // move slapdown to safe pos
        new InstantCommand(() -> elevatorSubsystem.moveElevator(desiredCoralPosition)),                             // move elevator to desired pos
        new WaitUntilCommand(() -> elevatorSubsystem.getElevatorPosition() == desiredCoralPosition),                // wait till elevator is at desired pos
        new InstantCommand(() -> coralManipulatorSubsystem.intake()),                                               // start the intake
        new ScheduleCommand(new WaitCommand(0.5).andThen(() -> coralManipulatorSubsystem.stopMotors())),            // stop the intake after 0.5 seconds
        new InstantCommand(() -> elevatorSubsystem.moveElevator(Constants.ScorePositions.ElevatorHome))));          // bring elevator home

//Auto Score Algae to Barge 
dLeftTrigger.onTrue(    //TODO: (Evens) fix the confliting button bindings
    new SequentialCommandGroup(
        new InstantCommand(() -> slapdownSubsystem.angleIntake(Constants.ScorePositions.SlapdownOut)),
        new InstantCommand(() -> elevatorSubsystem.moveElevator(Constants.ScorePositions.ElevatorBarge)),
        new WaitUntilCommand(() -> elevatorSubsystem.getElevatorPosition() == Constants.ScorePositions.ElevatorBarge),
        new InstantCommand(() -> slapdownSubsystem.angleIntake(Constants.ScorePositions.SlapdownOuttakeBarge)),
        new InstantCommand(() -> slapdownSubsystem.outtakeRollers()),
        new ScheduleCommand(new WaitCommand(0.5).andThen(() -> slapdownSubsystem.stopRollers())),
        new InstantCommand(() -> slapdownSubsystem.angleIntake(Constants.ScorePositions.SlapdownOut)),
        new InstantCommand(() -> elevatorSubsystem.moveElevator(Constants.ScorePositions.ElevatorHome))));

//Auto Score Algae to Processor
dRightTrigger.onTrue(
    new SequentialCommandGroup(
        new InstantCommand(() -> slapdownSubsystem.angleIntake(Constants.ScorePositions.SlapdownOut)),
            new InstantCommand(() -> elevatorSubsystem.moveElevator(Constants.ScorePositions.ElevatorProcessor)),
            new WaitUntilCommand(() -> elevatorSubsystem.getElevatorPosition() == Constants.ScorePositions.ElevatorProcessor),
            new InstantCommand(() -> slapdownSubsystem.angleIntake(Constants.ScorePositions.SlapdownOuttakeProcessor)),
            new InstantCommand(() -> slapdownSubsystem.outtakeRollers()),
            new ScheduleCommand(new WaitCommand(0.5).andThen(() -> slapdownSubsystem.stopRollers())),
            new InstantCommand(() -> slapdownSubsystem.angleIntake(Constants.ScorePositions.SlapdownOut)),
            new InstantCommand(() -> elevatorSubsystem.moveElevator(Constants.ScorePositions.ElevatorHome))));

    // pick up alage
dRightBumper.onTrue(
    new SequentialCommandGroup(
        new InstantCommand(() -> slapdownSubsystem.angleIntake(Constants.ScorePositions.SlapdownOut)),      // move slapdown to safe pos
        new InstantCommand(() -> elevatorSubsystem.moveElevator(desiredAlgaePosition)),                     // move elevator to desired pos
        new WaitUntilCommand(() -> elevatorSubsystem.getElevatorPosition() == desiredAlgaePosition),        // wait till elevator is at desired pos
        new InstantCommand(() -> slapdownSubsystem.angleIntake(Constants.ScorePositions.SlapdownIntake)),   // set the intake at the right angle
        new InstantCommand(() -> slapdownSubsystem.intakeRollers()),                                        // start the intake
        new ScheduleCommand(new WaitCommand(0.5).andThen(() -> slapdownSubsystem.stopRollers())),           // stop the intake after 0.5 seconds
        new InstantCommand(() -> elevatorSubsystem.moveElevator(Constants.ScorePositions.ElevatorHome))));  // bring elevator home

    // Pickup alage from Ground 
// dPOVDown.onTrue(
//     new SequentialCommandGroup(
//             new InstantCommand(() -> slapdownSubsystem.angleIntake(Constants.ScorePositions.SlapdownGroundIntake)),
//             new InstantCommand(() -> slapdownSubsystem.intakeRollers()),
//             new ScheduleCommand(new WaitCommand(0.5).andThen(() -> slapdownSubsystem.stopRollers()))));

    // intake coral
dLeftBumper.whileTrue(new InstantCommand(() -> slapdownSubsystem.intakeRollers()))
    .onFalse(new InstantCommand(() -> slapdownSubsystem.stopRollers()));

    //set elevator position to L4 
opY.onTrue(new InstantCommand(() -> desiredCoralPosition = Constants.ScorePositions.ElevatorL4))  
    .onFalse(new InstantCommand(() -> desiredCoralPosition = Constants.ScorePositions.ElevatorL2));  // on false set defualt to L2

    //set elevator position and algae intake to L3
opB.onTrue(new SequentialCommandGroup(
    new InstantCommand(() -> desiredCoralPosition = Constants.ScorePositions.ElevatorL3),
    new InstantCommand(() -> desiredAlgaePosition = Constants.ScorePositions.ElevatorL3Intake)))  
    .onFalse(new SequentialCommandGroup(
        new InstantCommand(() -> desiredCoralPosition = Constants.ScorePositions.ElevatorL2),  // on false set defualt coral level to L2 and slapdown to home
        new InstantCommand(() -> desiredAlgaePosition = Constants.ScorePositions.SlapdownOut)));  

    // set elevator position and algae intake to L2 
opA.onTrue(new SequentialCommandGroup(
        //new InstantCommand(() -> desiredCoralPosition = Constants.ScorePositions.ElevatorL2),
    new InstantCommand(() -> desiredAlgaePosition = Constants.ScorePositions.ElevatorL2Intake)))  
    .onFalse(new SequentialCommandGroup(
            //new InstantCommand(() -> desiredCoralPosition = Constants.ScorePositions.ElevatorL2),  // on false set defualt coral level to L2 and slapdown to home
        new InstantCommand(() -> desiredAlgaePosition = Constants.ScorePositions.SlapdownOut)));  

    //set elevator position to L1 and slapdown to L1/ground
opX.onTrue(new SequentialCommandGroup(
    new InstantCommand(() -> desiredCoralPosition = Constants.ScorePositions.ElevatorL1),
    new InstantCommand(() -> desiredAlgaePosition = Constants.ScorePositions.SlapdownGroundIntake)))  
.onFalse(new SequentialCommandGroup(
    new InstantCommand(() -> desiredCoralPosition = Constants.ScorePositions.ElevatorL2),   // on false set defualt coral level to L2 and slapdown to home
    new InstantCommand(() -> desiredAlgaePosition = Constants.ScorePositions.SlapdownOut)));  

    // Default command, normal field-relative drive
    drive.setDefaultCommand(
        DriveCommands.joystickDrive(
            drive,
            () -> xboxDriverController.getLeftY(),
            () -> xboxDriverController.getLeftX(),
            () -> -xboxDriverController.getRightX()));

    // Lock to 0° when A button is held
    // xboxDriverController
    //     .a()
    //     .whileTrue(
    //         DriveCommands.joystickDriveAtAngle(
    //             drive,
    //             () -> -xboxDriverController.getLeftY(),
    //             () -> -xboxDriverController.getLeftX(),
    //             () -> new Rotation2d()));

    // Switch to X pattern when X button is pressed
    // controller.x().onTrue(Commands.runOnce(drive::stopWithX, drive));

    // Reset gyro to 0° when B button is pressed
    // dB.onTrue(Commands.runOnce(() -> drive.resetGyro()));

    // dLeftBumper
    //     .whileTrue(new InstantCommand(() -> slapdownSubsystem.intakeRollers()))
    //     .onFalse(new InstantCommand(() -> slapdownSubsystem.stopRollers()));
    // dRightBumper
    //     .whileTrue(new InstantCommand(() -> slapdownSubsystem.outtakeRollers()))
    //     .onFalse(new InstantCommand(() -> slapdownSubsystem.stopRollers()));
    // dLeftTrigger.onTrue(new InstantCommand(() -> slapdownSubsystem.angleIntake(-3)));
    // dRightTrigger.onTrue(new InstantCommand(() -> slapdownSubsystem.angleIntake(0)));
    // dLeftTrigger
    //     .whileTrue(new InstantCommand(() -> coralManipulatorSubsystem.intake()))
    //     .whileFalse(new InstantCommand(() -> coralManipulatorSubsystem.stopMotors()));

    // dRightTrigger.onTrue(intakeCoralCommand);

    dPOVUp.onTrue(new InstantCommand(() -> slapdownSubsystem.angleIntake(-0.6)));
    // dLeftTrigger.onTrue(new InstantCommand(() -> sensorSubsytem.stopSensorBasedCommads()));

    // dLeftBumper.onTrue(Commands.runOnce(SignalLogger::start));
    // dRightBumper.onTrue(Commands.runOnce(SignalLogger::stop));

    /*
     * Joystick Y = quasistatic forward
     * Joystick A = quasistatic reverse
     * Joystick B = dynamic forward
     * Joystick X = dyanmic reverse
     */
    // dY.whileTrue(elevatorSubsystem.sysIdQuasistatic(SysIdRoutine.Direction.kForward));
    // dA.whileTrue(elevatorSubsystem.sysIdQuasistatic(SysIdRoutine.Direction.kReverse));
    // dB.whileTrue(elevatorSubsystem.sysIdDynamic(SysIdRoutine.Direction.kForward));
    // dX.whileTrue(elevatorSubsystem.sysIdDynamic(SysIdRoutine.Direction.kReverse));
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return autoChooser.get();
  }
}
