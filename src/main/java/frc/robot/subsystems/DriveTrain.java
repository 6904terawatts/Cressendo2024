// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */


// private TalonFX rightLeaderMotor, rightFollowMotor, leftLeaderMotor, leftFollowMotor;

  private final TalonFX[] motors = {
  new TalonFX(Constants.leftBackMotorId),
  new TalonFX(Constants.rightBackMotorId),
  new TalonFX(Constants.leftFrontMotorId),
  new TalonFX(Constants.rightFrontMotorId)
};
  public DriveTrain() {
for (TalonFX motor : motors) {
  
  motor.setNeutralMode(NeutralModeValue.Brake);
}

motors[1].setInverted(true);
motors[3].setInverted(true);

// rightLeaderMotor = motors[1];
// rightFollowMotor = motors[2];
// leftLeaderMotor = motors[3];
// leftFollowMotor = motors[4];

motors[2].set(motors[0].getDeviceID());
motors[3].set(motors[1].getDeviceID());


// motors[2].follow(motors[0]);
// motors[3].follow(motors[1]);



// motors[2].set(motors[0].getDeviceID());
// motors[3].set(motors[1].getDeviceID());
 

  }

  public void setArcadeDrive(double throttle, double turn) {
  double leftOutput = throttle + turn;
  double rightOutput = throttle - turn;
  
  
  if (Math.abs(leftOutput) < Constants.kArcadeDeadBand) {
    leftOutput = 0;
  }
  if (Math.abs(rightOutput) < Constants.kArcadeDeadBand) {
    rightOutput = 0;
  }
    double squaredRightOutput = rightOutput * rightOutput * Math.signum(rightOutput); 
    double squaredLeftOutput = leftOutput * leftOutput * Math.signum(leftOutput); 
  
    setPercentOutput(squaredLeftOutput, squaredRightOutput);
  }

  public void setPercentOutput(double leftOutput, double rightOutput) {
    motors[0].set(leftOutput);
    motors[1].set(rightOutput);
}




  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
