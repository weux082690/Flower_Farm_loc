import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class FlowerForest {
	private static final String[] FLOWERS = {"dandelion", "poppy", "allium", "azure bluet", "red tulip", "orange tulip", "white tulip", "pink tulip", "oxeye daisy", "cornflower", "lily of the valley"};

   	public static double clamp(double p_14009_, double p_14010_, double p_14011_) {
      		if (p_14009_ < p_14010_) {
         		return p_14010_;
      		} else {
         		return p_14009_ > p_14011_ ? p_14011_ : p_14009_;
      		}
   	}

	public String getState(int x, int z) {
		double d0 = clamp((1.0D + SimplexNoise.noise((double)x / 48.0D, (double)z / 48.0D)) / 2.0D, 0.0D, 0.9999D);
		return FLOWERS[(int)(d0 * (double)FLOWERS.length)];
	}

	public static void main(String args[]) {
		ArrayList<String> flowers_to_match = new ArrayList<>(List.of("allium", "red tulip", "orange tulip", "white tulip", "pink tulip"));
		FlowerForest flowerForest = new FlowerForest();
		System.out.printf("x,z\n");
		for (int x = -9999; x <= 9999; ++x) {
			for (int z = -9999; z <= 9999; ++z) {
				ArrayList<String> farmed_flowers = new ArrayList<>();
				Boolean flag = true;

				for (int xShift = -3; xShift <= 3; ++xShift) {
					for (int zShift = -3; zShift <= 3; ++ zShift) {
						farmed_flowers.add(flowerForest.getState(x + xShift, z + zShift));
					}
				}

				for (String flower : flowers_to_match) {
					if (!farmed_flowers.contains(flower)) {
						flag = false;
					}
				}

				if (flag) {
					System.out.printf("%d,%d\n", x, z);
				}
			}
		}
	}
}
