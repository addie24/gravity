package dal.gravity;

public class GravityConstant implements GravityModel {
	private double g;
	
	

	public GravityConstant(double inG) {
		this.g=inG;
	}

	@Override
	public double getGravitationalField() {
		return g;
	}

}
