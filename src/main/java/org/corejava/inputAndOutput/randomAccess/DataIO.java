package org.corejava.inputAndOutput.randomAccess;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DataIO
{
    public static void writeFixedString(String s, int size, DataOutput out)
            throws IOException
    {
        for (int i = 0; i < size; i++)
        {
            char ch = 0;
            if (i < s.length()) ch = s.charAt(i);
            out.writeChar(ch);
        }
    }
    public static String readFixedString(int size, DataInput in)
        throws IOException
    {
        var b = new StringBuilder(size);
        int i = 0;
        var done = false;
        while (!done && i < size)
        {
            char ch = in.readChar();
            i++;
            if (ch == 0) done = true;
            else b.append(ch);
        }



        



        in.skipBytes(2 * (size - i));

        return b.toString();
    }
}
