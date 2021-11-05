import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Plains {
	private static final String[] HIGH_NOISE_FLOWERS = {"poppy", "azure bluet", "oxeye daisy", "cornflower"};
	private static final String[] LOW_NOISE_FLOWERS = {"orange tulip", "red tulip", "pink tulip", "white tulip"};

	public String getState(int x, int z) {
		double d0 = SimplexNoise.noise(((double)x / 200.0D)*1.0D, ((double)z / 200.0D)*1.0D)*1.0D;
		if (d0 < -0.8D) {
			return "low noise";
		} else {
			return "high noise";
		}
	}

	public static void main(String args[]) {
		Plains plains = new Plains();
		System.out.printf("x,z\n");
		for (int x = -9999; x <= 9999; ++x) {
			for (int z = -9999; z <= 9999; ++z) {
				ArrayList<String> farmed_flowers = new ArrayList<>();
				Boolean flag = false;

				for (int xShift = -3; xShift <= 3; ++xShift) {
					for (int zShift = -3; zShift <= 3; ++ zShift) {
						farmed_flowers.add(plains.getState(x + xShift, z + zShift));
					}
				}

				// By probability calculations, these values mean at least 79.7% chance of containing all the flowers
				if (Collections.frequency(farmed_flowers, "low noise") == 20) {
					if (Collections.frequency(farmed_flowers, "high noise") == 29) {
						flag = true;
					}
				}

				if (flag) {
					System.out.printf("%d,%d\n", x, z);
				}
			}
		}
	}
}
