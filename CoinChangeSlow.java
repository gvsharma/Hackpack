import java.util.ArrayList;
import java.util.Collections;

public class CoinChangeSlow {

	static int denoms, find;
	static int[] coins, have;
	static boolean[][] memo;
	
	static ArrayList<pile> ways = new ArrayList<>();
	
	static int minCoins;
	public static void main(String[] args) {
		denoms = 5;
		find = 55;
		memo = new boolean[denoms][find+1];
		have = new int[denoms];
		coins = new int[denoms];
		coins[0] = 69;
		coins[1] = 30;
		coins[2] = 10;
		coins[3] = 5;
		coins[4] = 1;
		
		minCoins = Integer.MAX_VALUE;
		// Make sure the array is backwards,
		// the coins are in decreasing value
		travis(0,0);
		Collections.sort(ways);
		for (int i =0 ; i < ways.size(); i++) {
			System.out.println(ways.get(i));
		}
		// Does not use a memo so you can
		// generate all possible sets of coins
		// Pile at position zero uses the minimum
		// number of coins
	}
	
	public static void travis(int pos, int sum) {
		if (sum > find) { 
			return;
		}
		if (sum == find) {
			int p = 0;
			for (int i =0; i < denoms; i++) {
				p += have[i];
			}
			if (p < minCoins) {
				minCoins = p;
			}
			pile pi = new pile(denoms);
			pi.set(have);
			ways.add(pi);
			return;
		}
		if (pos == denoms) {
			return;
		}
		
		travis(pos+1,sum);
		int incs = 0;
		while (sum <= find) {
			
			have[pos]++;
			incs++;
			sum += coins[pos];
			travis(pos+1,sum);
		}
		have[pos] -= incs;
	}

}

class pile implements Comparable<pile> {
	int[] have;
	int denoms;
	int sum;
	public pile(int d) {
		denoms = d;
		have = new int[d];
		sum = 0;
	}
	
	public void set(int[] in) {
		for (int i = 0; i < denoms; i++) {
			have[i] = in[i];
			sum += in[i];
		}
	}

	// Sorts based on the minimum number of coins
	public int compareTo(pile arg0) {
		return sum - arg0.sum;
	}
	
	public String toString() {
		String out = "";
		for (int i = 0; i < denoms; i++) {
			out += have[i];
			out += " ";
		}
		return out;
	}
	
}
