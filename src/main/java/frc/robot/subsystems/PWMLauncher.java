// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static frc.robot.Constants.LauncherConstants.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PWMLauncher extends SubsystemBase  {

  RelativeEncoder m_shooterEncoder;
  CANSparkMax m_launchWheel = new CANSparkMax(Constants.LauncherConstants.kLauncherID, MotorType.kBrushless);
  CANSparkMax m_feedWheel = new CANSparkMax(Constants.LauncherConstants.kFeederID, MotorType.kBrushless);

   SparkPIDController m_shooterPID = m_launchWheel.getPIDController();

  PIDController Launcher = new PIDController(Constants.LauncherConstants.SHOOTER_kP, 0, 0);
  // /** Creates a new Launcher. */
  public PWMLauncher() {
    // m_launchWheel = new CANSparkMax(kLauncherID, MotorType.kBrushless);
    // m_feedWheel = new CANSparkMax(kFeederID, MotorType.kBrushless);

    m_launchWheel.restoreFactoryDefaults();
    m_launchWheel.setSmartCurrentLimit(Constants.LauncherConstants.MOTOR_CURRENT_LIMIT);
    m_launchWheel.setInverted(false);
    m_launchWheel.setIdleMode(CANSparkMax.IdleMode.kCoast);
    
    m_shooterEncoder = m_launchWheel.getEncoder();
    // m_shooterEncoder.setVelocityConversionFactor(3);
    m_shooterPID.setFeedbackDevice(m_shooterEncoder);
    m_shooterPID.setP(Constants.LauncherConstants.SHOOTER_kP);

    m_launchWheel.burnFlash();

    m_feedWheel.restoreFactoryDefaults();
    m_feedWheel.setSmartCurrentLimit(Constants.LauncherConstants.MOTOR_CURRENT_LIMIT);
    m_feedWheel.setInverted(false);
    m_feedWheel.setIdleMode(CANSparkMax.IdleMode.kCoast);
    m_feedWheel.burnFlash();


  }

  public void periodic() {
    // This method will be called once per scheduler run
    super.periodic();
    getMeasurement();
    SmartDashboard.putNumber("Shooter output", m_launchWheel.get());
   SmartDashboard.putNumber("Shooter Setpoint", Launcher.getSetpoint()); 
  }

  /**
   * This method is an example of the 'subsystem factory' style of command creation. A method inside
   * the subsytem is created to return an instance of a command. This works for commands that
   * operate on only that subsystem, a similar approach can be done in RobotContainer for commands
   * that need to span subsystems. The Subsystem class has helper methods, such as the startEnd
   * method used here, to create these commands.
   */
  public Command getIntakeCommand() {
    // The startEnd helper method takes a method to call when the command is initialized and one to
    // call when it ends
    return this.startEnd(
        // When the command is initialized, set the wheels to the intake speed values
        () -> {
          setFeedWheel(kIntakeFeederSpeed);
          setLaunchWheel(kIntakeLauncherSpeed);
        },
        // When the command stops, stop the wheels
        () -> {
          stop();
        });
  }



  public void useOutput( double setpoint) {
    // Use the output here
    m_shooterPID.setReference(setpoint, ControlType.kVelocity);
    System.out.println("Setting Shooter Setpoint");
  }
  // An accessor method to set the speed (technically the output percentage) of the launch wheel
  public void setLaunchWheel(double speed) {
    m_launchWheel.set(speed);
  }




  // An accessor method to set the speed (technically the output percentage) of the feed wheel
  public void setFeedWheel(double speed) {
    m_feedWheel.set(speed);
  }




  // A helper method to stop both wheels. You could skip having a method like this and call the
  // individual accessors with speed = 0 instead
  public void stop() {
    System.out.println("Stopping PWMLauncher");
    m_launchWheel.set(0);
    m_feedWheel.set(0);
  }

  public double getLaunchWheelVelocity() {
    return m_launchWheel.getEncoder().getVelocity();
  }

  public double getFeedWheelVelocity() {
    return m_feedWheel.getEncoder().getVelocity();
  }

  public double getMeasurement() {
    SmartDashboard.putNumber("SHOOTER RPM", getLaunchWheelVelocity());
    return getFeedWheelVelocity();
  }

}
