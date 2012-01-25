package de.gwvprojekt.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameUI
{
	// Swing-Shit
	private JFrame _mainframe;
	private JPanel _submainframe;
	private JPanel _matrixframe;
	private JPanel _buttonframe;
	
	private JButton[] _squares;
	private JButton _initializeButton;
	private JButton _hintButton;
	private JLabel _hintBar;
	
	// Data + Algorithm
	private Data _data;
	//private Algorithm _alg;
	
	// some Constants
	private final String HINT = "Hint";
	private final String INIT = "Initialize";
	
	
	public GameUI(Data data)//, Algorithm alg)
	{
		_data = data;
		//_alg = alg;
		_squares = new JButton[16];
		
		_mainframe = new JFrame();
		_submainframe = new JPanel();
		_matrixframe = new JPanel();
		_buttonframe = new JPanel();
		_initializeButton = new JButton(INIT);
		_hintButton = new JButton(HINT);
		_hintBar = new JLabel("Dieser Text ist (noch) keine Hilfe! :(");
		
		for (int i = 0; i < 16; i++)
		{
			_squares[i] = new JButton();
		}
		
		_mainframe.setLayout(new BorderLayout());
		_submainframe.setLayout(new BorderLayout());
		_matrixframe.setLayout(new GridLayout(4,4));
		_buttonframe.setLayout(new GridLayout(2,1));
		
		_mainframe.add(_submainframe, BorderLayout.CENTER);
		_mainframe.add(_hintBar, BorderLayout.SOUTH);
		
		_submainframe.add(_matrixframe, BorderLayout.CENTER);
		_submainframe.add(_buttonframe, BorderLayout.EAST);
		
		for (int i = 0; i < 16; i++)
		{
			_matrixframe.add(_squares[i]);
		}
		
		_buttonframe.add(_initializeButton);
		_buttonframe.add(_hintButton);
		
		updateUI();
		_mainframe.pack();
		_mainframe.show();
		
		
		//System.out.println("Olé!");
	}
	
	public void updateUI()
	{
		int a = 0;
		for (int x = 0; x < 4; x++)
		{
			for (int y = 0; y < 4; y++)
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
					_squares[a].setEnabled(true);
				}
				else
				{
					_squares[a].setBackground(Color.LIGHT_GRAY);
					_squares[a].setEnabled(false);
				}
				a++;
			}
		}
		_mainframe.repaint();
	}
}
