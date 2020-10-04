/**
 * Generates instance of Process.
 * 
 * @author Steven Lineses
 * @version 1.0
 */

import java.util.Random;

public class ProcessGenerator {
        private double processSpawnProbability;
        private Random random;

        public ProcessGenerator(double probability) {
            this.processSpawnProbability = probability;
            this.random = new Random();
        }

        /**
         * Used by cpu simulation to determine whether or not to generate a new process.
         */
        public boolean query() {
            return random.nextDouble() < processSpawnProbability;
        }

        /**
         * The processes priority and time to finish are randomly generated within a range
         * based on the max priority and max process time passed in from the command line.
         * 
         * Add 1 to the randomly generated int for the priority and process time since both
         * must be at least 1 and less than their maximum respective values.
        */
        public Process getNewProcess(int currentTime, int maxProcessTime, int maxPriority) {
            return new Process(currentTime, random.nextInt(maxPriority) + 1, random.nextInt(maxProcessTime) + 1);
        }
}