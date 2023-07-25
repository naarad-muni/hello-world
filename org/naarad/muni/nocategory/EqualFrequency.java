package org.naarad.muni.nocategory;

// TODO: finish code
public class EqualFrequency {
    public static void main(String[] args) {
        EqualFrequency frequency = new EqualFrequency();
        System.out.println(frequency.equalFrequency("abc"));
    }

    public boolean equalFrequency(String word) {
        if (word.length() == 2) {
            return true;
        }

        //int[] uniqueFrequencies
        int onceAppearingFrequency = 0;
        int onceAppearingFreqLength = 0;
        boolean onceAppearingVariableInitialised = false;
        int restAppearingFrequency = 0;
        int restAppearingFreqLength = 0;
        boolean restAppearingVariableInitialised = false;
        int[] frequency = new int[26];

        for (int i = 0; i < word.length(); i++) {
            frequency['A' - word.charAt(i)]++;
        }

        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] != 0) {
                if (!onceAppearingVariableInitialised) {
                    onceAppearingFrequency++;
                    onceAppearingFreqLength = frequency[i];
                    onceAppearingVariableInitialised = true;
                } else if (!restAppearingVariableInitialised) {
                    restAppearingFrequency++;
                    restAppearingFreqLength = frequency[i];
                    restAppearingVariableInitialised = true;
                } else if (onceAppearingFreqLength == frequency[i]) {
                    onceAppearingFrequency++;
                } else if (restAppearingFreqLength == frequency[i]) {
                    restAppearingFrequency++;
                } else {
                    return false;
                }
            }
        }

        return onceAppearingVariableInitialised && restAppearingVariableInitialised &&
                onceAppearingFreqLength - restAppearingFreqLength == 1 &&
                Math.abs(onceAppearingFrequency - restAppearingFrequency) == 1;
    }
}
