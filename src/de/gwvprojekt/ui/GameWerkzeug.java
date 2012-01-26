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
	private Algorithm _alg1;
	private Algorithm _alg2;
	private Random _random;
	
	/**
	 * Konstruktor der Klasse Gamewerkzeug
	 * @param data
	 */
	public GameWerkzeug(State data, Algorithm alg1, Algorithm alg2)
	{
		_data = data;
		_alg1 = alg1;
		_alg2 = alg2;
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
							}while(!_data.isSolvable());
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
						_ui.updateUI(false);

						String text = "";
						try{
							if(_ui.getDropMenue().getSelectedIndex()==0)
							{
								if(_alg1.findPath(_data, 10)) // A*
									text = _alg1.getSolveString();
								else
									text = "Insolvable!";
							}
							else
							{
								if(_alg2.findPath(_data, 10)) // limited A*
									text = _alg2.getSolveString();
								else
									text = "Insolvable!";
							}

						}catch(OutOfMemoryError ex){
							text = "Could not solve: Algorithm ran out of memory.";
						}catch(CloneNotSupportedException ex){
							text = "Could not solve: Internal error.";
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
						_ui.updateUI(false);
						
						String text = "";						
						try{
							if(_ui.getDropMenue().getSelectedIndex()==0)
							{
								if(_alg1.findPath(_data)) // A*
									text = _alg1.getSolveString();
								else
									text = "Insolvable!";
							}
							else
							{
								if(_alg2.findPath(_data)) // limited A*
									text = _alg2.getSolveString();
								else
									text = "Insolvable!";
							}
						}catch(OutOfMemoryError ex){
							text = "Could not solve: Algorithm ran out of memory.";
						}catch(CloneNotSupportedException ex){
							text = "Could not solve: Internal error.";
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
		
		_ui.getRestartButton().addActionListener(
				new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						_data.initializeData();
						_ui.updateUI(true);
					}
				});
	}
}
