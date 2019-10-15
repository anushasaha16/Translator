public class ModStringArray 
{
	private String[] arr;
	private int length;
	
	public ModStringArray(String[] a, int l)
	{
		arr = a;
		length = l;
	}
	
	public String[] getArray()
	{
		return arr;
	}
	
	public void setArrays(String[] a)
	{
		arr = a;
	}
	
	public boolean contains(String[] arr, String word)
	{
		for (String element : arr) 
		{
		    if (element.equalsIgnoreCase(word)) 
		    {
		        return true;
		    }
		}
		return false;
	}
	
	public int indexOf(String[] arr, String word)
	{
		for (int i = 0; i < arr.length; i++) 
		{
		    if (arr[i].equalsIgnoreCase(word)) 
		    {
		    	return i;
		    }
		}
		return -1;
	}
	
	
	
	
	
}