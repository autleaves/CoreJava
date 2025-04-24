package org.corejava.internationalization.numberFormat;

import java.util.Locale;

import static java.lang.System.out;

public class Test
{
	public static void main(String[] args)
	{
		var loc = new Locale("zh", "CN");
		out.println(loc.getDisplayName());
		out.println(loc.getDisplayName(Locale.CHINA));
	}
}
