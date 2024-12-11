import java.util.*;

public class Permutations<E> {

    private List<E> list;
    private List<List<E>> permutations;
    private int index;

    public Permutations(List<E> list) {
        this.list = list;
        permutations = new ArrayList<>();
        generatePermutations(list, list.size());
        index = 0;
    }

    private void generatePermutations(List<E> list, int size) {
        if (size == 1) {
            permutations.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < size; i++) {
            generatePermutations(list, size - 1);

            if (size % 2 == 0) {
                Collections.swap(list, i, size - 1);
            }
			else {
                Collections.swap(list, 0, size - 1);
            }
        }
    }

    public boolean hasNext() {
        return index < permutations.size();
    }

    public List<E> next() {
        if (!hasNext()) {
            return null;
        }

        List<E> result = permutations.get(index);
        index++;
        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Use: java Permutations <integer>");
			System.exit(1);
		}
		try {
			int n = Integer.parseInt(args[0]);
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				list.add(i);
			}
			Permutations<Integer> permutations = new Permutations<>(list);
			while (permutations.hasNext()) {
				System.out.println(permutations.next());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
    }
}