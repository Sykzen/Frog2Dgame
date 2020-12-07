package graphicalElements;


import gameCommons.Game;
import gameCommons.IFrog;
import util.Direction;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class FroggerGraphic extends JPanel implements IFroggerGraphics, KeyListener {
	private Clip clip;
	private Game game;
	private BufferedImage car;
	private BufferedImage rue;
	private BufferedImage eat;
	private BufferedImage water;
	private BufferedImage seaweed;
	private BufferedImage frogg;
	private ArrayList<Element> elementsToDisplay;
	private int pixelByCase = 30; /* aussi la dimension des images (une case) utulisée (30x30) */
	private int height;
	private int width;
	private IFrog frog;
	private JFrame frame;
	private boolean move;
	private Font font = new Font("TimesRoman", Font.ITALIC, 50);



	public FroggerGraphic(int width, int height) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		this.height = height;
		this.width = width;

		this.move = false;
		elementsToDisplay = new ArrayList<>();
		Initiat();
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(width * pixelByCase, height * pixelByCase));

		clip = AudioSystem.getClip();
		AudioInputStream ais = AudioSystem.getAudioInputStream(new File("materials/MusicBack.wav"));
		clip.open(ais);
		clip.loop(Clip.LOOP_CONTINUOUSLY);

		JFrame frame = new JFrame("Frogger");
		this.frame = frame;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.addKeyListener(this);

		frame.getContentPane().add(this);
		frame.pack();

	}

	private void Initiat() throws IOException {
		 /*for (Static i : Static.values())
			i=ImageIO.read(new File("materials/"+i+".png"));*/

		car = ImageIO.read(new File("materials/car1.png"));
		rue = ImageIO.read(new File("materials/rue.png"));
		eat = ImageIO.read(new File("materials/eat.png"));
		water = ImageIO.read(new File("materials/water.png"));
		seaweed = ImageIO.read(new File("materials/seaweed.png"));
		frogg = ImageIO.read(new File("materials/frog.png"));
	}

	private BufferedImage select_image(Element e){
		switch (e.aStatic){
			case car:
				return this.car;
			case eat:
				return this.eat;
			case seaweed:
				return this.seaweed;
			case water:
				return this.water;
			case rue:
				return this.rue;
			case frog:
				return this.frogg;
		}
		System.out.println("WARNING");
		return null;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
			for (Element element : elementsToDisplay) {
				g.drawImage(select_image(element),
						pixelByCase * element.absc,
						pixelByCase * (height - 1 - element.ord),
						this);

		}

	}


	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		try {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					frog.move(Direction.up);
					break;
				case KeyEvent.VK_DOWN:
					frog.move(Direction.down);
					break;
				case KeyEvent.VK_LEFT:
					frog.move(Direction.left);
					break;
				case KeyEvent.VK_RIGHT:
					frog.move(Direction.right);
					break;
				default:
					return;
			}
			this.move = true;

		} catch (NullPointerException ignored){
		}
	}

	public boolean getMove(){
		boolean res = this.move;
		this.move = false;
		return res;
	}

	public void clear() {
		this.elementsToDisplay.clear();
	}

	public void add(Element e) {
		this.elementsToDisplay.add(e);
	}

	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	public void endTemplate(String s) {
		/* css pour une grandeur de 50px et la couleur verte */
		String msg = "<html><style> body{" +
				"color: green;font-size:30px;}</style><body><p align=\"center\"></br>" + s +"</p></body></html>";
		frame.remove(this);
		JLabel label = new JLabel(msg);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setSize(this.getSize());
		frame.getContentPane().add(label);
		frame.repaint();
		clip.stop();

	}

}
