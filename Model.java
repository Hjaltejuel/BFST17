import java.awt.geom.Line2D;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

/**
 * Created by trold on 2/1/17.
 */
public class Model extends Observable implements Iterable<Line2D>, Serializable {
	private List<Line2D> lines;

	public Model(String filename) {
		lines = new ArrayList<>();
		try (BufferedReader input = new BufferedReader(new FileReader(filename))) {
			for (String line = input.readLine() ; line != null ; line = input.readLine()) {
				String[] words = line.split(" ");
				double x1 = Double.parseDouble(words[1]);
				double y1 = Double.parseDouble(words[2]);
				double x2 = Double.parseDouble(words[3]);
				double y2 = Double.parseDouble(words[4]);
				lines.add(new Line2D.Double(x1, y1, x2, y2));
			}
			dirty();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Model() {
		lines = new ArrayList<>();
	}

	public void add(Line2D line) {
		lines.add(line);
		dirty();
	}

	private void dirty() {
		setChanged();
		notifyObservers();
	}

	/**
	 * Returns an iterator over elements of type {@code T}.
	 *
	 * @return an Iterator.
	 */
	@Override
	public Iterator<Line2D> iterator() {
		return lines.iterator();
	}

	public Line2D removeLast() {
		Line2D last = lines.remove(lines.size() - 1);
		dirty();
		return last;
	}

	public void save(String filename) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
			out.writeObject(lines);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void load(String filename) {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
			lines = (List<Line2D>) in.readObject();
			dirty();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
