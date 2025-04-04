package org.corejava.objectAnalyzer;

import java.util.ArrayList;
import static java.lang.System.out;

public class ObjectAnalyzerTest {

    public static void main(String[] args)
        throws ReflectiveOperationException
    {
        ArrayList<Integer> squares = new ArrayList<>();
        for(int i=1; i <= 5; i++){
            squares.add(i * i);
        }
        out.println(new ObjectAnalyzer().toString(squares));
    }
}
