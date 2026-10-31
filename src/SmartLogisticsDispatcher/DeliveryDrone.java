package SmartLogisticsDispatcher;

/* 
 Build a system where a single vehicle class (DeliveryDrone) inherits capabilities from two distinct interfaces.
 */
public class DeliveryDrone implements ElectricPowered, AutonomousNav {
	String droneId;
	double batteryLevel;

	public DeliveryDrone(String droneId, double batteryLevel) {
		this.droneId = droneId;
		this.batteryLevel = Math.max(0.0, Math.min(100.0, batteryLevel));
	}

	@Override
	public void chargeBattery(double percentage) {
		if (percentage > 0) {
			this.batteryLevel = Math.min(100.0, this.batteryLevel + percentage);
		}
		System.out.println("Updated Battery level: " + batteryLevel);
	}

	@Override
	public void navigateTo(String destination) {
		if (batteryLevel >= 15.0) {
			System.out.println("Drone [" + droneId + "] flying to " + destination);
			batteryLevel -= 15.0;
		} else {
			System.out.println("Flight Aborted: Insufficient battery (" + batteryLevel + "%). Needs recharge.");
		}
	}

	@Override
	public void showPowerStatus() {
		ElectricPowered.super.showPowerStatus();
		AutonomousNav.super.showPowerStatus();

		System.out.println("Drone Diagnostics: Power distribution nominal.");
	}

	public static void main(String[] args) throws InterruptedException {
		DeliveryDrone drone = new DeliveryDrone("DR-101", 20.0);
		drone.showPowerStatus();
		Thread.sleep(2000);
		System.out.println("------------------------------");
		drone.navigateTo("Warehouse Hub B");
		Thread.sleep(2000);
		System.out.println("------------------------------");
		drone.navigateTo("Customer Zone 4");
		Thread.sleep(2000);
		System.out.println("------------------------------");
		drone.chargeBattery(50);
		drone.navigateTo("Customer Zone 4");
	}
}