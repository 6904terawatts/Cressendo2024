// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.HookSubsystem;

public class MoveHookCommand extends Command {
    private final HookSubsystem hookSubsystem;
    private final boolean moveUp;

    public MoveHookCommand(HookSubsystem hookSubsystem, boolean moveUp) {
        this.hookSubsystem = hookSubsystem;
        this.moveUp = moveUp;
        addRequirements(hookSubsystem);
    }

    @Override
    public void initialize() {
        // Determine whether to move the hook up or down based on the boolean parameter
        if (moveUp) {
            hookSubsystem.moveUp(); // Call the method to move the hook up
        } else {
            hookSubsystem.moveDown(); // Call the method to move the hook down
        }
    }

    @Override
    public void execute() {
        // Keep executing until the command is interrupted or finished
    }

    @Override
    public void end(boolean interrupted) {
        // Stop the hook movement when the command ends
        hookSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        // Replace this with your condition to determine when to finish the command
        return false; // Keep executing until interrupted or finished
    }
}