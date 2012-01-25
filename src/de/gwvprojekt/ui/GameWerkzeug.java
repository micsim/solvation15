package de.gwvprojekt.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWerkzeug
{
	private GameUI _ui;
	private Data _data;
	//private Algorithm _alg;
	
	
	public GameWerkzeug(Data data)//, Algorithm alg)
	{
		_data = data;
		//_alg = alg;
		_ui = new GameUI(_data);
		addActions();
	}
	
	private void addActions()
	{
		_ui.getInitButton().addActionListener(
				new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							_data.initializeData();
							_ui.updateUI();
						}
					});
		_ui.getHintButton().addActionListener(
				new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						String text = ""+System.nanoTime(); //TODO Mit Algorithmus verknüpfen 
						_ui.getHintLabel().setText("Hint:");
						_ui.getHintLabel2().setText(text);
						_ui.updateUI();
					}
				});
		
		_ui.getSolveButton().addActionListener(
				new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						String text = ""+System.nanoTime(); //TODO Mit Algorithmus verknüpfen
						_ui.getHintLabel().setText("How to solve:");
						_ui.getHintLabel2().setText(text);
						_ui.updateUI();
					}
				});
		
		for (int i = 0; i < 16; i++)
		{
			final int a = i;
			_ui.getMatrixButtons(i).addActionListener(
					new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							int column = a%4;
							int row = (a-column)/4;
							
							_data.move(row, column);
							_ui.updateUI();
						}
					});
		}
	}
}
