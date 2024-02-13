// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class HookSubsystem extends SubsystemBase {
    private final PWMSparkMax hookMotor;

    public HookSubsystem() {
        hookMotor = new PWMSparkMax(Constants.hookMotorId);
    }

    public void moveUp() {
        hookMotor.set(Constants.hookUpSpeed);
    }

    public void moveDown() {
        hookMotor.set(Constants.hookDownSpeed);
    }

    public void stop() {
        hookMotor.set(0);
    }
}