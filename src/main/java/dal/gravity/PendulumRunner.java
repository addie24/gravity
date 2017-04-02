package dal.gravity;

import java.text.NumberFormat;

/** 
 * compares the values of a simple pendulum using the harmonic motion equation
 * versus the Euler algorithm approximation
 */
public class PendulumRunner {

    public static void main (String [] args) {
	NumberFormat nf = NumberFormat.getInstance ();
	nf.setMaximumFractionDigits (3);

	double delta = (args.length == 0) ? .1 : Double.parseDouble (args[0]);
	double sLen = 10, pMass = 10, theta0 = Math.PI/30;
	GravityConstant c1=new GravityConstant(AbstractPendulum.GRAVITY);
	GravityConstant c2=new GravityConstant(25);
	RegularPendulum rp = new RegularPendulum (sLen, pMass, theta0, delta,c1);
	SimplePendulum sp = new SimplePendulum (sLen, pMass, theta0,c1);
	RegularPendulum rpCoarse = 
	    new RegularPendulum (sLen, pMass, theta0, .1,c1);

	// print out difference in displacement in 1 second intervals
	// for 20 seconds
	int iterations = (int) (1/delta);
	System.out.println("For Earth");
	System.out.println ("analytical vs. numerical displacement (fine, coarse)");
	for (int second = 1; second <= 20; second++) {
	    for (int i = 0; i < iterations; i++) rp.step ();
	    for (int i = 0; i < 10; i++) rpCoarse.step (); 
	    System.out.println ("t=" + second + "s: \t" + 
				nf.format (Math.toDegrees (sp.getTheta (second))) 
				+ "\t" +
				nf.format (Math.toDegrees (rp.getLastTheta ()))
				+ "\t" + 
				nf.format (Math.toDegrees (rpCoarse.getLastTheta ())));
	}
	
	RegularPendulum rp2 = new RegularPendulum (sLen, pMass, theta0, delta,c2);
	SimplePendulum sp2 = new SimplePendulum (sLen, pMass, theta0,c2);
	RegularPendulum rpCoarse2 = 
	    new RegularPendulum (sLen, pMass, theta0, .1,c2);
	
	int iterations2 = (int) (1/delta);
	System.out.println("For Jupiter");
	System.out.println ("analytical vs. numerical displacement (fine, coarse)");
	for (int second = 1; second <= 20; second++) {
	    for (int i = 0; i < iterations2; i++) rp.step ();
	    for (int i = 0; i < 10; i++) rpCoarse2.step (); 
	    System.out.println ("t=" + second + "s: \t" + 
				nf.format (Math.toDegrees (sp2.getTheta (second))) 
				+ "\t" +
				nf.format (Math.toDegrees (rp2.getLastTheta ()))
				+ "\t" + 
				nf.format (Math.toDegrees (rpCoarse2.getLastTheta ())));
	}
    }
}

