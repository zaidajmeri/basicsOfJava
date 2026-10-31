package SmartLogisticsDispatcher;
public interface ElectricPowered {
	abstract void chargeBattery(double percentage);

	default void showPowerStatus() {
		System.out.println("Electric System: Battery operating under standard efficiency curved");
	}
}