// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HookSubsystem extends SubsystemBase{
    
 private final DoubleSolenoid clawSolenoid =
 new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);

 public HookSubsystem() {

 }

 public void sethook(DoubleSolenoid.Value value) {
    clawSolenoid.set(value);
 }
}