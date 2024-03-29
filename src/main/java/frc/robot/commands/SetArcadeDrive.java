// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;

/** An example command that uses an example subsystem. */
public class SetArcadeDrive extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  


  private DriveTrain m_driveTrain;
  private DoubleSupplier m_leftOutput, m_rightOutput;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
   public SetArcadeDrive(DriveTrain driveTrain, DoubleSupplier leftOutput, DoubleSupplier rightOutput) {
    m_driveTrain = driveTrain;
    m_leftOutput = leftOutput;
    m_rightOutput = rightOutput;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    m_driveTrain.arcadeDrive(m_leftOutput.getAsDouble(), -m_rightOutput.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
