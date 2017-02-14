import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by trold on 2/1/17.
 */
public class DrawWindow implements Observer {
	Model model;
	JFrame window;
	DrawCanvas canvas;


	public DrawWindow(Model model) {
		this.model = model;
		model.addObserver(this);
		window = new JFrame("Linedrawing Machine X 4001");
		window.setLayout(new BorderLayout());
		JTextArea searchField = new JTextArea();

		window.add(searchField, BorderLayout.NORTH);
		searchField.addKeyListener(new KeyAdapter()  {
									   @Override
									   public void keyPressed(KeyEvent e) {
										   if(e.getKeyCode() == KeyEvent.VK_ENTER) {
											   e.consume();
											   searchField.setText("");
											   searchField.setFocusable(false);
											   searchField.setFocusable(true);
										   }
									   }
								   });


				//laver menu'en
				setUpMenu();

					canvas = new DrawCanvas(model);
					canvas.setPreferredSize(new Dimension(500, 500));
					new CanvasMouseController(canvas, model);
					window.add(canvas, BorderLayout.CENTER);
					window.pack();
					window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					window.setVisible(true);

				}

				/**
				 * This method is called whenever the observed object is changed. An
				 * application calls an <tt>Observable</tt> object's
				 * <code>notifyObservers</code> method to have all the object's
				 * observers notified of the change.
				 *
				 * @param o   the observable object.
				 * @param arg an argument passed to the <code>notifyObservers</code>
				 */
		@Override
	public void update(Observable o, Object arg) {

	}

	public void setUpMenu()
	{
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem save = new JMenuItem("Save", KeyEvent.VK_S);
		JMenuItem load = new JMenuItem("Load", KeyEvent.VK_L);
		JMenuItem exit = new JMenuItem("Exit", KeyEvent.VK_Q);
		file.add(save);
		file.add(load);
		file.add(exit);
		menu.add(file);


		JMenu tools = new JMenu("Tools");
		JMenuItem drawALine = new JMenuItem("Draw a line",KeyEvent.VK_D);
		JMenuItem pan = new JMenuItem("Pan", KeyEvent.VK_P);

		tools.add(drawALine);
		tools.add(pan);
		menu.add(tools);


		window.setJMenuBar(menu);


		//Tilføj selv et navn og gemme fra og loade fra


		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String savename = JOptionPane.showInputDialog("Enter a save name");
				savename += ".bin";
				model.save(savename);


			}
		});

		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				String loadname = JOptionPane.showInputDialog("Enter a name of the file you want to load");
				loadname += ".bin";

				//Måske findes filen ikke?
				model.load(loadname);
			}
		});

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		drawALine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CanvasMouseController.isDrawing();
			}
		});

		pan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CanvasMouseController.isPanning();
			}
		});




	}

}
