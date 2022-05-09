package edu.princeton.cs.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TurnOverStrClass {
    @Test
    public void turnOverStrTest(){
        char[] str = new String("I'm  a student.").toCharArray();
        exchangeEntireStr(str);
        Assert.assertEquals(new String(str), "student. a  I'm");
        str = new String("I'm  a good  student.").toCharArray();
        exchangeEntireStr(str);
        Assert.assertEquals(new String(str), "student.  good a  I'm");
    }

    private void swap(char[] chas, int i, int j){
        char t = chas[i];chas[i] = chas[j];chas[j] = t;
    }

    private void exchangeStr(char[] chas, int start, int end){
        int i = start;int j = end;
        while(j > i){
            swap(chas, j, i);
            j--; i++;
        }
    }

    private int resetI(char[] chas, int i) {
        i--;
        while(i < chas.length - 1 && chas[++i] == ' ') continue;
        return i;
    }

    private int resetJ(char[] chas, int j) {
        while (j < chas.length - 1 && chas[j++] == ' ');
        j--;
        while(j < chas.length - 1 && chas[++j] != ' ') ;
        return j == chas.length - 1 ? j+1 : j;
    }

    private void exchangeEntireStr(char[] chas){
        exchangeStr(chas, 0, chas.length - 1);
        int ipointer = 0; //encounter non-whitespace char
        int jPointer = resetJ(chas, 0);//encounter whitespace
        exchangeStr(chas, ipointer, jPointer -1 );
        while (jPointer != chas.length ){
            ipointer = resetI(chas, jPointer);
            jPointer = resetJ(chas, jPointer);
            exchangeStr(chas, ipointer, jPointer - 1);
        }
    }
}
