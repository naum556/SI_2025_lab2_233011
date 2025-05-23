import mk.ukim.finki.Item;
import mk.ukim.finki.SILab2;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {
    private final SILab2 lab2 = new SILab2();

    @Test
    void EveryStatementCriterium(){
        //1st case []
        RuntimeException exception = null;
        try{
            SILab2.checkCart(null, "1234");
        }catch (RuntimeException e){
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("allItems list can't be null!"));

        //2nd case [Item("name",1,100,0.0)], cardNumber="1234"


        RuntimeException exception1 = null;
        try{
            Item item = new Item("name", 1,100,0.0);

            List<Item> items = List.of(item);
            SILab2.checkCart(items, "1234");
        }catch (RuntimeException e) {
            exception1 = e;
        }
        assertNotNull(exception1);
        assertTrue(exception1.getMessage().contains("Invalid card number!"));


        //3rd case Item(null,50,100,0.0), cardNumber=1...16
        RuntimeException exception2 = null;
        try{
            Item item = new Item(null, 50,100,0.0);

            List<Item> items = List.of(item);

            SILab2.checkCart(items, "1234567891234567");
        }catch (RuntimeException e){
            exception2 = e;
        }
        assertNotNull(exception2);
        assertTrue(exception2.getMessage().contains("Invalid item!"));

        //4th case Item("item",50,400,0.2),CardNumber=1..16

        Item item = new Item("item",50,400,0.2);

        List<Item> items = List.of(item);

        assertNotNull(item.getName());
        assertTrue(item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10);

        double result = SILab2.checkCart(items, "1234567891234567");
        assertEquals(15970.0,result);

        //5th case Item("name",50,400,0.2)CardNumber=12345ABCD...
        RuntimeException exception3 = null;
        try{
            Item item1 = new Item("name",50,400,0.2);

            List<Item> items1 = List.of(item1);

            SILab2.checkCart(items1, "12345ABCDEFRTYUI");
        }catch (RuntimeException e){
            exception3 = e;
        }
        assertNotNull(exception3);
        assertTrue(exception3.getMessage().contains("Invalid character in card number!"));
    }

    @Test
    void MultipleConditionsCRiterium(){

        // T X X
        Item item1 = new Item("name",1, 400, 0);

        List<Item> items1 = List.of(item1);

        double result1 = SILab2.checkCart(items1, "1234567891234567");

        assertTrue(item1.getPrice() > 300 || item1.getDiscount() > 0 || item1.getQuantity() > 10);

        assertEquals(370, result1);

        //F T X

        Item item2 = new Item("name",1, 200, 0.8);

        List<Item> items2 = List.of(item2);

        double result2 = SILab2.checkCart(items2, "1234567891234567");

        assertTrue(item2.getPrice() > 300 || item2.getDiscount() > 0 || item2.getQuantity() > 10);

        assertEquals(9.999999999999993, result2);

        //F F T

        Item item3 = new Item("name",20, 200, 0);

        List<Item> items3 = List.of(item3);

        double result3 = SILab2.checkCart(items3, "1234567891234567");

        assertTrue(item3.getPrice() > 300 || item3.getDiscount() > 0 || item3.getQuantity() > 10);

        assertEquals(3970, result3);

        //F F F
        Item item4 = new Item("name",5, 200, 0);

        List<Item> items4 = List.of(item4);

        double result4 = SILab2.checkCart(items4, "1234567891234567");

        assertFalse(item4.getPrice() > 300 || item4.getDiscount() > 0 || item4.getQuantity() > 10);

        assertEquals(1000, result4);
    }
}
