package test.harenet;

import static org.junit.Assert.*;

import org.junit.Test;
import harenet.BitArray;

public class BitArrayTest {
    /**
    * Purpose: Check BitArray's data having size 0 
    * Input: input 0 to constructor
    * Expected:
    * BitArray's data length == 0
    */
    @Test
    public void testConstructorZero() {
        BitArray bitArray = new BitArray(0);
        assertEquals(bitArray.getData().length,0);
    }
    
    /**
    * Purpose: Check BitArray' data having negative size 
    * Input: input -1 to constructor
    * Expected:
    * NegativeArraySizeException occurs
    */    
    @Test(expected=NegativeArraySizeException.class)
    public void testConstructorNegative() throws Exception {
        BitArray bitArray = new BitArray(-1);
    }
    /**
     * birArray is size 0 Array ===> it's wrong!!!
     */
    
    /**
    * Purpose: Check BitArray' data having size 1 
    * Input: input 1 to constructor
    * Expected:
    * BitArray's data length == 1
    */    
    @Test
    public void testConstructorPositive1() {
        BitArray bitArray = new BitArray(1);
        assertEquals(bitArray.getData().length,1);
    }
    
    /**
    * Purpose: Check BitArray's data having word size(8) 
    * Input: input 8 to constructor
    * Expected:
    * BitArray's data length == 1
    */    
    @Test
    public void testConstructorPositive8() {
        BitArray bitArray = new BitArray(8);
        assertEquals(bitArray.getData().length,1);
    }

    /**
    * Purpose: Check BitArray's data having size over 8(word size) 
    * Input: input 9 to constructor
    * Expected:
    * BitArray's data length == 2
    */    
    @Test
    public void testConstructorPositive9() {
        BitArray bitArray = new BitArray(9);
        assertEquals(bitArray.getData().length,2);
    }
    
    /**
    * Purpose: Check BitArray's data having int max size
    * Input: input  to constructor
    * Expected:
    * Integer.MAX_VALUE = 2147483647
    * 2147483647/8 + 1 = 268435456
    * BitArray's data length == 268435456
    */    
    @Test
    public void testConstructorPositiveMax() {
        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        assertEquals(bitArray.getData().length,268435456);
    }
    /**
    * Purpose: Test setBit for 0.
    * Input: setBit => 0
    * Expected:
    * data[0] == 0b1 == 1
    */    
    @Test
    public void testSetBitZero() {
        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        bitArray.setBit(0);
        assertEquals(bitArray.getData()[0],1);
    }
    
    /**
    * Purpose: Test setBit for Positive(1).
    * Input: setBit => 1
    * Expected:
    * data[0] == 0b10 == 2
    */    
    @Test
    public void testSetBit1() {
        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        bitArray.setBit(1);
        assertEquals(bitArray.getData()[0],2);
    }
    
    /**
    * Purpose: Test setBit for Positive(2).
    * Input: setBit => 2
    * Expected:
    * data[0] == 0b100 == 4
    */    
    @Test
    public void testSetBit2() {
        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        bitArray.setBit(2);
        assertEquals(bitArray.getData()[0],4);
    }
    
    /**
    * Purpose: Test setBit for Positive(3).
    * Input: setBit => 3
    * Expected:
    * data[0] == 0b1000 == 8
    */    
    @Test
    public void testSetBit3() {
        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        bitArray.setBit(3);
        assertEquals(bitArray.getData()[0],8);
    }
    
    /**
    * Purpose: Test setBit for Positive(4).
    * Input: setBit => 4
    * Expected:
    * data[0] == 0b10000 == 16
    */    
    @Test
    public void testSetBit4() {
        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        bitArray.setBit(4);
        assertEquals(bitArray.getData()[0],16);
    }
    
    /**
    * Purpose: Test setBit for Positive(5).
    * Input: setBit => 5
    * Expected:
    * data[0] == 0b100000 == 32
    */    
    @Test
    public void testSetBit5() {
        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        bitArray.setBit(5);
        assertEquals(bitArray.getData()[0],32);
    }
    
    /**
    * Purpose: Test setBit for Positive(6).
    * Input: setBit => 6
    * Expected:
    * data[0] == 0b100 0000 == 64
    */    
    @Test
    public void testSetBit6() {
        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        bitArray.setBit(6);
        assertEquals(bitArray.getData()[0],64);
    }
    
    /**
    * Purpose: Test setBit for Positive(7).
    * Input: setBit => 7
    * Expected:
    * data[0] == 0b1000 0000 == -128 (because data[0] is byte.)
    */    
    @Test
    public void testSetBit7() {
        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        bitArray.setBit(7);
        assertEquals(bitArray.getData()[0],-128);
    }
    
    /**
    * Purpose: Test setBit for Positive(8).
    * Input: setBit => 8
    * Expected:
    * data[1] == 0b1 == 1
    * data[0] == 0b00000000 == 0
    */    
    @Test
    public void testSetBit8() {
        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        bitArray.setBit(8);
        assertEquals(bitArray.getData()[1],1);
        assertEquals(bitArray.getData()[0],0);
    }
    
    /**
    * Purpose: Test setBit for Zero in true branch.
    * Input: setBit => (0,true)
    * Expected:
    * data[0] == 0b1 == 1
    */    
    @Test
    public void testSetBitTrueZero() {
        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        bitArray.setBit(0,true);
        assertEquals(bitArray.getData()[0],1);
    }
    
    /**
    * Purpose: Test setBit for Positive(1) in true branch.
    * Input: setBit => (1,true)
    * Expected:
    * data[0] == 0b10 == 2
    */    
    @Test
    public void testSetBitTrue1() {
        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        bitArray.setBit(1);
        assertEquals(bitArray.getData()[0],2);
    }
    
    /**
    * Purpose: Test setBit for Positive(2) in true branch.
    * Input: setBit => (2,true)
    * Expected:
    * data[0] == 0b100 == 4
    */    
    @Test
    public void testSetBitTrue2() {
        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        bitArray.setBit(2);
        assertEquals(bitArray.getData()[0],4);
    }
    
    /**
    * Purpose: Test setBit for Positive(3) in true branch.
    * Input: setBit => (3,true)
    * Expected:
    * data[0] == 0b1000 == 8
    */    
    @Test
    public void testSetBitTrue3() {
        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        bitArray.setBit(3);
        assertEquals(bitArray.getData()[0],8);
    }
    
    /**
    * Purpose: Test setBit for Positive(4) in true branch.
    * Input: setBit => (4,true)
    * Expected:
    * data[0] == 0b10000 == 16
    */    
    @Test
    public void testSetBitTrue4() {
        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        bitArray.setBit(4);
        assertEquals(bitArray.getData()[0],16);
    }
    
    /**
    * Purpose: Test setBit for Positive(5) in true branch.
    * Input: setBit => (5,true)
    * Expected:
    * data[0] == 0b100000 == 32
    */    
    @Test
    public void testSetBitTrue5() {
        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        bitArray.setBit(5);
        assertEquals(bitArray.getData()[0],32);
    }
    
    /**
    * Purpose: Test setBit for Positive(6) in true branch.
    * Input: setBit => (6,true)
    * Expected:
    * data[0] == 0b100 0000 == 64
    */    
    @Test
    public void testSetBitTrue6() {
        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        bitArray.setBit(6);
        assertEquals(bitArray.getData()[0],64);
    }
    
    /**
    * Purpose: Test setBit for Positive(7) in true branch.
    * Input: setBit => (7,true)
    * Expected:
    * data[0] == 0b1000 0000 == -128 (because data[0] is byte.)
    */    
    @Test
    public void testSetBitTrue7() {
        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        bitArray.setBit(7);
        assertEquals(bitArray.getData()[0],-128);
    }
    
    /**
    * Purpose: Test setBit for Positive(8) in true branch.
    * Input: setBit => (8,true)
    * Expected:
    * data[1] == 0b1 == 1
    * data[0] == 0b00000000 == 0
    */    
    @Test
    public void testSetBitTrue8() {
        BitArray bitArray = new BitArray(Integer.MAX_VALUE);
        bitArray.setBit(8);
        assertEquals(bitArray.getData()[1],1);
        assertEquals(bitArray.getData()[0],0);
    }
    
    /**
    * Purpose: Test setBit for Zero in false branch.
    * Input: setBit => (0,false)
    * Expected:
    * data[0] == 0b1111 1110 => -0b0000 0010 ==  -2
    */    
    @Test
    public void testSetBitFalseZero() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0);
        bitArray.setBit(1);
        bitArray.setBit(2);
        bitArray.setBit(3);
        bitArray.setBit(4);
        bitArray.setBit(5);
        bitArray.setBit(6);
        bitArray.setBit(7);
        //data[0] = 0b1111 1111
        
        bitArray.setBit(0,false);
        assertEquals(bitArray.getData()[0],-2);
    }
    
    /**
    * Purpose: Test setBit for Positive(1) in false branch.
    * Input: setBit => (1,false)
    * Expected:
    * data[0] == 0b1111 1101 => -0b0000 0011 == -3
    */    
    @Test
    public void testSetBitFalse1() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0);
        bitArray.setBit(1);
        bitArray.setBit(2);
        bitArray.setBit(3);
        bitArray.setBit(4);
        bitArray.setBit(5);
        bitArray.setBit(6);
        bitArray.setBit(7);
        //data[0] = 0b1111 1111
        bitArray.setBit(1,false);
        
        assertEquals(bitArray.getData()[0],-3);
    }
    
    /**
    * Purpose: Test setBit for Positive(2) in false branch.
    * Input: setBit => (2,false)
    * Expected:
    * data[0] == 0b1111 1011 => -0b0000 0101 == -5
    */    
    @Test
    public void testSetBitFalse2() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0);
        bitArray.setBit(1);
        bitArray.setBit(2);
        bitArray.setBit(3);
        bitArray.setBit(4);
        bitArray.setBit(5);
        bitArray.setBit(6);
        bitArray.setBit(7);
        //data[0] = 0b1111 1111
        
        bitArray.setBit(2,false);
        assertEquals(bitArray.getData()[0],-5);
    }
    
    /**
    * Purpose: Test setBit for Positive(3) in false branch.
    * Input: setBit => (3,false)
    * Expected:
    * data[0] == 0b1111 0111 => -0b0000 1001 == -9
    */    
    @Test
    public void testSetBitFalse3() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0);
        bitArray.setBit(1);
        bitArray.setBit(2);
        bitArray.setBit(3);
        bitArray.setBit(4);
        bitArray.setBit(5);
        bitArray.setBit(6);
        bitArray.setBit(7);
        //data[0] = 0b1111 1111
        
        bitArray.setBit(3,false);
        assertEquals(bitArray.getData()[0],-9);
    }
    
    /**
    * Purpose: Test setBit for Positive(4) in false branch.
    * Input: setBit => (4,false)
    * Expected:
    * data[0] == 0b1110 1111 => -0b0001 0001 == -17
    */    
    @Test
    public void testSetBitFalse4() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0);
        bitArray.setBit(1);
        bitArray.setBit(2);
        bitArray.setBit(3);
        bitArray.setBit(4);
        bitArray.setBit(5);
        bitArray.setBit(6);
        bitArray.setBit(7);
        //data[0] = 0b1111 1111
        
        bitArray.setBit(4,false);
        assertEquals(bitArray.getData()[0],-17);
    }
    
    /**
    * Purpose: Test setBit for Positive(5) in false branch.
    * Input: setBit => (5,false)
    * Expected:
    * data[0] == 0b1101 1111 => -0b0010 0001 == -33
    */    
    @Test
    public void testSetBitFalse5() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0);
        bitArray.setBit(1);
        bitArray.setBit(2);
        bitArray.setBit(3);
        bitArray.setBit(4);
        bitArray.setBit(5);
        bitArray.setBit(6);
        bitArray.setBit(7);
        //data[0] = 0b1111 1111
        
        bitArray.setBit(5,false);
        assertEquals(bitArray.getData()[0],-33);
    }
    
    /**
    * Purpose: Test setBit for Positive(6) in false branch.
    * Input: setBit => (6,true)
    * Expected:
    * data[0] == 0b1011 1111 => -0b0100 0001 == -65
    */    
    @Test
    public void testSetBitFalse6() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0);
        bitArray.setBit(1);
        bitArray.setBit(2);
        bitArray.setBit(3);
        bitArray.setBit(4);
        bitArray.setBit(5);
        bitArray.setBit(6);
        bitArray.setBit(7);
        //data[0] = 0b1111 1111
        
        bitArray.setBit(6,false);
        assertEquals(bitArray.getData()[0],-65);
    }
    
    /**
    * Purpose: Test setBit for Positive(7) in false branch.
    * Input: setBit => (7,false)
    * Expected:
    * data[0] == 0b0111 1111 => 127
    */    
    @Test
    public void testSetBitFalse7() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0);
        bitArray.setBit(1);
        bitArray.setBit(2);
        bitArray.setBit(3);
        bitArray.setBit(4);
        bitArray.setBit(5);
        bitArray.setBit(6);
        bitArray.setBit(7);
        //data[0] = 0b1111 1111
        
        bitArray.setBit(7,false);
        assertEquals(bitArray.getData()[0],127);
    }
    
    /**
    * Purpose: Test setBit for Positive(8) in false branch.
    * Input: setBit => (8,false)
    * Expected:
    * data[1] == 0b0000 0000 == 0
    * data[0] == 0b1111 1111 => -0b0000 0001 == -1
    */    
    @Test
    public void testSetBitFalse8() {
        BitArray bitArray = new BitArray(9);
        bitArray.setBit(0);
        bitArray.setBit(1);
        bitArray.setBit(2);
        bitArray.setBit(3);
        bitArray.setBit(4);
        bitArray.setBit(5);
        bitArray.setBit(6);
        bitArray.setBit(7);
        bitArray.setBit(8);
        //data[1] = 0b0000 0001
        //data[0] = 0b1111 1111
        
        bitArray.setBit(8,false);
        assertEquals(bitArray.getData()[1],0);
        assertEquals(bitArray.getData()[0],-1);
    }
    
    /**
    * Purpose: Test clear for 1 byte.
    * Input: data[0] => 0b1010 0101 
    * Expected:
    * data[0] == 0b0000 0000
    */    
    @Test
    public void testClear1() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0);
        bitArray.setBit(2);
        bitArray.setBit(5);
        bitArray.setBit(7);
        //data[0] == 0b1010 0101
        
        bitArray.clear();
        assertEquals(bitArray.getData()[0],0);
    }
    
    /**
    * Purpose: Test clear for 2 byte.
    * Input: data[1] => 0b1010 0101 data[0] => 0b1111 1111
    * Expected:
    * data[1] == 0b0000 0000
    * data[0] == 0b0000 0000
    */    
    @Test
    public void testClear2() {
        BitArray bitArray = new BitArray(9);
        bitArray.setBit(0);
        bitArray.setBit(1);
        bitArray.setBit(2);
        bitArray.setBit(3);
        bitArray.setBit(4);
        bitArray.setBit(5);
        bitArray.setBit(6);
        bitArray.setBit(7);
        bitArray.setBit(8);
        bitArray.setBit(10);
        bitArray.setBit(13);
        bitArray.setBit(15);
        //data[1] == 0b1010 0101
        //data[0] == 0b1111 1111
        
        bitArray.clear();
        assertEquals(bitArray.getData()[1],0);
        assertEquals(bitArray.getData()[0],0);
    }
    
    /**
    * Purpose: Test setAll in case all bits are 0.
    * Input: data[0] => 0b0000 0000
    * Expected:
    * data[0] == 0b1111 1111 => -0b0000 0001 == -1
    */    
    @Test
    public void testSetAllZero() {
        BitArray bitArray = new BitArray(8);
        //data[0] == 0b0000 0000
        
        bitArray.setAll();
        assertEquals(bitArray.getData()[0],-1);
    }
    
    /**
    * Purpose: Test setAll for 1 byte.
    * Input: data[0] => 0b1010 0101
    * Expected:
    * data[0] == 0b1111 1111 => -0b0000 0001 == -1
    */    
    @Test
    public void testSetAll1() {
        BitArray bitArray = new BitArray(9);
        bitArray.setBit(0);
        bitArray.setBit(2);
        bitArray.setBit(5);
        bitArray.setBit(7);
        //data[0] == 0b1010 0101
        
        bitArray.setAll();
        assertEquals(bitArray.getData()[0],-1);
    }
    
    /**
    * Purpose: Test setAll for 2 byte.
    * Input: data[1] => 0b1010 0101 data[0] => 0b0000 0000
    * Expected:
    * data[1] == 0b1111 1111 => -0b0000 0001 == -1
    * data[0] == 0b1111 1111 => -0b0000 0001 == -1
    */    
    @Test
    public void testSetAll2() {
        BitArray bitArray = new BitArray(9);
        bitArray.setBit(8);
        bitArray.setBit(10);
        bitArray.setBit(13);
        bitArray.setBit(15);
        //data[1] == 1010 0101
        //data[0] == 0b0000 0000
        
        bitArray.setAll();
        assertEquals(bitArray.getData()[1],-1);
        assertEquals(bitArray.getData()[0],-1);
    }
    
    /**
    * Purpose: Test whether getBit returns false for bit 0.
    * Input: getBit => 0
    * Expected:
    * data[0] == 0b1111 1110
    * 0 != 0 => false
    */    
    @Test
    public void testGetBitZero0() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0,false);
        bitArray.setBit(1,true);
        bitArray.setBit(2,true);
        bitArray.setBit(3,true);
        bitArray.setBit(4,true);
        bitArray.setBit(5,true);
        bitArray.setBit(6,true);
        bitArray.setBit(7,true);
        //data[0] == 0b1111 1110
                
        assertEquals(bitArray.getBit(0),false);
    }
    
    /**
    * Purpose: Test whether  getBit returns false for bit 1.
    * Input: getBit => 1
    * Expected:
    * data[0] == 0b1111 1101
    * 0 != 0 => false
    */    
    @Test
    public void testGetBitZero1() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0,true);
        bitArray.setBit(1,false);
        bitArray.setBit(2,true);
        bitArray.setBit(3,true);
        bitArray.setBit(4,true);
        bitArray.setBit(5,true);
        bitArray.setBit(6,true);
        bitArray.setBit(7,true);
        //data[0] == 0b1111 1101
                
        assertEquals(bitArray.getBit(1),false);
    }
    
    /**
    * Purpose: Test whether getBit returns false for bit 2.
    * Input: getBit => 2
    * Expected:
    * data[0] == 0b1111 1011
    * 0 != 0 => false
    */    
    @Test
    public void testGetBitZero2() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0,true);
        bitArray.setBit(1,true);
        bitArray.setBit(2,false);
        bitArray.setBit(3,true);
        bitArray.setBit(4,true);
        bitArray.setBit(5,true);
        bitArray.setBit(6,true);
        bitArray.setBit(7,true);
        //data[0] == 0b1111 1011
                
        assertEquals(bitArray.getBit(2),false);
    }
    
    /**
    * Purpose: Test whether getBit returns false for bit 3.
    * Input: getBit => 3
    * Expected:
    * data[0] == 0b1111 0111
    * 0 != 0 => false
    */    
    @Test
    public void testGetBitZero3() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0,true);
        bitArray.setBit(1,true);
        bitArray.setBit(2,true);
        bitArray.setBit(3,false);
        bitArray.setBit(4,true);
        bitArray.setBit(5,true);
        bitArray.setBit(6,true);
        bitArray.setBit(7,true);
        //data[0] == 0b1111 0111
                
        assertEquals(bitArray.getBit(3),false);
    }
    
    /**
    * Purpose: Test whether getBit returns false for bit 4.
    * Input: getBit => 4
    * Expected:
    * data[0] == 0b1110 1111
    * 0 != 0 => false
    */    
    @Test
    public void testGetBitZero4() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0,true);
        bitArray.setBit(1,true);
        bitArray.setBit(2,true);
        bitArray.setBit(3,true);
        bitArray.setBit(4,false);
        bitArray.setBit(5,true);
        bitArray.setBit(6,true);
        bitArray.setBit(7,true);
        //data[0] == 0b1110 1111
                
        assertEquals(bitArray.getBit(4),false);
    }
    
    /**
    * Purpose: Test whether getBit returns false for bit 5.
    * Input: getBit => 5
    * Expected:
    * data[0] == 0b1101 1111
    * 0 != 0 => false
    */    
    @Test
    public void testGetBitZero5() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0,true);
        bitArray.setBit(1,true);
        bitArray.setBit(2,true);
        bitArray.setBit(3,true);
        bitArray.setBit(4,true);
        bitArray.setBit(5,false);
        bitArray.setBit(6,true);
        bitArray.setBit(7,true);
        //data[0] == 0b1101 1111
                
        assertEquals(bitArray.getBit(5),false);
    }
    
    /**
    * Purpose: Test whether getBit returns false for bit 6.
    * Input: getBit => 6
    * Expected:
    * data[0] == 0b1011 1111
    * 0 != 0 => false
    */    
    @Test
    public void testGetBitZero6() {
        BitArray bitArray = new BitArray(8); 
        bitArray.setBit(0,true);
        bitArray.setBit(1,true);
        bitArray.setBit(2,true);
        bitArray.setBit(3,true);
        bitArray.setBit(4,true);
        bitArray.setBit(5,true);
        bitArray.setBit(6,false);
        bitArray.setBit(7,true);
        //data[0] == 0b0000 0000
                
        assertEquals(bitArray.getBit(6),false);
    }
    
    /**
    * Purpose: Test whether getBit returns false for bit 7.
    * Input: getBit => 7
    * Expected:
    * data[0] == 0b0111 1111
    * 0 != 0 => false
    */    
    @Test
    public void testGetBitZero7() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0,true);
        bitArray.setBit(1,true);
        bitArray.setBit(2,true);
        bitArray.setBit(3,true);
        bitArray.setBit(4,true);
        bitArray.setBit(5,true);
        bitArray.setBit(6,true);
        bitArray.setBit(7,false);
        //data[0] == 0b0000 0000
                
        assertEquals(bitArray.getBit(7),false);
    }
    
    /**
    * Purpose: Test whether getBit returns false for bit 8.
    * Input: getBit => 8
    * Expected:
    * data[1] == 0b0000 0000
    * data[0] == 0b1111 1111
    * 0 != 0 => false
    */    
    @Test
    public void testGetBitZero8() {
        BitArray bitArray = new BitArray(9);
        bitArray.setBit(0,true);
        bitArray.setBit(1,true);
        bitArray.setBit(2,true);
        bitArray.setBit(3,true);
        bitArray.setBit(4,true);
        bitArray.setBit(5,true);
        bitArray.setBit(6,true);
        bitArray.setBit(7,true);
        bitArray.setBit(8,false);
        //data[1] == 0b0000 0000
        //data[0] == 0b1111 1111
                
        assertEquals(bitArray.getBit(8),false);
    }
    
    /**
    * Purpose: Test whether getBit returns true for bit 0.
    * Input: getBit => 0
    * Expected:
    * data[0] == 0b0000 0001
    * 1 != 0 => true
    */    
    @Test
    public void testGetBitOne0() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0,true);
        bitArray.setBit(1,false);
        bitArray.setBit(2,false);
        bitArray.setBit(3,false);
        bitArray.setBit(4,false);
        bitArray.setBit(5,false);
        bitArray.setBit(6,false);
        bitArray.setBit(7,false);
        //data[0] == 0b0000 0001
                
        assertEquals(bitArray.getBit(0),true);
    }
    
    /**
    * Purpose: Test whether getBit returns true for bit 1.
    * Input: getBit => 1
    * Expected:
    * data[0] == 0b0000 0010
    * 1 != 0 => true
    */    
    @Test
    public void testGetBitOne1() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0,false);
        bitArray.setBit(1,true);
        bitArray.setBit(2,false);
        bitArray.setBit(3,false);
        bitArray.setBit(4,false);
        bitArray.setBit(5,false);
        bitArray.setBit(6,false);
        bitArray.setBit(7,false);
        //data[0] == 0b0000 0010
                
        assertEquals(bitArray.getBit(1),true);
    }
    
    /**
    * Purpose: Test whether getBit returns true for bit 2.
    * Input: getBit => 2
    * Expected:
    * data[0] == 0b0000 0100
    * 1 != 0 => true
    */    
    @Test
    public void testGetBitOne2() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0,false);
        bitArray.setBit(1,false);
        bitArray.setBit(2,true);
        bitArray.setBit(3,false);
        bitArray.setBit(4,false);
        bitArray.setBit(5,false);
        bitArray.setBit(6,false);
        bitArray.setBit(7,false);
        //data[0] == 0b0000 0100
                
        assertEquals(bitArray.getBit(2),true);
    }
    
    /**
    * Purpose: Test whether getBit returns true for bit 3.
    * Input: getBit => 3
    * Expected:
    * data[0] == 0b0000 1000
    * 1 != 0 => true
    */    
    @Test
    public void testGetBitOne3() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0,false);
        bitArray.setBit(1,false);
        bitArray.setBit(2,false);
        bitArray.setBit(3,true);
        bitArray.setBit(4,false);
        bitArray.setBit(5,false);
        bitArray.setBit(6,false);
        bitArray.setBit(7,false);
        //data[0] == 0b0000 1000
                
        assertEquals(bitArray.getBit(3),true);
    }
    
    /**
    * Purpose: Test whether getBit returns true for bit 4.
    * Input: getBit => 4
    * Expected:
    * data[0] == 0b0001 0000
    * 1 != 0 => true
    */    
    @Test
    public void testGetBitOne4() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0,false);
        bitArray.setBit(1,false);
        bitArray.setBit(2,false);
        bitArray.setBit(3,false);
        bitArray.setBit(4,true);
        bitArray.setBit(5,false);
        bitArray.setBit(6,false);
        bitArray.setBit(7,false);
        //data[0] == 0b0001 0000
                
        assertEquals(bitArray.getBit(4),true);
    }
    
    /**
    * Purpose: Test whether getBit returns true for bit 5.
    * Input: getBit => 5
    * Expected:
    * data[0] == 0b0010 0000
    * 1 != 0 => true
    */    
    @Test
    public void testGetBitOne5() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0,false);
        bitArray.setBit(1,false);
        bitArray.setBit(2,false);
        bitArray.setBit(3,false);
        bitArray.setBit(4,false);
        bitArray.setBit(5,true);
        bitArray.setBit(6,false);
        bitArray.setBit(7,false);
        //data[0] == 0b0010 0000
                
        assertEquals(bitArray.getBit(5),true);
    }
    
    /**
    * Purpose: Test whether getBit returns true for bit 6.
    * Input: getBit => 6
    * Expected:
    * data[0] == 0b0001 0000
    * 1 != 0 => true
    */    
    @Test
    public void testGetBitOne6() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0,false);
        bitArray.setBit(1,false);
        bitArray.setBit(2,false);
        bitArray.setBit(3,false);
        bitArray.setBit(4,false);
        bitArray.setBit(5,false);
        bitArray.setBit(6,true);
        bitArray.setBit(7,false);
        //data[0] == 0b0100 0000
                
        assertEquals(bitArray.getBit(6),true);
    }
    
    /**
    * Purpose: Test whether getBit returns true for bit 6.
    * Input: getBit => 6
    * Expected:
    * data[0] == 0b1000 0000
    * 1 != 0 => true
    */    
    @Test
    public void testGetBitOne7() {
        BitArray bitArray = new BitArray(8);
        bitArray.setBit(0,false);
        bitArray.setBit(1,false);
        bitArray.setBit(2,false);
        bitArray.setBit(3,false);
        bitArray.setBit(4,false);
        bitArray.setBit(5,false);
        bitArray.setBit(6,false);
        bitArray.setBit(7,true);
        //data[0] == 0b1000 0000
                
        assertEquals(bitArray.getBit(7),true);
    }
    
    /**
    * Purpose: Test whether getBit returns true for bit 8.
    * Input: getBit => 8
    * Expected:
    * data[1] == 0b0000 0001
    * data[0] == 0b0000 0000
    * 1 != 0 => true
    */    
    @Test
    public void testGetBitOne8() {
        BitArray bitArray = new BitArray(9);
        bitArray.setBit(0,false);
        bitArray.setBit(1,false);
        bitArray.setBit(2,false);
        bitArray.setBit(3,false);
        bitArray.setBit(4,false);
        bitArray.setBit(5,false);
        bitArray.setBit(6,false);
        bitArray.setBit(7,false);
        bitArray.setBit(8,true);
        //data[1] == 0b0000 0001
        //data[0] == 0b0000 0000
                
        assertEquals(bitArray.getBit(8),true);
    }
}
