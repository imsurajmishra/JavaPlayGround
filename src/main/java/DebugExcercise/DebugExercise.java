package DebugExcercise;

import org.junit.Assert;

public class DebugExercise {

    public static void main(String[] args) {
        String name = "world";

        if(args.length!=0){
            name = args[0];
        }

        System.out.println("Hello "+name);

        int a = 1+4;

        Assert.assertEquals(a, 5);
    }
}
