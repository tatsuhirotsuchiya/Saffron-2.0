package demos.binpacking;

import java.util.ArrayList;

public class BinPackingDemo
{
	public static void main(String[] args) throws Exception
	{
		Item[] items = new Item[]
		{ new Item("A", 127), new Item("B", 121), new Item("C", 37),
				new Item("D", 12), new Item("E", 53), new Item("F", 26),
				new Item("G", 17), new Item("H", 32) };

		Bin[] bins = new Bin[]
		{ new Bin("Bin0", 150), new Bin("Bin1", 100), new Bin("Bin2", 185) };

		ArrayList<ArrayList<Item>> solution = BinPacker.pack(items, bins);

		System.out.println(BinPacker.getProblem());
		System.out.println(solution);
	}
}
