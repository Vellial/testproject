package trainee.allure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.qatools.allure.annotations.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assume.assumeThat;

/**
 * Created by Vellial on 28.08.2017.
 */
@RunWith(Parameterized.class)
@Title("Arithmetic test suite. +, -, *, /")
public class arithmeticTest {

    public int op1;
    public int op2;
    public String operation;
    public int result;

    public arithmeticTest(int op1, int op2, String operation, int result) {
        this.op1 = op1;
        this.op2 = op2;
        this.operation = operation;
        this.result = result;
    }

    @Title("Check addition operation")
    @Description("In this test we will check addition operation")
    @Test
    public void additionTest() {
        assumeThat(operation, is("+"));
        checkResult(getAdditionResult(op1, op2, operation), result);
    }

    @Title("Check substraction operation")
    @Description("In this test we will check substraction operation")
    @Test
    public void substractionTest() {
        assumeThat(operation, is("-"));
        checkResult(getSubstractionResult(op1, op2, operation), result);
    }

    @Title("Check division operation")
    @Description("In this test we will check division operation")
    @Test
    public void divisionTest() {
        assumeThat(operation, is("/"));
        checkResult(getDivisionResult(op1, op2, operation), result);
    }

    @Title("Check multiplication operation")
    @Description("In this test we will check multiplication operation")
    @Test
    public void multiplicationTest() {
        assumeThat(operation, is("*"));
        checkResult(getMultiplicationResult(op1, op2, operation), result);
    }

    @Step("Check results. Expected Result = {1}")
    private void checkResult(int actualResult, int expectedResult) {
        assertTrue("Actual result(" + actualResult + ") not equals to expected(" + expectedResult + ")", actualResult == expectedResult);
    }

    @Step("Getting result of division: {0} {2} {1}")
    private int getDivisionResult(int firstInt, int secondInt, String operation) {
        checkNotZero(secondInt);
        return firstInt / secondInt;
    }

    @Step("Check divider equals 0: {0}")
    private void checkNotZero(int intValue) {
        assertTrue("Divider equals 0", intValue != 0);
    }

    @Step("Getting result of addition: {0} {2} {1}")
    private int getAdditionResult(int firstInt, int secondInt, String operation) {
        return firstInt + secondInt;
    }

    @Step("Getting result of substraction: {0} {2} {1}")
    private int getSubstractionResult(int firstInt, int secondInt, String operation) {
        return firstInt - secondInt;
    }

    @Step("Getting result of multiplication: {0} {2} {1}")
    private int getMultiplicationResult(int firstInt, int secondInt, String operation) {
        return firstInt * secondInt;
    }

    @Parameterized.Parameters
    public static List<String[]> getTestData() {
        String[][] data = new String[4][4];
        try {
            ClassLoader classLoader = arithmeticTest.class.getClassLoader();
            File file = new File(classLoader.getResource("data").getFile());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            List<String> lines = new ArrayList<String>();
            int i = 0;
            while ((line = reader.readLine()) != null) {
                String[] params = line.split(";");
                for (int j = 0; j < params.length; j++) {
//                    System.out.println(Arrays.toString(params));
                    String op1 = params[j];
                    data[i][j] = op1;
                }
                lines.add(line);
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Arrays.asList(data);
    }


    // Get input data from file for tests.
    private String getStrFromFile(int numberOfLine) {
        String[] linesAsArray = new String[4];
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("data").getFile());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            List<String> lines = new ArrayList<String>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            linesAsArray = lines.toArray(new String[lines.size()]);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return linesAsArray[numberOfLine];
    }

}
