import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

public class CalculatorTest {

    @Test
    public void test_Empty() throws Exception {
        Calculator calculator = new Calculator();
        Assert.assertTrue(calculator.add("") == 0 );
    }

    @Test
    public void test_One_Number() throws Exception {
        Calculator calculator = new Calculator();
        int expected = 1;
        int actual  = calculator.add("1");
        Assert.assertTrue(expected == actual );
    }

    @Test
    public void test_Two_Number() throws Exception {
        Calculator calculator = new Calculator();
        int expected = 3;
        int actual  = calculator.add("1,2");
        Assert.assertTrue(expected == actual );
    }

    @Test
    public void test_Five_Number() throws Exception {
        Calculator calculator = new Calculator();
        int expected = 15;
        int actual  = calculator.add("1,2,3,4,5");
        Assert.assertTrue(expected == actual );
    }

    @Test
    public void test_NewLine_Number() throws Exception {
        Calculator calculator = new Calculator();
        int expected = 6;
        int actual  = calculator.add("1\n2,3");
        Assert.assertTrue(expected == actual );
    }

    @Test
    public void test_Change_Delimiter_SlashSlash_At_Front() throws Exception {
        Calculator calculator = new Calculator();
        int expected = 3;
        int actual  = calculator.add("//;\n1;2");
        Assert.assertTrue(expected == actual );
    }

    @Test
    public void test_Exception_When_One_Negative_Value(){
        Calculator calculator = new Calculator();
        String expected = "negatives not allowed: -1";
        try{
            calculator.add("//;\n-1;2");
            fail();
        }catch (Exception e){
            Assert.assertEquals(expected, e.getMessage() );
        }
    }

    @Test
    public void test_Exception_When_Two_Negative_Value(){
        Calculator calculator = new Calculator();
        String expected = "negatives not allowed: -1 -2";
        try{
            calculator.add("//;\n-1;-2;3");
            fail();
        }catch (Exception e){
            Assert.assertEquals(expected, e.getMessage() );
        }
    }

    @Test
    public void test_Exception_When_Three_Negative_Value(){
        Calculator calculator = new Calculator();
        String expected = "negatives not allowed: -1 -3";
        try{
            calculator.add("1,-1,-3,2");
            fail();
        }catch (Exception e){
            Assert.assertEquals(expected, e.getMessage() );
        }
    }

    @Test
    public void test_Ignore_Larger_Than_1000() throws Exception {
        Calculator calculator = new Calculator();
        int expected = 2;
        int actual  = calculator.add("2,1001");
        Assert.assertTrue(expected == actual );
    }

    @Test
    public void test_Delimiter_Length_Three() throws Exception {
        Calculator calculator = new Calculator();
        int expected = 6;
        int actual  = calculator.add("//***\n1***2***3");
        Assert.assertTrue(expected == actual );
    }

    @Test
    public void test_Allow_Mulitple_Delimiter() throws Exception {
        Calculator calculator = new Calculator();
        int expected = 6;
        int actual  = calculator.add("//*|%\n1*2%3");
        Assert.assertTrue(expected == actual );
    }

    @Test
    public void test_Allow_Mulitple_With_4_Characters_Delimiter() throws Exception {
        Calculator calculator = new Calculator();
        int expected = 6;
        int actual  = calculator.add("//****|++++\n1****2++++3");
        Assert.assertTrue(expected == actual );
    }

}
