package PracticeSet;

class Shipment {
	String trackingId;
	double weightKg;

	public Shipment(String trackingId, double weightKg) {
		this.trackingId = trackingId;
		if (weightKg <= 0) {
			System.out.println("Weight should be greater than 0");
			this.weightKg = 1.0;
		} else {
			this.weightKg = weightKg;
		}
		System.out.println("L1 Superclass : Shipment [" + trackingId + "] initialized");
	}

	double getBaseCost() {
		return weightKg * 50;
	}
}

class ExpressShipment extends Shipment {
	int priorityLevel;

	public ExpressShipment(String trackingId, double weightKg, int priorityLevel) {
		super(trackingId, weightKg);
		if (priorityLevel < 1 || priorityLevel > 3) {
			this.priorityLevel = 1; // default
		} else {
			this.priorityLevel = priorityLevel;
		}
		System.out.println("L2 Intermediate: Priority level " + priorityLevel + " attached");
	}

	double getPrioritySurcharge() {
		return priorityLevel * 150;
	}

}

class InsuredExpressShipment extends ExpressShipment {
	double declaredValue;
	double insuranceRate;

	public InsuredExpressShipment(String trackingId, double weightKg, int priorityLevel, double declaredValue,
			double insuranceRate) {
		super(trackingId, weightKg, priorityLevel);
		if (declaredValue > 0 && insuranceRate >= 0) {
			this.declaredValue = declaredValue;
			this.insuranceRate = insuranceRate;
			System.out.println("L3 Leaf: Insurance locked for value ₹" + declaredValue);
		} else {
			this.declaredValue = Math.max(0.0, declaredValue);
			this.insuranceRate = Math.max(0.0, insuranceRate);
		}
	}

	InsuredExpressShipment(String trackingId, double weightKg, int priorityLevel, double declaredValue) {
		this(trackingId, weightKg, priorityLevel, declaredValue, 2.0);
	}

	double getInsuranceFee() {
		double insuranceFee = declaredValue * (insuranceRate / 100.0);
		return insuranceFee;
	}

	double getTotalFinalCost() {
		return getBaseCost() + getPrioritySurcharge() + getInsuranceFee();
	}

	void printBillSummary() {
		System.out.println("------------------------------");
		System.out.println("Tracking Id: " + trackingId);
		System.out.println("Weight: " + weightKg);
		System.out.println("Priority: " + priorityLevel);
		System.out.println("Declared Value: " + declaredValue);
		System.out.println("==============================");
		System.out.println("Base Cost: ₹" + getBaseCost());
		System.out.println("Surcharge: ₹" + getPrioritySurcharge());
		System.out.println("Insurance Fee: ₹" + getInsuranceFee());
		System.out.println("==============================");
		System.out.println("Final Total: ₹" + getTotalFinalCost());
		System.out.println("------------------------------");
	}
}

public class Logistics_Freight_System {
	public static void main(String[] args) {
		InsuredExpressShipment s1 = new InsuredExpressShipment("EXP-901", 12.5, 3, 40000.0, 3.0);

		s1.printBillSummary();

		System.out.println();

		InsuredExpressShipment s2 = new InsuredExpressShipment("EXP-902", 15, 2, 35000.0);
		s2.printBillSummary();

	}
}
