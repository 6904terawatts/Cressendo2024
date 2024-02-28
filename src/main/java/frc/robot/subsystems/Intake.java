// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import static frc.robot.Constants.intakeConstants.intakeSpeed;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import static frc.robot.Constants.LauncherConstants.kFeederID;
import static frc.robot.Constants.LauncherConstants.kIntakeFeederSpeed;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

CANSparkMax m_IntakeWheels1;
CANSparkMax m_IntakeWheels2;
CANSparkMax m_feedWheel;


  /** Creates a new ExampleSubsystem. */
  public Intake() {
    
    m_IntakeWheels1 = new CANSparkMax(3, MotorType.kBrushless);
    m_IntakeWheels2 = new CANSparkMax(4,MotorType.kBrushless);
    m_IntakeWheels1.restoreFactoryDefaults();
    m_IntakeWheels2.restoreFactoryDefaults();
    // m_feedWheel = new PWMSparkMax(kFeederID);
    m_IntakeWheels1.setInverted(true);

   
  
  }

      
   // An accessor method to set the speed (technically the output percentage) of the launch wheel
   

  public Command getReverseIntakeCommand() {
          // The startEnd helper method takes a method to call when the command is initialized and one to
          // call when it ends
          return this.startEnd(
              // When the command is initialized, set the wheels to the intake speed values
              () -> {
                setIntakeWheels(-intakeSpeed);
                
              },
              // When the command stops, stop the wheels
              () -> {
                stop();
              });
              
            }
  // An accessor method to set the speed (technically the output percentage) of the feed wheel
 
  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public void stop() {
    m_IntakeWheels1.set(0);
    m_IntakeWheels2.set(0);

  }
  public void setIntakeWheels(double speed) {
    System.out.println("Intake is running");
    m_IntakeWheels1.set(speed);
    m_IntakeWheels2.set(speed);
  }
  // public void setFeedWheel(double speed) {
  //   m_feedWheel.set(speed);
  // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
