// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;



import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.PWMLauncher;

public class noteAuto extends Command {
    private final PWMLauncher launcherSubsystem;
    private final DriveTrain driveSubsystem;

    public noteAuto(PWMLauncher launcherSubsystem, DriveTrain driveSubsystem) {
        this.launcherSubsystem = launcherSubsystem;
        this.driveSubsystem = driveSubsystem;
        addRequirements(launcherSubsystem, driveSubsystem);
    }

    @Override
    public void initialize() {
        // Initialize the launcher and drive subsystems
        launcherSubsystem.setLaunchWheel(1.0); // Assuming 1.0 is full speed
        launcherSubsystem.setFeedWheel(1.0);   // Assuming 1.0 is full speed
        driveSubsystem.arcadeDrive(-0.5, -0.5); // Assuming -0.5 is a backward speed
    }

    @Override
    public void execute() {
        // Keep executing until the command is interrupted or finished
    }

    @Override
    public void end(boolean interrupted) {
        // Stop the launcher and drive subsystems
        launcherSubsystem.stop();
        driveSubsystem.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        // Replace this with your condition to determine when to finish the command
        return false; // Keep executing until interrupted or finished
    }
}
