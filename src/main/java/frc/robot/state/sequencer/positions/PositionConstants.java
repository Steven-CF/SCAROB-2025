package frc.robot.state.sequencer.positions;

import frc.robot.subsystems.hand.HandConstants;

public final class PositionConstants {
    public final static double clamperClosedPosition = HandConstants.clamperHomePosition;
    public final static double coralIntakeWidth = HandConstants.clamperCoralPosition;
    public final static double algaeIntakeWidth = HandConstants.clamperAlgaePosition;
    public final static double reefIntakeWidth = HandConstants.clamperReefIntakePosition;

    /*
     * !!!!!!!!!!!!!!!!!!!!!!!!
     * CORAL INTAKE POSITION CONSTANTS
     * !!!!!!!!!!!!!!!!!!!!!!!!
     */

    public static final class CORAL_INTAKE {
        public static final class CORAL_FEEDER {
            public final static double clamperIntakePosition = coralIntakeWidth;
        }

        public static final class CORAL_FLOOR_UPRIGHT {
            public final static double armForwardPosition = 35;
            public final static double clamperIntakePosition = coralIntakeWidth;
        }
    }

    
    /*
     * !!!!!!!!!!!!!!!!!!!!!!!!
     * CORAL SCORE POSITION CONSTANTS
     * !!!!!!!!!!!!!!!!!!!!!!!!
     */

    public static final class CORAL_SCORE {
        // TODO needs real positions, we haven't worked on this yets
        public static final class CORAL_L1 {
            public final static double raiseElevatorPosition = 4;
            public final static double raiseElevatorThresholdPosition = 4.1;
            public final static double lowerElevatorThresholdPosition = 3.9;
            public final static double armForwardPosition = 0;
            public final static double armScorePosition = 0;
        }

        public static final class CORAL_L2 {
            public final static double raiseElevatorPosition = 9;
            public final static double raiseElevatorThresholdPosition = 9.1;
            public final static double lowerElevatorThresholdPosition = 8.9;
            public final static double armForwardPosition = 8.5;
            public final static double armScorePosition = 15;
        }

        public static final class CORAL_L3 {
            public final static double raiseElevatorPosition = 17;
            public final static double raiseElevatorThresholdPosition = 17.1;
            public final static double lowerElevatorThresholdPosition = 16.9;
            public final static double armForwardPosition = 8.5;
            public final static double armScorePosition = 15;
        }

        public static final class CORAL_L4 {
            public final static double raiseElevatorPosition = 27;
            public final static double raiseElevatorThresholdPosition = 27.1;
            public final static double lowerElevatorThresholdPosition = 26.9;
            public final static double armForwardPosition = 9;
            public final static double armScorePosition = 15;
        }
    }


    /*
     * !!!!!!!!!!!!!!!!!!!!!!!!
     * ALGAE INTAKE POSITION CONSTANTS
     * !!!!!!!!!!!!!!!!!!!!!!!!
     */

    public static final class ALGAE_INTAKE {
        public static final class ALGAE_REEF_L2 {
            // position definitions
            public final static double raiseElevatorPosition = 7;
            public final static double secondStageElevatorPosition = 17;
            public final static double clamperIntakePosition = reefIntakeWidth;
            public final static double clamperHoldPosition = algaeIntakeWidth;
        }

        public static final class ALGAE_REEF_L3 {
            public final static double raiseElevatorPosition = 33;
            public final static double secondStageElevatorPosition = 43;
            public final static double clamperIntakePosition = reefIntakeWidth;
            public final static double clamperHoldPosition = algaeIntakeWidth;
        }

        public static final class ALGAE_FLOOR {
            public final static double armForwardPosition = 35;
            public final static double clamperIntakePosition = algaeIntakeWidth;
        }
    }


    /*
     * !!!!!!!!!!!!!!!!!!!!!!!!
     * ALGAE SCORE POSITION CONSTANTS
     * !!!!!!!!!!!!!!!!!!!!!!!!
     */

    public static final class ALGAE_SCORE {
        public static final class ALGAE_SCORE_BARGE {
            public final static double raiseElevatorPosition = 98;
            public final static double raiseElevatorThresholdPosition = 58;
            public final static double lowerElevatorThresholdPosition = 97;
            public final static double armForwardPosition = 5;
        }

        public static final class ALGAE_HANDOFF {
            public final static double armForwardPosition = 35;
        }
    }
}