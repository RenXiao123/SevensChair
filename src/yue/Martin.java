package yue;

import java.util.ArrayList;

import chair.Card;
import chair.Player;

public class Martin implements Player
{
    ArrayList<chair.Card> handCards;
	
	private int points;
	
	private String myName;
	
	public Martin(String name){
		myName = name;
	}
	
	public void Remove(yue.Combo bestcombo, ArrayList<yue.Card> c)
	{
		for(int i=0;i<bestcombo.NumberofCards;i++)
		{
		
			for(int j=0;j<c.size();j++)
			{
				if((c.get(j).Num==bestcombo.cards[i].Num)&&(c.get(j).suits==bestcombo.cards[i].suits))
				{
					c.remove(j);
					break;
				}
			}
		}
	}
	
	
	public void cardtrans(chair.Card c1, yue.Card c2)
	{
		int num;
	    int suit;
	    num=c1.getNumber();
	    suit=c1.getSuit();
	    int input;
	    if(suit<5)
	    {
	    	input=4*(num-1)+suit;
	    }
	    else
	    {
	    	if(num==99)
	    	{
	    		input=20;
	    	}
	    	else
	    	{
	    		input=30;
	    	}
	    }
		c2.Input(input);
	}
	
	
	
	
	public void combotrans(chair.Combo c1,yue.Combo c2)
	{
		int length;
		length=c1.size();
		yue.Card[] card=new yue.Card[length];
		for(int i=0;i<length;i++)
		{
			card[i]=new yue.Card();
			this.cardtrans(c1.get(i), card[i]);
		}
		Operation set=new Operation();
		c2=set.Scores(card);
	}
	
	
	@Override
	public ArrayList<chair.Card> getHandCards() {
		// TODO Auto-generated method stub
		return handCards;
	}

	@Override
	public int getPoints() {
		
		// TODO Auto-generated method stub
		return points;
	}

	@Override
	public void startGame(int order) {
		points =0;
		handCards=new ArrayList();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startRound(ArrayList<chair.Card> newCards) {
		// TODO Auto-generated method stub
		handCards.addAll(newCards);
		
	}

	@Override
	public chair.Combo play(chair.Combo enemyCombo) {
		// TODO Auto-generated method stub
		yue.Combo enemy=new yue.Combo();
		this.combotrans(enemyCombo, enemy);
		Combo c=new Combo();
		
		chair.Combo cc=new chair.Combo();
		
		Operation set=new Operation();
		int length;
		length=handCards.size();
		yue.Card[] card=new yue.Card[length];
		ArrayList<yue.Card> handc=new ArrayList<yue.Card>();
		for(int i=0;i<length;i++)
		{   
			card[i]=new yue.Card();
		    this.cardtrans(handCards.get(i), card[i]);
		    handc.add(card[i]);
		}
		yue.HandCombo hc=new yue.HandCombo();
		hc.GetHandCombo(card, length);
		hc.sort();
		hc.delete();
		Combo bestcombo=new Combo();
		bestcombo=hc.BestCombo();
		boolean compare;
		compare=set.ComboBattle(bestcombo, enemy);
		if(compare)
		{
			this.Remove(bestcombo,handc);
			int size=c.NumberofCards;
			for(int j=0;j<size;j++)
			{
				int u;
				if(bestcombo.cards[j].suits=="Heart")
				{
					u=1;
				}
				else if(bestcombo.cards[j].suits=="Spade")
				{
					u=2;
				}
				else if(bestcombo.cards[j].suits=="Diamond")
				{
					u=3;
				}
				else if(bestcombo.cards[j].suits=="Club")
				{
					u=4;
				}
				
				else
				{
					u=5;
				}
				int n;
				if(bestcombo.cards[j].Num==99)
				{
					n=20;
				}
				else if(bestcombo.cards[j].Num==100)
				{
					n=100;
				}
				else
				{
					n=bestcombo.cards[j].Num;
				}
				chair.Card chaircard=new chair.Card(n,u);
				cc.add(chaircard);
			}
		}
		return cc;
	}

	@Override
	public void endGame(int gameResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLeftNum() {
		// TODO Auto-generated method stub
		return handCards.size();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Martin";
	}

}
