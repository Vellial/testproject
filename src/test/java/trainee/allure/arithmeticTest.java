package trainee.allure;

import org.junit.Test;
import ru.yandex.qatools.allure.annotations.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Vellial on 28.08.2017.
 */
@Title("Arithmetic test suite. +, -, *, /")
public class arithmeticTest {

    @Title("Check addition operation")
    @Description("In this test we will check addition operation")
    @Test
    public void additionTest() {
        String additionOperators = getStrFromFile(0);
        String[] params = additionOperators.split(";");
        Integer op1 = Integer.valueOf(params[0]);
        Integer op2 = Integer.valueOf(params[1]);
        String operation = params[2];
        Integer result = Integer.valueOf(params[3]);
        checkResult(getAdditionResult(op1, op2, operation), result);
    }

    @Title("Check substraction operation")
    @Description("In this test we will check substraction operation")
    @Test
    public void substractionTest() {
        String substractionOperators = getStrFromFile(1);
        String[] params = substractionOperators.split(";");
        Integer op1 = Integer.valueOf(params[0]);
        Integer op2 = Integer.valueOf(params[1]);
        String operation = params[2];
        Integer result = Integer.valueOf(params[3]);
        checkResult(getSubstractionResult(op1, op2, operation), result);
    }

    @Title("Check division operation")
    @Description("In this test we will check division operation")
    @Test
    public void divisionTest() {
        String divisionOperators = getStrFromFile(3);
        String[] params = divisionOperators.split(";");
        Integer op1 = Integer.valueOf(params[0]);
        Integer op2 = Integer.valueOf(params[1]);
        String operation = params[2];
        Integer result = Integer.valueOf(params[3]);
        checkResult(getDivisionResult(op1, op2, operation), result);
    }

    @Title("Check multiplication operation")
    @Description("In this test we will check multiplication operation")
    @Test
    public void multiplicationTest() {
        String multiplicationOperators = getStrFromFile(2);
        String[] params = multiplicationOperators.split(";");
        Integer op1 = Integer.valueOf(params[0]);
        Integer op2 = Integer.valueOf(params[1]);
        String operation = params[2];
        Integer result = Integer.valueOf(params[3]);
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
