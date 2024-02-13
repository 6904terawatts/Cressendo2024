// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** An example command that uses an example subsystem. */
public class DriveForward extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveForward(DriveTrain driveTrain) {
    DoubleSupplier throttle = () -> -0.4;
    DoubleSupplier turn = () -> 0;

    addCommands(new SetArcadeDrive(driveTrain, throttle, turn).withTimeout(5),
      new SetArcadeDrive(driveTrain, ()-> 0, ()->0));


    

  }


}
