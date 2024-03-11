// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PWMLauncher;

public class RunFeedWheel extends Command {
  /** Creates a new RunIntake. */
PWMLauncher m_Launcher;
double m_speed;

  public RunFeedWheel(PWMLauncher launcher, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.

m_Launcher = launcher;
m_speed = speed;
addRequirements(m_Launcher);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    m_Launcher.setFeedWheel(m_speed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    m_Launcher.setFeedWheel(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
