package de.gwvprojekt.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import de.gwvprojekt.algorithm.Algorithm;
import de.gwvprojekt.algorithm.State;

public class GameWerkzeug
{
	//Felder
	private GameUI _ui;
	private State _data;
	private Algorithm _alg;
	private Random _random;
	
	/**
	 * Konstruktor der Klasse Gamewerkzeug
	 * @param data
	 */
	public GameWerkzeug(State data, Algorithm alg)
	{
		_data = data;
		_alg = alg;
		_random = new Random();
		_ui = new GameUI(_data);
		addActions();
	}
	
	/**
	 * Hängt ActionListener an die Labels und Buttons der GUI
	 */
	private void addActions()
	{
		// InitButton
		_ui.getInitButton().addActionListener(
				new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							_data.initializeData();
							do
							{
								_data.randomizeData(_random);
							}while(!_data.solveable());
							_ui.updateUI(true);
						}
					});
		
		// HintButton
		_ui.getHintButton().addActionListener(
				new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						String text = "";
						
						_ui.updateUI(false);
						try {
							if(_alg.findPath(_data))
								text = _alg.getHintString();
							else
								text = "Insolvable!";
						} catch (CloneNotSupportedException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
						
						System.out.println("Hint:");
						System.out.println(text);
						
						_ui.getHintLabel().setText("Hint:");
						_ui.getHintLabel2().setText(text);
						_ui.updateUI(true);
					}
				});
		
		// SolveButton
		_ui.getSolveButton().addActionListener(
				new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						String text = "";
						_ui.updateUI(false);
						try {
							if(_alg.findPath(_data))
								text = _alg.getSolveString();
							else
								text = "Insolvable!";
						} catch (CloneNotSupportedException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}

						System.out.println("How to solve:");
						System.out.println(text);
						
						_ui.getHintLabel().setText("How to solve:");
						_ui.getHintLabel2().setText(text);
						_ui.updateUI(true);
					}
				});
		
		// Spielfelder
		for (byte i = 0; i < 16; i++)
		{
			final byte a = i;
			_ui.getMatrixButtons(i).addActionListener(
					new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							byte column = (byte) (a%4);
							byte row = (byte) (a/4);
							
							_data.move(row, column);
							_ui.updateUI(true);
						}
					});
		}
	}
}
