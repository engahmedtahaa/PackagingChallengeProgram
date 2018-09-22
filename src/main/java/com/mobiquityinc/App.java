package com.mobiquityinc;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Packer;


/**
 * For testing!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
     
        	try {
				System.out.println(Packer.pack(System.getProperty("user.dir") + "/testcases.txt"));
			} catch (APIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
}
