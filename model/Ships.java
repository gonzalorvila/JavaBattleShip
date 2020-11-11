package model;

import java.util.ArrayList;
import java.util.*;

public class Ships 
{
	private String shipName;
	private int length;
	private int location;

 	public void storeLocations(ArrayList<Integer> locationsArray, int[] location)
    {
        for(int i = 0; i < location.length; i++) {
			locationsArray.add(location[i]);
		}
    }

}
