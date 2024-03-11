// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autos;



import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.PWMLauncher;

public class twoNoteAuto extends Command {
    private final PWMLauncher launcherSubsystem;
    private final DriveTrain driveSubsystem;

    private final noteAuto shootAndBackUpCommand;
    private final noteAuto intakeAndForwardCommand;

    private boolean isFirstShotComplete = false;

    public twoNoteAuto(PWMLauncher launcherSubsystem, DriveTrain driveSubsystem) {
        this.launcherSubsystem = launcherSubsystem;
        this.driveSubsystem = driveSubsystem;
        
        // Create noteAuto commands for shooting and backing up, and for intaking and moving forward
        this.shootAndBackUpCommand = new noteAuto(launcherSubsystem, driveSubsystem);
        this.intakeAndForwardCommand = new noteAuto(launcherSubsystem, driveSubsystem);

        addRequirements(launcherSubsystem, driveSubsystem);
    }

    @Override
    public void initialize() {
        // Start shooting and backing up
        shootAndBackUpCommand.initialize();
    }

    @Override
    public void execute() {
        // Check if the first shot is complete
        if (shootAndBackUpCommand.isFinished() && !isFirstShotComplete) {
            // Start intaking and moving forward
            intakeAndForwardCommand.initialize();
            isFirstShotComplete = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        // End both commands when the command ends
        shootAndBackUpCommand.end(interrupted);
        intakeAndForwardCommand.end(interrupted);
    }

    @Override
    public boolean isFinished() {
        // Finish when both commands are finished
        return shootAndBackUpCommand.isFinished() && intakeAndForwardCommand.isFinished();
    }
}