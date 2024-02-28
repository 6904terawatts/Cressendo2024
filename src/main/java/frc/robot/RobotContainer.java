// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.LauncherConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.DriveForward;
import frc.robot.commands.LaunchNote;
import frc.robot.commands.PrepareLaunch;
import frc.robot.commands.RunFeedWheel;
import frc.robot.commands.RunIntake;
import frc.robot.commands.RunReverseIntake;
import frc.robot.commands.SetArcadeDrive;
import frc.robot.commands.noteAuto;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.HookSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.PWMLauncher;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

private final PWMLauncher m_launcher = new PWMLauncher();
private final Intake m_Intake = new Intake();
private final DriveTrain m_driveTrain = new DriveTrain();
private final HookSubsystem m_hook = new HookSubsystem();
private final static Joystick leftJoystick = new Joystick(Constants.leftJoystickId);
private final static Joystick rightJoystick = new Joystick(Constants.rightJoystickId);


private final CommandXboxController m_operatorController =
      new CommandXboxController(OperatorConstants.kOperatorControllerPort);




  

  // Replace with CommandPS4Controller or CommandJoystick if needed
  
     

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    configureBindings();
    m_driveTrain.setDefaultCommand(new SetArcadeDrive(m_driveTrain, leftJoystick::getY, rightJoystick::getX));



    // Configure the trigger bindings
   
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
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
     /*Create an inline sequence to run when the operator presses and holds the A (green) button. Run the PrepareLaunch
     * command for 1 seconds and then run the LaunchNote command */
    m_operatorController
        .x()
        .whileTrue(new PrepareLaunch(m_launcher).withTimeout(LauncherConstants.kLauncherDelay).andThen(new LaunchNote(m_launcher)).handleInterrupt(() -> m_launcher.stop()));



// Set up a binding to run the intake command while the operator is pressing and holding the
    // left Bumper
    m_operatorController.y().whileTrue(m_launcher.getIntakeCommand());

    m_operatorController.rightBumper().whileTrue(new RunIntake(m_Intake, 1).alongWith(new RunFeedWheel(m_launcher, 1)));
    m_operatorController.leftBumper().whileTrue(new RunReverseIntake(m_Intake, 1));
  

    // Create a button for moving the hook up
m_operatorController
.b()
.onTrue(new InstantCommand(()->m_hook.sethook(Value.kForward))); // true indicates moving the hook up

// Create a button for moving the hook down
m_operatorController
.a()
.onTrue(new InstantCommand(()->m_hook.sethook(Value.kReverse))); // false indicates moving the hook down


    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
  
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    
    // An example command will be run in autonomous
    return new DriveForward(m_driveTrain);
    }
  }

