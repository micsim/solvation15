package de.gwvprojekt.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.gwvprojekt.algorithm.State;

public class GameUI
{
	// Swing-Shit
	private JFrame _mainframe;
	private JPanel _submainframe;
	private JPanel _matrixframe;
	private JPanel _buttonframe;
	private JPanel _southernTextframe;
	
	private JButton[] _squares;
	private JButton _initializeButton;
	private JButton _hintButton;
	private JButton _solveButton;
	private JButton _restartButton;
	private JLabel _hintBar;
	private JLabel _hintBar2;
	private JComboBox _drop;
	
	// Data + Algorithm
	private State _data;
	//private Algorithm _alg;
	
	// some Constants
	private final String HINT = "Hint";
	private final String INIT = "Initialize";
	private final String SOLVE = "Solve";
	private final String RESTART = "Restart";
	
	/**
	 * Konstruktor der Klasse GameUI
	 * @param data
	 */
	public GameUI(State data)//, Algorithm alg)
	{
		_data = data;
		//_alg = alg;
		_squares = new JButton[16];
		
		_mainframe = new JFrame();
		//_mainframe.setSize(750, 400);
		_mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_submainframe = new JPanel();
		_matrixframe = new JPanel();
		_buttonframe = new JPanel();
		_southernTextframe = new JPanel();
		_initializeButton = new JButton(INIT);
		_hintButton = new JButton(HINT);
		_solveButton = new JButton(SOLVE);
		_restartButton = new JButton(RESTART);
		_hintBar = new JLabel("(>'-')>");
		_hintBar2 = new JLabel("Dieser Text ist (noch) keine Hilfe! <('-'<)");
		_drop = new JComboBox();
		_drop.addItem("A*");
		_drop.addItem("limited A*");
		
		for (int i = 0; i < 16; i++)
		{
			_squares[i] = new JButton();
		}
		
		_mainframe.setLayout(new BorderLayout());
		_submainframe.setLayout(new BorderLayout());
		_matrixframe.setLayout(new GridLayout(4,4));
		_buttonframe.setLayout(new GridLayout(5,1));
		_southernTextframe.setLayout(new GridLayout(2,1));
		
		_mainframe.add(_submainframe, BorderLayout.CENTER);
		_mainframe.add(_southernTextframe, BorderLayout.SOUTH);
		_southernTextframe.add(_hintBar);
		_southernTextframe.add(_hintBar2);
		
		_submainframe.add(_matrixframe, BorderLayout.CENTER);
		_submainframe.add(_buttonframe, BorderLayout.EAST);
		
		for (int i = 0; i < 16; i++)
		{
			_matrixframe.add(_squares[i]);
		}
		
		_buttonframe.add(_restartButton);
		_buttonframe.add(_initializeButton);
		_buttonframe.add(_hintButton);
		_buttonframe.add(_solveButton);
		_buttonframe.add(_drop);
		
		updateUI(true);
		_mainframe.pack();
		_mainframe.setVisible(true);
	}
	
	/**
	 * Funktion um die UI zu aktualisieren 
	 */
	public void updateUI(boolean enable)
	{
		int a = 0;
		for (byte x = 0; x < 4; x++)
		{
			for (byte y = 0; y < 4; y++)
			{
				String text;
				if (_data.getStringValue(x, y).equals("16"))
				{
					text = "";
				}
				else
				{
					text = _data.getStringValue(x, y);
				}
				_squares[a].setText(text);
				if(_data.isMovable(x, y))
				{
					_squares[a].setBackground(Color.GREEN);
					_squares[a].setEnabled(enable);
				}
				else
				{
					_squares[a].setBackground(Color.LIGHT_GRAY);
					_squares[a].setEnabled(false);
				}
				a++;
			}
		}
		_hintButton.setEnabled(enable);
		_initializeButton.setEnabled(enable);
		_solveButton.setEnabled(enable);
		
		_mainframe.repaint();
	}

	/**
	 * Liefert das 1te HintLabel
	 * @return _hintbar
	 */
	public JLabel getHintLabel()
	{
		return _hintBar;
	}
	
	/**
	 * Liefert das 2te HintLabel
	 * @return _hintBar2
	 */
	public JLabel getHintLabel2()
	{
		return _hintBar2;
	}
	
	/**
	 * Liefert das Feld des Spielfeldes an der Stelle index
	 * @param index
	 * @return _squares[index]
	 */
	public JButton getMatrixButtons(int index)
	{
		return _squares[index];
	}
	
	/**
	 * Liefert den HintButton
	 * @return _hintButton
	 */
	public JButton getHintButton()
	{
		return _hintButton;
	}
	
	/**
	 * Liefert den InitButton
	 * @return _initializeButton
	 */
	public JButton getInitButton()
	{
		return _initializeButton;
	}
	
	/**
	 * Liefert den SolveButton
	 * @return _solveButton
	 */
	public JButton getSolveButton()
	{
		return _solveButton;
	}
	
	/**
	 * 
	 * @return
	 */
	public JButton getRestartButton()
	{
		return _restartButton;
	}
	
	/**
	 * 
	 * @return
	 */
	public JComboBox getDropMenue()
	{
		return _drop;
	}
}
