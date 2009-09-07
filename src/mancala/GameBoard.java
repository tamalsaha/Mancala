package mancala;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@SuppressWarnings("serial")
public class GameBoard extends JFrame {

	private ImagePanel jContentPane = null;
	private JPanel jTopPanel;
	private JLabel jLeftLabel = null;
	private JLabel jRightLabel = null;
	private JRadioButton jRbComputer;
	private JLabel jTurnLabel;
	private JLabel jLabel1;
	private JComboBox jComboDepth;
	private JButton jButtonStart;
	private ButtonGroup buttonGroup1;
	private JRadioButton jRbHuman;
	private JPanel jCenterContentPane = null;
	private JButton jButton00 = null;
	private JButton jButton10 = null;
	private JButton jButton20 = null;
	private JButton jButton30 = null;
	private JButton jButton40 = null;
	private JButton jButton50 = null;
	private JButton jButton01 = null;
	private JButton jButton11 = null;
	private JButton jButton21 = null;
	private JButton jButton31 = null;
	private JButton jButton41 = null;
	private JButton jButton51 = null;

	/**
	 * This method initializes
	 * 
	 */

	ManCala game;

	public GameBoard() {
		super();
		initialize();
		game = new ManCala();
		printBoard(false);
	}

	private void printBoard(final boolean gameEnd) {
		if (jButtonStart.isEnabled()) {
			jButton00.setEnabled(false);
			jButton10.setEnabled(false);
			jButton20.setEnabled(false);
			jButton30.setEnabled(false);
			jButton40.setEnabled(false);
			jButton50.setEnabled(false);

			jButton01.setEnabled(false);
			jButton11.setEnabled(false);
			jButton21.setEnabled(false);
			jButton31.setEnabled(false);
			jButton41.setEnabled(false);
			jButton51.setEnabled(false);

			return;
		}
		jLeftLabel.setText("     " + game.bottomBoard.get(6) + "     ");
		jRightLabel.setText("     " + game.topBoard.get(6) + "     ");

		jButton00.setText(game.topBoard.get(0).toString());
		jButton10.setText(game.topBoard.get(1).toString());
		jButton20.setText(game.topBoard.get(2).toString());
		jButton30.setText(game.topBoard.get(3).toString());
		jButton40.setText(game.topBoard.get(4).toString());
		jButton50.setText(game.topBoard.get(5).toString());

		jButton01.setText(game.bottomBoard.get(5).toString());
		jButton11.setText(game.bottomBoard.get(4).toString());
		jButton21.setText(game.bottomBoard.get(3).toString());
		jButton31.setText(game.bottomBoard.get(2).toString());
		jButton41.setText(game.bottomBoard.get(1).toString());
		jButton51.setText(game.bottomBoard.get(0).toString());

		if (gameEnd) {
			jTurnLabel.setText("");

			jButton00.setText("0");
			jButton10.setText("0");
			jButton20.setText("0");
			jButton30.setText("0");
			jButton40.setText("0");
			jButton50.setText("0");

			jButton01.setText("0");
			jButton11.setText("0");
			jButton21.setText("0");
			jButton31.setText("0");
			jButton41.setText("0");
			jButton51.setText("0");

			jButton00.setEnabled(false);
			jButton10.setEnabled(false);
			jButton20.setEnabled(false);
			jButton30.setEnabled(false);
			jButton40.setEnabled(false);
			jButton50.setEnabled(false);

			jButton01.setEnabled(false);
			jButton11.setEnabled(false);
			jButton21.setEnabled(false);
			jButton31.setEnabled(false);
			jButton41.setEnabled(false);
			jButton51.setEnabled(false);
			String winner = game.topBoard.get(6) > game.bottomBoard.get(6) ? "Top"
					: "Bottom";
			if (game.topBoard.get(6) != game.bottomBoard.get(6))
				JOptionPane.showMessageDialog(null, "Game Over !!! " + winner
						+ " wins!");
			else
				JOptionPane.showMessageDialog(null,
						"Game Over!!! It's a tie...");
		} else {
			if (game.turn) {
				jTurnLabel.setText("Turn: Top (Human)");
			} else {
				jTurnLabel.setText("Turn: Bottom ("
						+ (jRbComputer.isSelected() ? "Computer)" : "Human)"));
			}

			jButton00.setEnabled(game.turn);
			jButton10.setEnabled(game.turn);
			jButton20.setEnabled(game.turn);
			jButton30.setEnabled(game.turn);
			jButton40.setEnabled(game.turn);
			jButton50.setEnabled(game.turn);

			jButton01.setEnabled(!game.turn);
			jButton11.setEnabled(!game.turn);
			jButton21.setEnabled(!game.turn);
			jButton31.setEnabled(!game.turn);
			jButton41.setEnabled(!game.turn);
			jButton51.setEnabled(!game.turn);

			if (!game.turn && jRbComputer.isSelected()) {
				jTurnLabel.setText("Turn: Bottom (Computer) calculating...");
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						NextMove();
					}
				});
			}
		}
	}

	private void NextMove() {
		printBoard(!game.move(game.getBestNextMove(Integer.parseInt(jComboDepth
				.getSelectedItem().toString()))));
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		this.setContentPane(getJContentPane());
		this.setTitle("ManCala");
		this.setSize(600, 240);
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jRightLabel = new JLabel();
			jRightLabel.setForeground(Color.white);
			jRightLabel.setText("               ");
			jLeftLabel = new JLabel();
			jLeftLabel.setForeground(Color.white);
			jLeftLabel.setText("               ");
			jTopPanel = new JPanel();
			GroupLayout jTopLabelLayout = new GroupLayout(
					(JComponent) jTopPanel);
			jTopPanel.setLayout(jTopLabelLayout);
			jTopLabelLayout.setVerticalGroup(jTopLabelLayout
					.createSequentialGroup().addGroup(
							jTopLabelLayout.createParallelGroup(
									GroupLayout.Alignment.BASELINE)
									.addComponent(getJComboDepth(),
											GroupLayout.Alignment.BASELINE,
											GroupLayout.PREFERRED_SIZE, 22,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(getJRbComputer(),
											GroupLayout.Alignment.BASELINE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(getJButtonStart(),
											GroupLayout.Alignment.BASELINE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(getJLabel1(),
											GroupLayout.Alignment.BASELINE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(
							jTopLabelLayout.createParallelGroup(
									GroupLayout.Alignment.BASELINE)
									.addComponent(getJRadioButton1(),
											GroupLayout.Alignment.BASELINE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(getJTurnLabel(),
											GroupLayout.Alignment.BASELINE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE,
											GroupLayout.PREFERRED_SIZE))
					.addContainerGap());
			jTopLabelLayout
					.setHorizontalGroup(jTopLabelLayout
							.createSequentialGroup()
							.addContainerGap()
							.addGroup(
									jTopLabelLayout
											.createParallelGroup()
											.addComponent(
													getJRadioButton1(),
													GroupLayout.Alignment.LEADING,
													GroupLayout.PREFERRED_SIZE,
													153,
													GroupLayout.PREFERRED_SIZE)
											.addComponent(
													getJRbComputer(),
													GroupLayout.Alignment.LEADING,
													GroupLayout.PREFERRED_SIZE,
													153,
													GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(
									LayoutStyle.ComponentPlacement.RELATED)
							.addComponent(getJLabel1(),
									GroupLayout.PREFERRED_SIZE, 42,
									GroupLayout.PREFERRED_SIZE)
							.addComponent(getJComboDepth(),
									GroupLayout.PREFERRED_SIZE, 46,
									GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addGroup(
									jTopLabelLayout
											.createParallelGroup()
											.addGroup(
													jTopLabelLayout
															.createSequentialGroup()
															.addComponent(
																	getJTurnLabel(),
																	GroupLayout.PREFERRED_SIZE,
																	221,
																	GroupLayout.PREFERRED_SIZE)
															.addGap(
																	0,
																	0,
																	Short.MAX_VALUE))
											.addGroup(
													GroupLayout.Alignment.LEADING,
													jTopLabelLayout
															.createSequentialGroup()
															.addComponent(
																	getJButtonStart(),
																	GroupLayout.PREFERRED_SIZE,
																	119,
																	GroupLayout.PREFERRED_SIZE)
															.addGap(
																	0,
																	102,
																	Short.MAX_VALUE)))
							.addContainerGap(67, 67));
			jTopPanel
					.setBorder(BorderFactory.createEmptyBorder(10, -1, 10, -1));
			try {
				jContentPane = new ImagePanel(javax.imageio.ImageIO
						.read(new java.io.File("c:\\2games buttons.jpg")),
						ImagePanel.SCALED);
			} catch (Exception e) {
			}
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(jTopPanel, BorderLayout.NORTH);
			jTopPanel.setPreferredSize(new java.awt.Dimension(573, 79));
			jContentPane.add(jLeftLabel, BorderLayout.WEST);
			jContentPane.add(jRightLabel, BorderLayout.EAST);
			jContentPane.add(getJCenterContentPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jCenterContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJCenterContentPane() {
		if (jCenterContentPane == null) {
			final GridBagConstraints gridBagConstraints00 = new GridBagConstraints();
			gridBagConstraints00.gridx = 0;
			gridBagConstraints00.gridy = 0;
			gridBagConstraints00.insets.set(10, 10, 10, 10);
			gridBagConstraints00.fill = GridBagConstraints.BOTH;
			gridBagConstraints00.weightx = 1;
			gridBagConstraints00.weighty = 1;

			final GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 1;
			gridBagConstraints10.gridy = 0;
			gridBagConstraints10.insets.set(10, 10, 10, 10);
			gridBagConstraints10.fill = GridBagConstraints.BOTH;
			gridBagConstraints10.weightx = 1;
			gridBagConstraints10.weighty = 1;

			final GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.gridx = 2;
			gridBagConstraints20.gridy = 0;
			gridBagConstraints20.insets.set(10, 10, 10, 10);
			gridBagConstraints20.fill = GridBagConstraints.BOTH;
			gridBagConstraints20.weightx = 1;
			gridBagConstraints20.weighty = 1;

			final GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			gridBagConstraints30.gridx = 3;
			gridBagConstraints30.gridy = 0;
			gridBagConstraints30.insets.set(10, 10, 10, 10);
			gridBagConstraints30.fill = GridBagConstraints.BOTH;
			gridBagConstraints30.weightx = 1;
			gridBagConstraints30.weighty = 1;

			final GridBagConstraints gridBagConstraints40 = new GridBagConstraints();
			gridBagConstraints40.gridx = 4;
			gridBagConstraints40.gridy = 0;
			gridBagConstraints40.insets.set(10, 10, 10, 10);
			gridBagConstraints40.fill = GridBagConstraints.BOTH;
			gridBagConstraints40.weightx = 1;
			gridBagConstraints40.weighty = 1;

			final GridBagConstraints gridBagConstraints50 = new GridBagConstraints();
			gridBagConstraints50.gridx = 5;
			gridBagConstraints50.gridy = 0;
			gridBagConstraints50.insets.set(10, 10, 10, 10);
			gridBagConstraints50.fill = GridBagConstraints.BOTH;
			gridBagConstraints50.weightx = 1;
			gridBagConstraints50.weighty = 1;

			// ///////

			final GridBagConstraints gridBagConstraints01 = new GridBagConstraints();
			gridBagConstraints01.gridx = 0;
			gridBagConstraints01.gridy = 1;
			gridBagConstraints01.insets.set(10, 10, 10, 10);
			gridBagConstraints01.fill = GridBagConstraints.BOTH;
			gridBagConstraints01.weightx = 1;
			gridBagConstraints01.weighty = 1;

			final GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 1;
			gridBagConstraints11.gridy = 1;
			gridBagConstraints11.insets.set(10, 10, 10, 10);
			gridBagConstraints11.fill = GridBagConstraints.BOTH;
			gridBagConstraints11.weightx = 1;
			gridBagConstraints11.weighty = 1;

			final GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 2;
			gridBagConstraints21.gridy = 1;
			gridBagConstraints21.insets.set(10, 10, 10, 10);
			gridBagConstraints21.fill = GridBagConstraints.BOTH;
			gridBagConstraints21.weightx = 1;
			gridBagConstraints21.weighty = 1;

			final GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 3;
			gridBagConstraints31.gridy = 1;
			gridBagConstraints31.insets.set(10, 10, 10, 10);
			gridBagConstraints31.fill = GridBagConstraints.BOTH;
			gridBagConstraints31.weightx = 1;
			gridBagConstraints31.weighty = 1;

			final GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.gridx = 4;
			gridBagConstraints41.gridy = 1;
			gridBagConstraints41.insets.set(10, 10, 10, 10);
			gridBagConstraints41.fill = GridBagConstraints.BOTH;
			gridBagConstraints41.weightx = 1;
			gridBagConstraints41.weighty = 1;

			final GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
			gridBagConstraints51.gridx = 5;
			gridBagConstraints51.gridy = 1;
			gridBagConstraints51.insets.set(10, 10, 10, 10);
			gridBagConstraints51.fill = GridBagConstraints.BOTH;
			gridBagConstraints51.weightx = 1;
			gridBagConstraints51.weighty = 1;

			// ///////

			jCenterContentPane = new JPanel();
			jCenterContentPane.setLayout(new GridBagLayout());
			jCenterContentPane.add(getJButton00(), gridBagConstraints00);
			jCenterContentPane.add(getJButton10(), gridBagConstraints10);
			jCenterContentPane.add(getJButton20(), gridBagConstraints20);
			jCenterContentPane.add(getJButton30(), gridBagConstraints30);
			jCenterContentPane.add(getJButton40(), gridBagConstraints40);
			jCenterContentPane.add(getJButton50(), gridBagConstraints50);

			jCenterContentPane.add(getJButton01(), gridBagConstraints01);
			jCenterContentPane.add(getJButton11(), gridBagConstraints11);
			jCenterContentPane.add(getJButton21(), gridBagConstraints21);
			jCenterContentPane.add(getJButton31(), gridBagConstraints31);
			jCenterContentPane.add(getJButton41(), gridBagConstraints41);
			jCenterContentPane.add(getJButton51(), gridBagConstraints51);

		}
		return jCenterContentPane;
	}

	/**
	 * This method initializes jButton00
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton00() {
		if (jButton00 == null) {
			jButton00 = new JButton();
			jButton00.addActionListener(new GameActionListener());
		}
		return jButton00;
	}

	/**
	 * This method initializes jButton10
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton10() {
		if (jButton10 == null) {
			jButton10 = new JButton();
			jButton10.addActionListener(new GameActionListener());
		}
		return jButton10;
	}

	/**
	 * This method initializes jButton20
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton20() {
		if (jButton20 == null) {
			jButton20 = new JButton();
			jButton20.addActionListener(new GameActionListener());
		}
		return jButton20;
	}

	/**
	 * This method initializes jButton30
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton30() {
		if (jButton30 == null) {
			jButton30 = new JButton();
			jButton30.addActionListener(new GameActionListener());
		}
		return jButton30;
	}

	/**
	 * This method initializes jButton40
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton40() {
		if (jButton40 == null) {
			jButton40 = new JButton();
			jButton40.addActionListener(new GameActionListener());
		}
		return jButton40;
	}

	/**
	 * This method initializes jButton50
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton50() {
		if (jButton50 == null) {
			jButton50 = new JButton();
			jButton50.addActionListener(new GameActionListener());
		}
		return jButton50;
	}

	/**
	 * This method initializes jButton01
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton01() {
		if (jButton01 == null) {
			jButton01 = new JButton();
			jButton01.addActionListener(new GameActionListener());
		}
		return jButton01;
	}

	/**
	 * This method initializes jButton11
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton11() {
		if (jButton11 == null) {
			jButton11 = new JButton();
			jButton11.addActionListener(new GameActionListener());
		}
		return jButton11;
	}

	/**
	 * This method initializes jButton21
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton21() {
		if (jButton21 == null) {
			jButton21 = new JButton();
			jButton21.addActionListener(new GameActionListener());
		}
		return jButton21;
	}

	/**
	 * This method initializes jButton31
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton31() {
		if (jButton31 == null) {
			jButton31 = new JButton();
			jButton31.addActionListener(new GameActionListener());
		}
		return jButton31;
	}

	/**
	 * This method initializes jButton41
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton41() {
		if (jButton41 == null) {
			jButton41 = new JButton();
			jButton41.addActionListener(new GameActionListener());
		}
		return jButton41;
	}

	/**
	 * This method initializes jButton51
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton51() {
		if (jButton51 == null) {
			jButton51 = new JButton();
			jButton51.addActionListener(new GameActionListener());
		}
		return jButton51;
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(final String[] args) throws IOException {
		// TODO Auto-generated method stub
		new GameBoard().show();
	}

	private JRadioButton getJRbComputer() {
		if (jRbComputer == null) {
			jRbComputer = new JRadioButton();
			jRbComputer.setText("Play with Computer");
			jRbComputer.setSelected(true);
			jRbComputer.addActionListener(new GameActionListener());
			getButtonGroup1().add(jRbComputer);
		}
		return jRbComputer;
	}

	private JRadioButton getJRadioButton1() {
		if (jRbHuman == null) {
			jRbHuman = new JRadioButton();
			jRbHuman.setText("Play with Human");
			jRbHuman.addActionListener(new GameActionListener());
			getButtonGroup1().add(jRbHuman);
		}
		return jRbHuman;
	}

	private JLabel getJTurnLabel() {
		if (jTurnLabel == null) {
			jTurnLabel = new JLabel();
			jTurnLabel.setForeground(Color.RED);
			jTurnLabel.setText("Turn: -");
		}
		return jTurnLabel;
	}

	private ButtonGroup getButtonGroup1() {
		if (buttonGroup1 == null) {
			buttonGroup1 = new ButtonGroup();
		}
		return buttonGroup1;
	}

	private JButton getJButtonStart() {
		if (jButtonStart == null) {
			jButtonStart = new JButton();
			jButtonStart.setText("Start Game");
			jButtonStart.addActionListener(new GameActionListener());
		}
		return jButtonStart;
	}

	private JComboBox getJComboDepth() {
		if (jComboDepth == null) {
			ComboBoxModel jComboDepthModel = new DefaultComboBoxModel(
					new String[] { "5", "8", "10", "12", "15", "18", "20" });
			jComboDepth = new JComboBox();
			jComboDepth.setModel(jComboDepthModel);
			jComboDepth.setSelectedIndex(3);
			jComboDepth.setRequestFocusEnabled(false);
		}
		return jComboDepth;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Depth: ");
		}
		return jLabel1;
	}

	public class GameActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			if (e.getSource() instanceof JRadioButton) {
				final JRadioButton src = (JRadioButton) e.getSource();
				if (src == jRbComputer) {
					jLabel1.setEnabled(true);
					jComboDepth.setEnabled(true);
				} else if (src == jRbHuman) {
					jLabel1.setEnabled(false);
					jComboDepth.setEnabled(false);
				}
			} else {
				final JButton src = (JButton) e.getSource();
				boolean gameEnd = false;
				if (src == jButton00) {
					gameEnd = game.move(0);
				} else if (src == jButton10) {
					gameEnd = game.move(1);
				} else if (src == jButton20) {
					gameEnd = game.move(2);
				} else if (src == jButton30) {
					gameEnd = game.move(3);
				} else if (src == jButton40) {
					gameEnd = game.move(4);
				} else if (src == jButton50) {
					gameEnd = game.move(5);
				} else if (src == jButton01) {
					gameEnd = game.move(12);
				} else if (src == jButton11) {
					gameEnd = game.move(11);
				} else if (src == jButton21) {
					gameEnd = game.move(10);
				} else if (src == jButton31) {
					gameEnd = game.move(9);
				} else if (src == jButton41) {
					gameEnd = game.move(8);
				} else if (src == jButton51) {
					gameEnd = game.move(7);
				} else if (src == jButtonStart) {
					jRbComputer.setEnabled(false);
					jRbHuman.setEnabled(false);
					jButtonStart.setEnabled(false);
					jComboDepth.setEnabled(false);
					if (jRbComputer.isSelected()) {
						JOptionPane.showMessageDialog(null, "Toss");
						game.turn = (new Random().nextInt(32)) % 2 == 0;
						JOptionPane.showMessageDialog(null,
								game.turn ? "Top wins toss."
										: "Bottom wins toss.");
					}
					gameEnd = true;
				}
				printBoard(!gameEnd);
			}
		}
	}

} // @jve:decl-index=0:visual-constraint="10,10"
