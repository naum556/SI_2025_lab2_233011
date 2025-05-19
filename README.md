#Втора лабораториска вежба по Софтверско инженерство


#Наум Калајџиев, број на индекс - 233011

#Цикломатска комплексност
https://github.com/naum556/SI_2025_lab2_233011/blob/main/cfg.png

Цикломатската комплексност се добива така што ги одземаме бројот на рабови со бројот на нодови и додаваме 2
Цикломатска комплексност = Е-N+2 = 35-28+2 = 9

#Every Statement Criterium

https://github.com/naum556/SI_2025_lab2_233011/blob/main/Every%20Statement%20Criterium.PNG

За Every Statement Criterium потребни се минимално 5 test cases:
[] - Првиот тест случај го покрива првиот if услов односно првото флрање на exception каде листата со ставки е празна па заради тоа if условот кој проверува дали листата е празна станува true и се фрла exception што ја запира програмата да заврши со извршување и не се враќа sum односно чекор 23.
[Item("name",1,100,0.0)], cardNumber="1234" - Вториот тест случај е каде листата не е празна и има барем една ставка со име, квантитет,цена и попуст но има невалидна картичка. Овој тест случај ги покрива чекорите 3,4.1,4.2,4.3,5, 12 и 13 а ги скоскнува 8,9,10,11 бидејќи попустот е поставен на 0 а цената е помала од 300 и квантитетот е помал од 10, па затоа не исполнува ниту едно барање од тие чекори. Кога ќе стигниме до чекор 14 
се враќа false бидејќи должината на картичката не е еднаква на 16 па затоа директно одиме на 22 каде се фрла exception и се запира програмата и нема да се стигне до враќањето на sum.
Item(null,50,100,0.0), cardNumber=1...16 - Во третиот тест случај каде имаме квантитет поголем од 10, цената е исто помала од 300 и немаме попуст и исто така немаме име на ставката односно името е null па затоа иако ги скокнуваме чекор 1 и 2 па одиме на 3 па влегуваме во for - 4.1,4.2,5, во 6 добиваме true па се фрла exception и се запира програмата и другите чекори не се извршуваат и не се враќа sum, 4.3 не се извршува бидејќи for циклусот не добива можност повторно да се изврши односно да инкрементира индекс.
Item("item",50,400,0.2),CardNumber=1..16 - Во четвртиот тест случај имаме ставка со име, квантитет поголем од 10, цена поголема од 300 и попуст поголем од 0, чекор 1 и 2 не се извршуваат, 3 па влегуваме во for циклусот каде одиме 5,6 и 7 ги скокаме, тие не се извршуваат, сега во 8 бидејќи барем едно барање е true тогаш влегуваме во 9 па се извршува, истотака 10 е точно па влагуваме во 11 и се извршува. 14 е точно па одиме 15, 16 и влегуваме во for циклусот 17, целиот for се извршува до крај и исто така се извршува 18 а бидејќи форматот на картишката е точен тогаш 19,20 не се извршува , исто и 21 и 22 и на крај се враќа sum односно се извршува 23 и на крај се запира програмата.
Item("name",50,400,0.2)CardNumber=12345ABCD... - Овде исто како и во четвртиот тест пример ги поминуваме истите чекори освен кога ќе стигниме до 19, бидејќи форматот на картичката не е точен се враќа true и се фрла exception на 20 и се запира програмата.

#Multiple Conditions Criterium

https://github.com/naum556/SI_2025_lab2_233011/blob/main/Multiple%20Condition%20Criterium.PNG

За да се постигвне Multiple Conditions Criterium, потребно се минимално 3 случаи:
За првиот случај ако првото барање е true тогаш не е важно дали останатите се true или false.
За вториот случај ако второт барање е true не е важно дали останатите се труе или false.
Исто важи и за третиот случај само овде true е последното барање.
Доколу имаме барем едно барање што враќа true тогаш if статементот е исполнет.

#Објаснување на Unit тестовите

За Every Statement Method:

//1st case []
        RuntimeException exception = null;
        try{
            SILab2.checkCart(null, "1234");
        }catch (RuntimeException e){
            exception = e;
        }
        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("allItems list can't be null!"));

-Во овој тест имаме листа од ставки која е null и невалиден број на картичка, тестираме за случајот кога ќе внесиме празна листа без items и затоа правиме catch на exception каде проверуваме дали пораката на исклучокот која ќе ја добиеме е еднаква на "allItems list can't be null!" што потврдува дека е фрлено exception поради тоа што немаме внесено листа. Проверката за невалиден број на картичка не е потребен поради што програмата завршува кога ќе се фрли exception allItems list can't be null!.


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

        -Во овој тест случај имаме листа од валидна ставка и правиме try и catch на exception но сега поради невалиден број на картичка (должината на бројот не е еднаков на 16) исто проверуваме дали пораката на исклучокот е еднаква на пораката која ја добиваме кога ќе се фрли exception поради невалиден број на картичка.

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

      -Во овој тест случај имаме листа во која имаме ставка без име, односно името е null па исто правиме try и catch на exception и споредуваме дали пораката на исклучокот е еднаква со "Invalid item!", односно дали го добивме тој исклучок со таа порака.


      //4th case Item("item",50,400,0.2),CardNumber=1..16

        Item item = new Item("item",50,400,0.2);

        List<Item> items = List.of(item);

        assertNotNull(item.getName());
        assertTrue(item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10);

        double result = SILab2.checkCart(items, "1234567891234567");
        assertEquals(15970.0,result);

        -Во овој тест случај имаме листа со валидна ставка со цена поголема од 300, квантитет поголем од 10 и попуст поголем од 0 и валиден број на картичка, ставката го задоволува условот if(item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10) и истотака assertEquals(15970.0,result) проверува дали сумата ќе е еднаква на 15970 со пресметка на сумата која ја добиваме преку if условите if(item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10) и if (item.getDiscount() > 0) која враќа true, па формулата ќе биде -30 + (400 * 0.8 * 50) = -30 + 16000 = 15970. 

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

        -Во овој тест случај имаме валидна ставка но невалиден број на картичка, картичката има должина од 16 но е составена и од букви наместо само од цифри. Исто проворемуваме дали пораката на исклучокот кој го фаќаме е еднаква со пораката која ја добиваме кога ќе се фрли исклучокот и ке ја заврши програмата со извршување.


    За Multiple Conditions Criterium:
        
        // T X X
        Item item1 = new Item("name",1, 400, 0);

        List<Item> items1 = List.of(item1);

        double result1 = SILab2.checkCart(items1, "1234567891234567");

        assertTrue(item1.getPrice() > 300 || item1.getDiscount() > 0 || item1.getQuantity() > 10);

        assertEquals(370, result1);

        -За овој тест случај гледаме дека само првиот услов е точен па за другите услови не мора да проверуваме бицејќи доволно е само еден услов да е точен. Правиме assertEquals(370, result1);
        за да видиме дали ќе ја добиеме точната сума која ја враќа програмата.

        //F T X

        Item item2 = new Item("name",1, 200, 0.8);

        List<Item> items2 = List.of(item2);

        double result2 = SILab2.checkCart(items2, "1234567891234567");

        assertTrue(item1.getPrice() > 300 || item1.getDiscount() > 0 || item1.getQuantity() > 10);

        assertEquals(9.999999999999993, result2);

        -За овој тест случај гледаме дека првиот услов враќа false но вториот е враќа true па затоа не мора да гледаме за третиот услов. Исто така проверуваме дали сумата која ја враќа функцијата ќе е точна.

        //F F T

        Item item3 = new Item("name",20, 200, 0);

        List<Item> items3 = List.of(item3);

        double result3 = SILab2.checkCart(items3, "1234567891234567");

        assertTrue(item1.getPrice() > 300 || item1.getDiscount() > 0 || item1.getQuantity() > 10);

        assertEquals(3970, result3);

        Во овој тест случај првите два услови враќаат false но последниот враќа true па затоа на крај ќе испадне true. Исто така проверуваме дали сумата која ја враќа функцијата ќе е точна.
    

