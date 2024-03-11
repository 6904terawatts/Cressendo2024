// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autos;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static Command exampleAuto(DriveTrain m_driveTrain) {

    
    return new RunCommand(() -> m_driveTrain.arcadeDrive(-.5, 0))
        .withTimeout(5)
        .andThen(new RunCommand(() -> m_driveTrain.arcadeDrive(0, 0)));
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
