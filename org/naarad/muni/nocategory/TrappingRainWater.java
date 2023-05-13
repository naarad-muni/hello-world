package org.naarad.muni.nocategory;

/**
 * <a href="https://leetcode.com/problems/trapping-rain-water/">trapping rain water</a>
 * <p>
 * <p>
 * {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1} => 6
 * {4,2,0,3,2,5} => 9
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        TrappingRainWater rainWater = new TrappingRainWater();
        System.out.println(rainWater.trap(new int[]{4, 2, 0, 3, 2, 5}));
    }

    public int trap(int[] height) {
        int[] leftToRightMax = new int[height.length];
        int[] rightToLeftMax = new int[height.length];

        int currentMax = 0;
        for (int i = 0; i < leftToRightMax.length; i++) {
            currentMax = Math.max(currentMax, height[i]);
            leftToRightMax[i] = currentMax;
        }

        currentMax = 0;
        for (int i = rightToLeftMax.length - 1; i >= 0; i--) {
            currentMax = Math.max(currentMax, height[i]);
            rightToLeftMax[i] = currentMax;
        }

        int maxWater = 0;
        for (int i = 0; i < height.length; i++) {
            int currentWaterTrap = Math.min(leftToRightMax[i], rightToLeftMax[i]) - height[i];

            if (currentWaterTrap > 0) {
                maxWater += currentWaterTrap;
            }
        }

        return maxWater;
    }
}
