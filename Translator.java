import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import java.util.Stack;
import java.util.Queue;
import java.util.PriorityQueue;

import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.TreeSet;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.TreeMap;
public class Translator
{
	private Map<String, ModStringArray> myLanguages;

	public Translator()
	{
		myLanguages = new HashMap<>();
		this.loadDataFromFile();
	}

	private void loadDataFromFile()
	{
		try
		{
			Scanner scan = new Scanner(new File("BabelFish_win.txt"));
			String line = scan.nextLine();
			String[] languages = line.split("\t");
			for(int i = 0; i < languages.length; i++)
			{
				ModStringArray a = new ModStringArray(new String[1000], 1000);
				myLanguages.put(languages[i], a);
			}
			for(int j = 0; j < 1000; j++)
			{
				String l = scan.nextLine();
				String[] ln = l.split("\t");
				for(int i = 0; i < languages.length; i++)
				{
					ModStringArray ar = myLanguages.get(languages[i]);
					String[] w = ar.getArray();
					w[j] = ln[i];
					ar.setArrays(w);
					myLanguages.put(languages[i], ar);
				}
			}

			scan.close();
		}
		catch(Exception e)
		{
			System.out.println("File Not Found. No Data Loaded");
			e.printStackTrace();
		}
	}

	public String ignoreSpecialCharacters(String word)
	{
		String[] specChars = new String[4];
		specChars[0] = ".";
		specChars[1] = "?";
		specChars[2] = ",";
		specChars[3] = "!";
		ModStringArray c = new ModStringArray(specChars, 4);
		for(int i = 0; i < c.getArray().length; i++)
		{
			if(word.contains(c.getArray()[i]))
			{
				int index = word.indexOf(c.getArray()[i]);
				String n = word.substring(0, index) + word.substring(index+1);
				word = n;
			}
		}
		return word;
	}

	public String translate(String language1, String language2, String text)
	{
		if(myLanguages.keySet().contains(language1) && myLanguages.keySet().contains(language2))
		{
			String s = "";
			String[] t = text.split(" ");

			ModStringArray a = myLanguages.get(language1);
			String[] w = a.getArray();
			for(int i = 0; i < t.length; i++)
			{
				t[i] = this.ignoreSpecialCharacters(t[i]);
				if(a.contains(w, t[i]))
				{
					int index = a.indexOf(w, t[i]);
					String word = myLanguages.get(language2).getArray()[index];
					t[i] = word;
				}
				s = s + t[i] + " ";
			}
			return s;
		}
		else
			return "We do not provide translation for this language.";
	}

	public String[] getLanguages()
	{
		String s = "";
		Set<String> lang = new TreeSet<>();
		for(Entry<String, ModStringArray> entry: myLanguages.entrySet())
		{
			String l = entry.getKey();
			lang.add(l);
		}
		for(String l: lang)
		{
			s = s + l + " ";
		}
		String[] allLanguages = s.split(" ");
		return allLanguages;
	}

	public String displayAllLanguages()
	{
		String s = "";
		for(Entry<String, ModStringArray> entry: myLanguages.entrySet())
		{
			s = s + entry.getKey() + "\n";
		}
		return s;
	}
	
	public void exitProgram()
	{
		System.exit(0);
	}


/*--------------------------------Tester code--------------------------------*/
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		Translator t = new Translator();
		//t.loadDataFromFile();
		//System.out.println(t.translate("English", "Spanish", "I eat cake"));
		int choice = 0;
		String language1 = "English";
		String language2 = "Spanish";
		String text = "";
		while(!(choice == 6))
		{
			t.loadDataFromFile();
			System.out.println(language1 + ": " + text +"\t" + language2 + ":"				);
			System.out.println("1. Change Language 1");
			System.out.println("2. Change Language 2");
			System.out.println("3. Switch Language");
			System.out.println("4. Enter text");
			System.out.println("5. Translate");
			System.out.println("6. Exit");
			System.out.println("Enter choice: ");

			choice = scan.nextInt();
			if(choice == 1)
			{
				System.out.println("Choose from the following");
				System.out.println(t.displayAllLanguages());
				scan.nextLine();
				language1 = scan.nextLine();
			}
			if(choice == 2)
			{
				System.out.println("Choose from the following");
				System.out.println(t.displayAllLanguages());
				scan.nextLine();
				language2 = scan.nextLine();
			}
			if(choice == 3)
			{
				String temp = language1;
				language1 = language2;
				language2 = temp;
			}
			if(choice == 4)
			{
				System.out.println("Enter text you want to translate");
				scan.nextLine();
				text = scan.nextLine();
			}
			if(choice == 5)
			{
				System.out.println(t.translate(language1, language2, text));
			}
			if(choice == 6)
			{
				t.exitProgram();
			}
		}

	}
}
