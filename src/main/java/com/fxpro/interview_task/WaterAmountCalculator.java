package com.fxpro.interview_task;

import com.fxpro.interview_task.exception.AmountValidationException;

public class WaterAmountCalculator {

    private static final Integer LANDSCAPE_MAX_POSITION = 32000;

    private static final Integer LANDSCAPE_MIN_POSITION = 0;


    public long calculateAmount(int[] landscape) {
        validateLandscape(landscape);
        long collectedWatersAmount = 0;
        final int size = landscape.length;
        final int[] maxFromLeftToRight = new int[size];
        final int[] maxFromRightToLeft = new int[size];

        int maxFromLeft = 0;

        for (int i = 0; i < size; i++) {
            if (landscape[i] > maxFromLeft) {
                maxFromLeftToRight[i] = landscape[i];
                maxFromLeft = landscape[i];
            } else {
                maxFromLeftToRight[i] = maxFromLeft;
            }
        }

        int maxFromRight = 0;
        for (int i = size - 1; i >= 0; i--) {
            if (landscape[i] > maxFromRight) {
                maxFromRightToLeft[i] = landscape[i];
                maxFromRight = landscape[i];
            } else {
                maxFromRightToLeft[i] = maxFromRight;
            }
        }

        for (int i = 0; i < size; i++) {
            int waterLevel = Math.min(maxFromLeftToRight[i], maxFromRightToLeft[i]);
            if (landscape[i] < waterLevel) {
                collectedWatersAmount += waterLevel - landscape[i];
            }
        }
        return collectedWatersAmount;

    }


    private void validateLandscape(int[] landScape) {

        if (landScape == null || landScape.length == 0) {
            throw new AmountValidationException("Landscape is empty");
        }
        if (landScape.length > LANDSCAPE_MAX_POSITION) {
            throw new AmountValidationException("Landscape position numbers is more than expected");
        }
        for (int i : landScape) {

            if (i > LANDSCAPE_MAX_POSITION) {
                throw new AmountValidationException("Landscape height is greater than expected");
            }
            if (i < LANDSCAPE_MIN_POSITION) {
                throw new AmountValidationException("Landscape height is less than expected");
            }

        }

    }


}
