package org.corejava.database.exec;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

import static java.lang.System.out;


public class ExecSQL
{
	public static void main(String[] args) throws IOException {
		try (Scanner in = args.length == 0 ? new Scanner(System.in)
				: new Scanner(Paths.get(args[0]), StandardCharsets.UTF_8))
		{
			try (Connection conn = getConnection();
				 Statement stat = conn.createStatement())
			{
				while(true)
				{
					if(args.length == 0) out.println("Enter command or EXIT to exit:");

					if(!in.hasNextLine()) return;

					String line = in.nextLine().trim();
					if(line.equalsIgnoreCase("EXIT")) return;
					if (line.endsWith(";")) line = line.substring(0, line.length() -1);
					try
					{
						boolean isResult = stat.execute(line);
						if (isResult)
						{
							try (ResultSet rs = stat.getResultSet())
							{
								showResultSet(rs);
							}
						} else {
							int updateCount = stat.getUpdateCount();
							out.println(updateCount + " rows updated");
						}
					} catch (SQLException e) {
						for (Throwable t : e)
							t.printStackTrace();
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static Connection getConnection() throws SQLException, IOException
	{
		var props = new Properties();
		try (InputStream in = Files.newInputStream(Paths.get("database.properties")))
		{
			props.load(in);
		}
		String url = props.getProperty("jdbc.url");
		String username = props.getProperty("jdbc.username");
		String password = props.getProperty("jdbc.password");

		return DriverManager.getConnection(url, username, password);
	}

	public static void showResultSet(ResultSet result) throws SQLException
	{
		ResultSetMetaData metaData = result.getMetaData();
		int columnCount = metaData.getColumnCount();

		for (int i = 1; i <= columnCount; i++)
		{
			if (i > 1) out.println(", ");
			out.println(metaData.getColumnLabel(i));
		}
		out.println();

		while (result.next())
		{
			for (int i = 1; i <= columnCount; i++)
			{
				if (i > 1) out.print(", ");
				out.print(result.getString(i));
			}
		}
		out.println();
	}
}
