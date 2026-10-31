package SmartLogisticsDispatcher;

public interface AutonomousNav {
	abstract void navigateTo(String destination);

	default void showPowerStatus() {
		System.out.println("Navigation System: Route computer drawing auxiliary power.");
	}
}