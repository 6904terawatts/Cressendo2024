package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.CtreUtils;

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

  TalonFX leftFront;
  TalonFX leftRear;
  TalonFX rightFront;
  TalonFX rightRear;


    DifferentialDriveOdometry odometry;

  /*Constructor. This method is called when an instance of the class is created. This should generally be used to set up
   * member variables and perform any configuration or set up necessary on hardware.
   */
  public DriveTrain() {
  

    leftFront = new TalonFX(Constants.leftFrontMotorId);
    leftRear = new TalonFX(Constants.leftBackMotorId);
    rightFront = new TalonFX(Constants.rightFrontMotorId);
    rightRear = new TalonFX(Constants.rightBackMotorId);

    /*Sets current limits for the drivetrain motors. This helps reduce the likelihood of wheel spin, reduces motor heating
     *at stall (Drivetrain pushing against something) and helps maintain battery voltage under heavy demand */
    TalonFXConfiguration motorConfig = new TalonFXConfiguration();
   CtreUtils.configureTalonFx(leftFront, motorConfig);
   CtreUtils.configureTalonFx(rightFront, motorConfig);


    // Set the rear motors to follow the front motors.
  //  leftRear.set(leftFront.getDeviceID());
  //   rightRear.set(rightFront.getDeviceID());
  
    // Invert the left side so both side drive forward with positive motor outputs
    leftFront.setInverted(true);
    rightRear.setInverted(false);
    rightFront.setInverted(false);
    leftRear.setInverted(true);
    // Put the front motors into the differential drive object. This will control all 4 motors with
    // the rears set to follow the fronts
    m_drivetrain = new DifferentialDrive(leftFront, rightFront);

   
      
  }

  public static TalonFXConfiguration generateFXDriveMotorConfig() {
    TalonFXConfiguration motorConfig = new TalonFXConfiguration();

    motorConfig.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.RotorSensor;
    motorConfig.Slot0.kV = 0.1185;
    motorConfig.Slot0.kP = 0.24;
    motorConfig.Slot0.kI = 0.0;
    motorConfig.Slot0.kD = 0.0;

    motorConfig.Voltage.PeakForwardVoltage = 12;
    motorConfig.Voltage.PeakReverseVoltage = -12;

    motorConfig.CurrentLimits.SupplyCurrentLimit = 35;
    motorConfig.CurrentLimits.SupplyCurrentThreshold = 60;
    motorConfig.CurrentLimits.SupplyTimeThreshold = 0.1;
    motorConfig.CurrentLimits.SupplyCurrentLimitEnable = true;

    motorConfig.OpenLoopRamps.VoltageOpenLoopRampPeriod = 0.25; // TO
    // DO adjust this later
    motorConfig.ClosedLoopRamps.VoltageClosedLoopRampPeriod = 0.1; // TODO Adjust this later

    motorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    motorConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;

    return motorConfig;
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
  leftFront.set(leftOutput);
  rightFront.set(rightOutput);
  leftRear.set(leftOutput);
  rightRear.set(rightOutput);

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