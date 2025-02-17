# Tutorial - From Scratch

This [tutorial](doc/TutorialFromScratch.md) is intended to get you setup and running with a practice environment and simulator to allow you to learn by tinkering.

[Return to README](../README.md)

## New Robot Project

Start "2025 WPILib VS Code" and create a new Robot Command project. [Detailed Instructions](https://docs.wpilib.org/en/stable/docs/software/vscode-overview/creating-robot-program.html)

TLDR;
- once VS Code opens press `ctrl-shirt-p`
- type `WPILib`
- select `Create a new Project`
- click button `Project Type` (note the ui is a little wonky mixing form buttons with VS Code drop downs)
  - choose `Template`
  - choose `Java`
  - choose `Command Robot`
- set the `Base Folder` (note: recommend creating a base folder for all of your work, this process will add a new folder under it)
- set the `Project Name` (ex. "Practice")
- set the `Team Number` to `10004`
- check `Enable Destop Support` (required for automated testing and simulation)
- click `Generate Project` and open in current or new VS Code.  (Recommend trusting work in new folder when asked)
- right click the `build.gradle` and select `Build Robot Code`. (Your project should build without issue)

## Run Simulator

Detailed simulation instructions can be found [here](https://docs.wpilib.org/en/stable/docs/software/wpilib-tools/robot-simulation/introduction.html).

TLDR;
- Run the `Sim GUI` by presing `ctrl-shift-p`, typing `WPILib`, and selecting `Simulate Robot Code`
- Click `OK` button next to the drop down when `Sim GUI` appears. (Make sure the checkbox is selected)
- In the Robot Simulation window

Note: if vendor code does not support simulation, isolate to the robot only by requiring flag `RobotBase.isReal()` around vendor code calls.

## Point Tools to Simulator

By default tools are setup to point the robotRIO, so we will point them to the simulator which should currently be running per the previous section.

Run the `Shuffleboard 2025` in the `2025 WPLib Tools` folder found on your desktop.
- Select `Preferences` from the `File` menu
- Set `Plugins=>NetworkTables=>Server` to `localhost`

Run `Smarthdashboard 2025`
- Set the `Team Number/Host` to `localhost` (this can be found under preferences if you are not prompted when opening the first time)

Run `Glass 2025`
- Set mode to `Client NT4` under `NetworkTable Settings`
- Set team/IP to `localhost` and click `Apply` button

Open `AdvantageScope (WPILib) 2025`
- Select `Connect to Simulator` from file menu

## Code

Follow this tutorial to update your code: [drivesim-tutorial](https://docs.wpilib.org/en/stable/docs/software/wpilib-tools/robot-simulation/drivesim-tutorial/index.html)

TLDR;
Add the following files to your project and replace `edu.wpi.first.wpilibj.examples.statespacedifferentialdrivesimulation` namespace references with to `frc.robot`:
- Add [DriveSubsystem.java](https://github.com/wpilibsuite/allwpilib/blob/v2024.3.2/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/statespacedifferentialdrivesimulation/subsystems/DriveSubsystem.java) to your subsystems folder.
- Replace [Constants.java](https://github.com/wpilibsuite/allwpilib/blob/v2024.3.2/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/statespacedifferentialdrivesimulation/Constants.java)
- Replace [RobotContainer.java](https://github.com/wpilibsuite/allwpilib/blob/v2024.3.2/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/statespacedifferentialdrivesimulation/RobotContainer.java)
- Replace [Robot.java](https://github.com/wpilibsuite/allwpilib/blob/v2024.3.2/wpilibjExamples/src/main/java/edu/wpi/first/wpilibj/examples/statespacedifferentialdrivesimulation/Robot.java)

Click the rebuild/refresh symbol

## Advantage Scope

Return to the Advantage Scope window and size it so that it can share screen space with the GUI Sim.
Navigate to the 2d or 3d view
Set the game source to `2025 Field` and any other desired fields
Drag the Field=>Robot from the left menu into the pose location


## GUI Sim

Return to the open GUI Sim window.
- Drag a joystick (if you have one) or keyboard from the system joysticks menu to joysticks menu.
- Configure keyboard as desired from the DC drop down menu at the top
    - setup axis 1 (fwd/backward) and axis 4 (rotation)
- Set the window to share the screen space with Advantage Scope
- Change the robot state to Autonomous for a few seconds it should move sporadically
- Change the robot state to Teleoperated
  - use asdw to drive the vehicle

Note: you may need to decrease the values for the keyboard settings if you are using the keyboard.

Happy Roboting!

## Exit

[Return to README](../README.md)
