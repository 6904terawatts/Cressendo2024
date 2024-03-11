// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autos;



import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.PWMLauncher;

public class noteAuto extends Command {
    private final PWMLauncher m_launcherSubsystem;
    private final DriveTrain m_driveSubsystem;

    public noteAuto(PWMLauncher launcherSubsystem, DriveTrain driveSubsystem) {
        m_launcherSubsystem = launcherSubsystem;
        m_driveSubsystem = driveSubsystem;
        addRequirements(launcherSubsystem, driveSubsystem);
    }

    @Override
    public void initialize() {
        // Initialize the launcher and drive subsystems
     
    }

    @Override
    public void execute() {

        new RunCommand(() -> m_launcherSubsystem.setLaunchWheel(1.0))
        .withTimeout(2)
        .andThen(new RunCommand(() -> m_launcherSubsystem.setFeedWheel(1.0)))
        .withTimeout(2)
        .andThen(new RunCommand(() -> m_launcherSubsystem.setLaunchWheel(0))).alongWith(new RunCommand(() -> m_launcherSubsystem.setFeedWheel(0)))
        .andThen(new RunCommand (() -> m_driveSubsystem.arcadeDrive(-.5, 0)));
  }

        // Keep executing until the command is interrupted or finished
    


    @Override
    public void end(boolean interrupted) {
        // Stop the launcher and drive subsystems
        m_launcherSubsystem.stop();
        m_driveSubsystem.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        // Replace this with your condition to determine when to finish the command
        return false; // Keep executing until interrupted or finished
    }
}
