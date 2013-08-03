
import javax.swing.*;
import java.text.*;
import java.util.*;
import java.awt.event.*;
import java.awt.BorderLayout;

public class DealOrNoDeal implements ActionListener {

	public int roundNumber = 1;
	public DecimalFormat df = new DecimalFormat("###,##,###.##");
	public double offer = 0;
	public int yourCase = 0;
	public int numOfCasesLeft = 26;
	public int numOfCases = 6;
	public int numOfCasesChosen = 0;
	public int isYourCase = 1;
	public Cases randomMoney = new Cases();;
	public List<String> cash = randomMoney.getRandomCash();;
	public Banker banker = new Banker();
	public JFrame frame1, frame2, frame3, frame4;
	public JLabel[] moneySelection;
	public JLabel buffer, round, offerLabel;
	public JButton[] suitCase;
	public JButton deal, noDeal;
	public JPanel caseContainers, leftSide, rightSide, dealPanel;
	
	public DealOrNoDeal() { 
		moneySelection = new JLabel[26];
		buffer = new JLabel("Please choose your case.");
		round = new JLabel("Round Number :" + roundNumber);
		frame1 = new JFrame("Deal or No Deal");
		frame1.setVisible(true);
		frame1.setSize(400, 350);
		frame1.setLayout(new BorderLayout(200, 50));
		frame1.setLocationRelativeTo(null);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2 = new JFrame("Low Prices");
		frame2.setLocationRelativeTo(frame1);
		frame2.setSize(300,200);
		frame2.setVisible(true);
		frame2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame3 = new JFrame("High Prices");
		frame3.setLocationRelativeTo(frame2);
		frame3.setSize(300,200);
		frame3.setVisible(true);
		frame3.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame4 = new JFrame();
		frame4.setSize(350,200);
		frame4.setLocationRelativeTo(frame1);
		dealPanel = new JPanel();
		deal = new JButton("Deal");
		deal.setActionCommand("deal");
		deal.addActionListener(this);
		noDeal = new JButton("No Deal");
		noDeal.setActionCommand("no deal");
		noDeal.addActionListener(this);
		offerLabel = new JLabel();
		dealPanel.add(offerLabel);
		dealPanel.add(deal);
		dealPanel.add(noDeal);
		frame4.add(dealPanel);
		suitCase = new JButton[26];
		caseContainers = new JPanel();
		caseContainers.setVisible(true);
		leftSide = new JPanel();
		rightSide = new JPanel();
		for (int i = 0; i < suitCase.length; i++){
			suitCase[i] = new JButton(String.valueOf(i + 1));
			caseContainers.add(suitCase[i]);
			suitCase[i].setActionCommand("suitcase " + (i + 1));
			suitCase[i].addActionListener(this);
			moneySelection[i] = new JLabel(randomMoney.passableMoney[i]);
			moneySelection[i].setBorder(BorderFactory.createRaisedBevelBorder());
		}
		frame1.add(caseContainers, BorderLayout.CENTER);
		frame1.add(buffer, BorderLayout.NORTH);
		frame1.add(round, BorderLayout.SOUTH);
		for (int j = 0; j < 13; j++){
			leftSide.add(moneySelection[j]);
			rightSide.add(moneySelection[j + 13]);
		}
		frame2.add(leftSide);
		frame3.add(rightSide);
		frame2.validate();
		frame3.validate();
		frame1.validate();
	}
	
	public void actionPerformed(ActionEvent e){
		if ("deal".equals(e.getActionCommand())){
			JOptionPane.showMessageDialog(null, "Congratulations, you won $" + df.format(offer) + "!\n If you kept your case, you would have won $" + df.format(Double.parseDouble(cash.get(0))));
			System.exit(0);
		}
		if ("no deal".equals(e.getActionCommand())){
			if (numOfCasesLeft == 2){
				JOptionPane.showMessageDialog(null, "Your case contained $" + df.format(Double.parseDouble(cash.get(0))));
				System.exit(0);
			}
			numOfCasesChosen = 0;
			numOfCases--;
			if (numOfCases < 1){
				numOfCases = 1;
			}
			frame4.setVisible(false);
			roundNumber++;
			round.setText("Round Number: " + roundNumber);
			JOptionPane.showMessageDialog(null, "Please choose " + numOfCases + " cases.");
		}
		for (int i = 0; i < 26; i++){
			if (isYourCase == 1){
				if (("suitcase " + (i + 1)).equals(e.getActionCommand())){
					suitCase[i].setEnabled(false);
					yourCase = Integer.parseInt(suitCase[i].getText());
					buffer.setText("Your case: " + yourCase);
					isYourCase = 0;
					JOptionPane.showMessageDialog(null, "Please choose 6 cases");
					break;
				}
			}
			if (isYourCase == 0 && numOfCasesChosen != numOfCases){
				if (("suitcase " + (i + 1)).equals(e.getActionCommand())){
					suitCase[i].setEnabled(false);
					for (int j = 0; j < 26; j++){
						if (moneySelection[j].getText() == String.valueOf(cash.get(0))){
							moneySelection[j].setVisible(false);
							cash.remove(0);
							numOfCasesLeft--;
							break;
						}
					}
					numOfCasesChosen++;
					if (numOfCasesChosen == numOfCases){
						offer = banker.getDeal(roundNumber, cash);
						offerLabel.setText("The banker will offer you " + df.format(offer) + " for your case.");
						frame4.setVisible(true);
					}
				}
			}
		}
	}
	public static void main(String[] args){
		new DealOrNoDeal();
	}

}


