package frc.robot.subsystems;

import static frc.robot.CtreUtils.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix6.hardware.Pigeon2;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/* This class declares the subsystem for the robot drivetrain if controllers are connected via CAN. Make sure to go to
 * RobotContainer and uncomment the line declaring this subsystem and comment the line for PWMDrivetrain.
 *
 * The subsystem contains the objects for the hardware contained in the mechanism and handles low level logic
 * for control. Subsystems are a mechanism that, when used in conjuction with command "Requirements", ensure
 * that hardware is only being used by 1 command at a time.
 */
public class DriveTrain extends SubsystemBase {
  /*Class member variables. These variables represent things the class needs to keep track of and use between
  different method calls. */
  DifferentialDrive m_drivetrain;

  WPI_TalonSRX leftFront;
  WPI_TalonSRX leftRear;
  WPI_TalonSRX rightFront;
  WPI_TalonSRX rightRear;


    DifferentialDriveOdometry odometry;

  /*Constructor. This method is called when an instance of the class is created. This should generally be used to set up
   * member variables and perform any configuration or set up necessary on hardware.
   */
  public DriveTrain() {
  

    leftFront = new WPI_TalonSRX(Constants.leftFrontMotorId);
    leftRear = new WPI_TalonSRX(Constants.leftBackMotorId);
    rightFront = new WPI_TalonSRX(Constants.rightFrontMotorId);
    rightRear = new WPI_TalonSRX(Constants.rightBackMotorId);

    /*Sets current limits for the drivetrain motors. This helps reduce the likelihood of wheel spin, reduces motor heating
     *at stall (Drivetrain pushing against something) and helps maintain battery voltage under heavy demand */
    TalonSRXConfiguration motorConfig = generateSRXDriveMotorConfig();
    leftFront.configAllSettings(motorConfig);
    leftRear.configAllSettings(motorConfig);
    rightFront.configAllSettings(motorConfig);
    rightRear.configAllSettings(motorConfig);

   

    // Set the rear motors to follow the front motors.
    leftRear.follow(leftFront);
    rightRear.follow(rightFront);

    // Invert the left side so both side drive forward with positive motor outputs
    leftFront.setInverted(true);
    leftRear.setInverted(true);
    rightFront.setInverted(false);
    leftRear.setInverted(false);
    // Put the front motors into the differential drive object. This will control all 4 motors with
    // the rears set to follow the fronts
    m_drivetrain = new DifferentialDrive(leftFront, rightFront);

   
      
  }



  /*Method to control the drivetrain using arcade drive. Arcade drive takes a speed in the X (forward/back) direction
   * and a rotation about the Z (turning the robot about it's center) and uses these to control the drivetrain motors */
  public void arcadeDrive(double speed, double rotation) {
    m_drivetrain.arcadeDrive(speed, rotation);

    double leftOutput = speed + rotation;
  double rightOutput = speed - rotation;

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
   leftFront.set(ControlMode.PercentOutput, leftOutput);
  rightFront.set(ControlMode.PercentOutput, rightOutput);
  
  }

  


  @Override
  public void periodic() {
 
  }

  //   @Override
  //   public void close() {
  //     leftRear.close();
  //     rightRear.close();
  //   }
}