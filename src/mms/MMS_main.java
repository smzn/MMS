package mms;

import java.util.Scanner;

public class MMS_main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double lambda,mu;
		int s;
		Scanner stdin = new Scanner(System.in);


		System.out.print("Input_(double)lambda:");
		lambda = stdin.nextDouble();
		System.out.print("Input_(double)mu    :");
		mu	   = stdin.nextDouble();
		System.out.print("Input_(int)s        :");
		s	   = stdin.nextInt();

		MMS_lib mms = new MMS_lib(lambda, mu, s);
		mms.functionMMs();

		System.out.println("");
		System.out.println("row利用率 =" + mms.getrow());
		System.out.println("Q   =" + mms.getQ());
		System.out.println("L   =" + mms.getL());
		System.out.println("W   =" + mms.getW());
		System.out.println("U   =" + mms.getU());


	}

}
