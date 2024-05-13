package test;


import org.junit.Test;

import calc.CalculatorFace;

public class TestCalcBasic extends TestCalcAbs {

    static final String PM = Character.toString(CalculatorFace.PLUS_MINUS);

    @Test
    public void singleDigit() {
        for (int i = 0; i < 10; i++)
            testSequence(Integer.toString(i), new String[] {Integer.toString(i), Double.toString(i)});
    }
    
    @Test
    public void multipleDigits() {
        String tests[] = new String[] {"12", "34", "56", "78", "90", "147", "258", "369", "3690", "29384756"};
        for (String s : tests)
            testSequence(s, new String[] {s, s + ".0"});
    }
    
    @Test
    public void decimal() {
        testSequence("12.7", new String[] {"12.7"});
        testSequence("1.7182", new String[] {"1.7182"});
        testSequence("3211.7", new String[] {"3211.7"});
    }
    
    @Test
    public void decimalZeros() {
        testSequence("12.07", new String[] {"12.07"});
        testSequence("12.007", new String[] {"12.007"});
        testSequence("12.1007", new String[] {"12.1007"});
    }

    @Test
    public void decimalStart() {
        testSequence(".5", new String[] {"0.5", ".5"});
        testSequence(".25", new String[] {"0.25", ".25"});
        testSequence(".205", new String[] {"0.205", ".205"});
    }

    @Test
    public void decimalStartZeros() {
        testSequence(".0501", new String[] {"0.0501", ".0501"});
        testSequence(".00005", new String[] {"0.00005", ".00005"});
    }

    @Test
    public void decimalRepeat() {
        testSequence("13..5", new String[] {"13.5"});
        testSequence("13...5", new String[] {"13.5"});
        testSequence("13.5.", new String[] {"13.5"});
        testSequence("13.5.8", new String[] {"13.58"});
    }

    @Test
    public void decimalRepeatStart() {
        testSequence("..5", new String[] {"0.5", ".5"});
        testSequence(".5.2", new String[] {"0.52", ".52"});
    }

    @Test
    public void negativePre() {
        testSequence(PM + "15", new String[] {"-15", "-15.0"});
    }
    
    @Test
    public void negativeMid() {
        testSequence("1" + PM + "5", new String[] {"-15", "-15.0"});
    }

    @Test
    public void negativePost() {
        testSequence("15" + PM, new String[] {"-15", "-15.0"});
    }

    @Test
    public void negativeTwice() {
        testSequence("1" + PM + "5" + PM, new String[] {"15", "15.0"});
    }

    @Test
    public void immediatelyAfterOperator() {
        testSequence("2218/", new String[] {"2218", "2218.0"});
        testSequence("4050-", new String[] {"4050", "4050.0"});
        testSequence("7+", new String[] {"7", "7.0"});
    }

    @Test
    public void addEq() {
        testSequence("10+21=", new String[] {"31", "31.0"});
        testSequence("13+28+", new String[] {"41", "41.0"});
    }

    @Test
    public void decimalRepeatPlus() {
        testSequence("13.2.5+1.25=", new String[] {"14.5"});
        testSequence("13.2.5+1.25=", new String[] {"14.5"});
    }

    @Test
    public void decimalRepeatPlusTwice() {
        testSequence("13.2.5+1.2.5=", new String[] {"14.5"});
        testSequence("13.2.5+1.2.5*", new String[] {"14.5"});
    }

    @Test
    public void decimalPlusMinus() {
        testSequence("1" + PM + ".5", new String[] {"-1.5"});
        testSequence("1" + PM + ".5" + PM, new String[] {"1.5"});
        testSequence("1." + PM + "5", new String[] {"-1.5"});
        testSequence("1." + PM + "5" + PM, new String[] {"1.5"});
        testSequence("1.0" + PM + "5", new String[] {"-1.05"});
        testSequence("1.0" + PM + "5" + PM, new String[] {"1.05"});
    }

    @Test
    public void subEq() {
        testSequence("17-9=", new String[] {"8", "8.0"});
        testSequence("17-8+", new String[] {"9", "9.0"});
    }

    @Test
    public void subNeg() {
        testSequence("3-12=", new String[] {"-9", "-9.0"});
        testSequence("3-12-", new String[] {"-9", "-9.0"});
    }

    @Test
    public void subNegTimes() {
        testSequence("3-12*4=", new String[] {"-36", "-36.0"});
        testSequence("3-12*4+", new String[] {"-36", "-36.0"});
    }

    @Test
    public void subNegTimesNeg() {
        testSequence("3-12*4" + PM + "=", new String[] {"36", "36.0"});
        testSequence("3-12*4" + PM + "-", new String[] {"36", "36.0"});
    }

    @Test
    public void timesEq() {
        testSequence("7*2=", new String[] {"14", "14.0"});
        testSequence("7*22-", new String[] {"154", "154.0"});
    }

    @Test
    public void divEq() {
        testSequence("8888/88=", new String[] {"101", "101.0"});
        testSequence("8888/88/", new String[] {"101", "101.0"});
    }
    
    @Test
    public void addThreeNotFinished() {
        testSequence("1+2+4", new String[] {"4", "4.0"});
    }
    
    @Test
    public void addThreeEq() {
        testSequence("10+27+40=", new String[] {"77", "77.0"});
        testSequence("10+27+40+", new String[] {"77", "77.0"});
    }

    @Test
    public void divThreeEq() {
        testSequence("8888/88/2=", new String[] {"50.5"});
        testSequence("8888/88/2/", new String[] {"50.5"});
    }

    @Test
    public void clear() {
        testSequence("1+5C4+9=", new String[] {"13", "13.0"});
        testSequence("1+5C4+9*", new String[] {"13", "13.0"});
    }

    @Test
    public void clearNeg() {
        testSequence("28" + PM + "C3+5=", new String[] {"8", "8.0"});
        testSequence("28" + PM + "C3+5+", new String[] {"8", "8.0"});
    }

    @Test
    public void clearThenNeg() {
        testSequence("28" + PM + "C3" + PM + "+5=", new String[] {"2", "2.0"});
        testSequence("28" + PM + "C3" + PM + "+5+", new String[] {"2", "2.0"});
    }

    @Test
    public void clearThenDecimal() {
        testSequence("3.5C2.8", new String[] {"2.8"});
    }

    @Test
    public void overwriteResult() {
        testSequence("2+8=3", new String[] {"3", "3.0"});
    }

    @Test
    public void overwriteResultDecimal() {
        testSequence("2+8.1=3.7", new String[] {"3.7"});
    }

    @Test
    public void twoCalculations() {
        testSequence("2+8=3*13=", new String[] {"39", "39.0"});
    }

    @Test
    public void chainAfterEquals() {
        testSequence("3*4=+2=", new String[] {"14", "14.0"});
        testSequence("3*4=+2/", new String[] {"14", "14.0"});
    }

    @Test
    public void dontCrash() {
        // These are examples of sequences whose result is not specified by the
        // assignment, but your program should not crash!
        String[] noCrashes = new String[] {
            "=",
            "==",
            "+=",
            "*+",
            "*+=",
            "3+=",
            "3-+=",
            "3+3===",
            "+3=",
            "***",
            PM + "=",
            PM + PM + PM,
            "5/0=",
            "0/0=",
            "3+8/0-",
            "5*==",
            "CC",
            "=C=C=",
            ".=",
            ".*",
        };
        for (String s : noCrashes)
            testSequence(s, null);
    }
}
