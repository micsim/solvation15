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
							_data.randomizeData(_random);
							_ui.updateUI();
						}
					});
		
		// HintButton
		_ui.getHintButton().addActionListener(
				new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						try {
							_alg.findPath(_data);
						} catch (CloneNotSupportedException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}

						String text = _alg.getHintString();
						
						_ui.getHintLabel().setText("Hint:");
						_ui.getHintLabel2().setText(text);
						_ui.updateUI();
					}
				});
		
		// SolveButton
		_ui.getSolveButton().addActionListener(
				new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						
						try {
							_alg.findPath(_data);
						} catch (CloneNotSupportedException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}

						String text = _alg.getSolveString();
						
						_ui.getHintLabel().setText("How to solve:");
						_ui.getHintLabel2().setText(text);
						_ui.updateUI();
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
							_ui.updateUI();
						}
					});
		}
	}
}
