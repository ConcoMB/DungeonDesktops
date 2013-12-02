package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class DeskMenu extends JMenu{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Window window;
		private JMenuBar bar = new JMenuBar();
		private JCheckBox music;
		private JMenuItem newGame;
		private JMenuItem loadGame;
		private JMenuItem saveGame;
		private JMenuItem restartGame;
		private JMenuItem exit; 
		private JMenu programa; 
		private JMenu options; 
		
		public DeskMenu(Window window){
			this.window=window;
		}
		
		public void initMenu() {
			newGame = new JMenuItem("New Game");
			loadGame = new JMenuItem("Load Game");
			saveGame = new JMenuItem("Save Game");
			restartGame = new JMenuItem("Restart Game");
			music =  new JCheckBox("Music Off");
			exit = new JMenuItem("Exit");
			programa = new JMenu("Inicio");
			options = new JMenu("Options");
			window.setJMenuBar(bar);
			bar.add(programa);
			bar.add(options);
			newGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					window.newGame();
				}
			});

			restartGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					window.restart();
				}
			});
			
			exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					System.exit(0);
				}
			});

			loadGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					JFileChooser fileOpen = new JFileChooser();
					fileOpen.setCurrentDirectory(new File("./Games"));
					fileOpen.setFileSelectionMode(JFileChooser.FILES_ONLY);
					int ret = fileOpen.showDialog(window.getContentPane(), "Open file");
					if (ret == JFileChooser.APPROVE_OPTION) {
						File file = fileOpen.getSelectedFile();
						try {
							window.loadGame(file);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(window.getContentPane(), "An error has occurred while loading the file");
						}
					}
				}
			});

			saveGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try {
						window.saveGame();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(window.getContentPane(), "An error has occurred while saving the file");
					}
				}
			});
			
			music.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					if(music.isSelected()){
						window.getMusic().stop();						
					}
					else{
						window.getMusic().start();
					}
				}
			});
			programa.add(newGame);				
			programa.add(loadGame);
			programa.add(saveGame);
			programa.add(restartGame);
			programa.addSeparator();
			programa.add(exit);
			options.add(music);
			window.reset();
		}
		
		public JMenuBar getMenuBar(){
			return bar;
		}
}
