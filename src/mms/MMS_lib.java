package mms;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MMS_lib {
	double lambda, mu, row, Q, L, W, U;
	int s;
	int point = 10;

	public MMS_lib(double lambda, double mu, int s) {
		this.lambda = lambda;
		this.mu = mu;
		this.s = s;
	}
	//階乗計算
	private static BigDecimal kaijou(BigDecimal n){
        if(n.equals(BigDecimal.ONE)){
            return BigDecimal.ONE;
        }        
        return n.multiply(kaijou(n.subtract(BigDecimal.ONE)));
    }
	
	//p0を求める関数
	//引数 a, row,でp0を返す
	public  BigDecimal functionP0(double a, double row){
		BigDecimal answer,p0,pow,temp = new BigDecimal("0.00");

		for(int k=0; k<=s-1; k++){
			if( k > 0)
				answer = kaijou(new BigDecimal(String.valueOf(k)));
			else answer = new BigDecimal("1.00");
			System.out.println(k+"回目");
			pow = new BigDecimal("1.00");
			for(int n = 0; n < k ; n++) pow = pow.multiply(BigDecimal.valueOf(a));
			System.out.println("pow="+pow);
			System.out.println("answer="+answer);
			temp = temp.add(pow.divide(answer, point, BigDecimal.ROUND_HALF_UP));
			System.out.println("temp="+temp);
		}
		answer = kaijou(new BigDecimal(String.valueOf(s)));
		pow = new BigDecimal("1.000");
		for(int n = 0; n < s; n++) pow = pow.multiply(BigDecimal.valueOf(a));
		BigDecimal deno = BigDecimal.valueOf(1.0-row);
		temp = temp.add( pow.divide(answer.multiply(deno),point, BigDecimal.ROUND_HALF_UP));
		BigDecimal one = new BigDecimal("1.00");
		p0 = one.divide(temp,7, BigDecimal.ROUND_HALF_UP);

		return p0;
	}

	//計算用エンジン関数
	public  void functionMMs(){
		double a;
		BigDecimal p0;

		a = lambda/mu;
		row = a/(double) s;

		p0 = functionP0(a, row);
		//待ち人数
		BigDecimal first = new BigDecimal("1.000");
		for(int n = 0; n < s; n++) first = first.multiply(BigDecimal.valueOf(s));
		BigDecimal second = new BigDecimal("1.000");
		for(int n = 0; n < s + 1; n++) second = second.multiply(BigDecimal.valueOf(row));
		BigDecimal answer = kaijou(new BigDecimal(String.valueOf(s)));
		BigDecimal third = BigDecimal.valueOf(Math.pow(1.0-row,2.0));
		Q = first.multiply(second).divide(answer.multiply(third),point, BigDecimal.ROUND_HALF_UP).multiply(p0).doubleValue();
		L = Q + a;			//系内人数
		W = Q / lambda;		//待ち時間
		U = W + (1/mu); 	//系内時間
	}
	//各解を求める関数
	//利用率：ρ
	public double getrow() {
		return row;
	}
	//Q：待ち人数
	public double getQ() {
		return Q;
	}
	//L：系内人数
	public double getL() {
		return L;
	}
	//W：待ち時間
	public double getW() {
		return W;
	}
	//U:系内時間
	public double getU() {
		return U;
	}


}
