import java.awt.Choice;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gui extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private SkladkaZUS zus;
	
	private JTextField kwotaZaGodzTF;
	private JTextField iloscDyzurow24GodzTF;
	private JTextField iloscDyzurow12GodzTF;
	private JTextField iloscGodzinTF;
	
	DaneDoWyliczen daneDoWyliczen;

	private JTextField kwotaNettoNaFaktureTF;
	private JTextField kwotaBruttoNaFaktureTF;
	private JTextField zusDoZaplatyTF;
	private JTextField podatekPitTF;
	private JTextField kwotaDoRekiTF;
	

	private JTextField sumaGodzWMiesiacuTF;

	private Choice opcjaChoice;

	private JLabel kwotaZaGodzLabel;

	private JButton obliczPrzycisk;
	
	public Gui() {
		
		zus = new SkladkaZUS();
		//new SkladkaZUS(713.35, 59.61, 288.95,248.82);
		zus.setSpoleczne(713.35);
		zus.setFunduszPracy(59.61);
		zus.setZdrowotne(288.95);
		zus.setZdrowotneDoOdliczenia(248.82);
		
		daneDoWyliczen = new DaneDoWyliczen(zus);
		
		setTitle("Obliczacz pensji");
		setPreferredSize(new Dimension(400, 400));
		setLayout(new GridLayout(4, 1));
		
		add(stworzPanelWyborOpjiLiczenia());
		add(stworzPanelDanych());
		add(stworzPanelPrzyciskow());
		add(stworzPanelWynikow());
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private JPanel stworzPanelDanych() {
			JPanel panel = new JPanel();
			opcjaChoice.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
				
					if(opcjaChoice.getSelectedItem() == "DY�URAMI" ){
						panel.removeAll();
						panel.add(stworzPanelDanychDyzurami());
						panel.revalidate();
						panel.repaint();
						opcjaChoice.setEnabled(false);
						obliczPrzycisk.setEnabled(true);
					}
					else if(opcjaChoice.getSelectedItem() == "GODZINAMI" ){
						panel.removeAll();
						panel.add(stworzPanelDanychGodzinami());
						panel.revalidate();
						panel.repaint();
						opcjaChoice.setEnabled(false);
						obliczPrzycisk.setEnabled(true);
					}
					else{
						panel.removeAll();
						panel.revalidate();
						panel.repaint();
						obliczPrzycisk.setEnabled(false);
					}
				}
		});
		return panel;
	}

	private JPanel stworzPanelDanychGodzinami() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(2, 2,10,0));
		panel.setBorder(BorderFactory.createTitledBorder(""));
		
		kwotaZaGodzLabel = new JLabel("Kwota za godz.:");
		JLabel iloscGodzinLabel = new JLabel("Ilo�� godzin");
		iloscGodzinTF = new JTextField();
		kwotaZaGodzTF = new JTextField();
		
		kwotaZaGodzLabel.setHorizontalAlignment(JLabel.RIGHT);
		iloscGodzinLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		panel.add(kwotaZaGodzLabel);
		panel.add(kwotaZaGodzTF);
		panel.add(iloscGodzinLabel);
		panel.add(iloscGodzinTF);		

		return panel;
	}
	public JPanel stworzPanelWyborOpjiLiczenia(){
		JPanel panel = new JPanel();
		
		opcjaChoice = new Choice();
		opcjaChoice.addItem("");
		opcjaChoice.addItem("GODZINAMI");
		opcjaChoice.addItem("DY�URAMI");
		
		panel.add(opcjaChoice);
		return panel;
	}
	public JPanel stworzPanelDanychDyzurami(){
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2,10,0));
		panel.setBorder(BorderFactory.createTitledBorder(""));
		
		kwotaZaGodzLabel = new JLabel("Kwota za godz.:");
		JLabel iloscDyzurow24GodzLabel = new JLabel("Ilo�� dy�ur�w 24 godzinnych:");
		JLabel iloscDyzurow12GodzLabel = new JLabel("Ilo�� dy�ur�w 12 godzinnych:");
		
		kwotaZaGodzLabel.setHorizontalAlignment(JLabel.RIGHT);
		iloscDyzurow12GodzLabel.setHorizontalAlignment(JLabel.RIGHT);
		iloscDyzurow24GodzLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		kwotaZaGodzTF = new JTextField("40");
		iloscDyzurow24GodzTF = new JTextField("3");
		iloscDyzurow12GodzTF = new JTextField("0");
		
		panel.add(kwotaZaGodzLabel);
		panel.add(kwotaZaGodzTF);
		panel.add(iloscDyzurow12GodzLabel);
		panel.add(iloscDyzurow12GodzTF);
		panel.add(iloscDyzurow24GodzLabel);
		panel.add(iloscDyzurow24GodzTF);
		
		return panel;
	}
	
	public JPanel stworzPanelPrzyciskow(){
		JPanel panel = new JPanel();
		
		obliczPrzycisk = new JButton("Oblicz");
		JButton wyczyscPrzycisk = new JButton("Wyczy��");
		JButton zakonczPrzycisk = new JButton("Zako�cz");
		JOptionPane optionPane = new JOptionPane();
		
		wyczyscPrzycisk.setEnabled(false);
		obliczPrzycisk.setEnabled(false);
		
		JTextArea informacja = new JTextArea("Uwaga!\n"
				+ "Obliczone kwoty programu s� kwotami orientacyjnymi.\n"
				+ "W �adnym wypadku nie mo�na ich u�y� do realnych rozlicze� z US");
		informacja.setEditable(false);
		
		obliczPrzycisk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					przyciskObliczAction();
				}catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(optionPane, "Czego� nie uzupe�ni�e�!\n"
							+ "Mog�o si� tak�e zdarzy�, �e w kt�rym� polu, nie poda�e� poprawnej warto�ci.\n"
							+ "Sprawd� prosz� i spr�buj jeszcze raz.");
				}
				wyczyscPrzycisk.setEnabled(true);
				obliczPrzycisk.setEnabled(false);
			}
			
		});
		
		wyczyscPrzycisk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				przyciskWyczyscAction();
				wyczyscPrzycisk.setEnabled(false);
				obliczPrzycisk.setEnabled(true);
			}
			
		});
		zakonczPrzycisk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		panel.add(obliczPrzycisk);
		panel.add(wyczyscPrzycisk);
		panel.add(zakonczPrzycisk);
		panel.add(informacja);
		
		return panel;
	}
	
	private void przyciskObliczAction() throws NumberFormatException, NullPointerException{
		NumberFormat formater = NumberFormat.getCurrencyInstance();
		
		if(opcjaChoice.getSelectedItem() == "GODZINAMI"){
			daneDoWyliczen.setKwotaZaGodz(Double.parseDouble(kwotaZaGodzTF.getText()));
			daneDoWyliczen.setIloscGodzin(Integer.parseInt(iloscGodzinTF.getText()));
			
			kwotaZaGodzTF.setEnabled(false);
			iloscGodzinTF.setEnabled(false);
			sumaGodzWMiesiacuTF.setEnabled(false);
			
			kwotaNettoNaFaktureTF.setText(formater.format(daneDoWyliczen.obliczKwotaNetto1()));
			kwotaBruttoNaFaktureTF.setText(formater.format(daneDoWyliczen.obliczKwoteBrutto1()));
			
			zusDoZaplatyTF.setText(formater.format(zus.sumaSkladekZus()));
			podatekPitTF.setText(formater.format(daneDoWyliczen.obliczPit1()));
			kwotaDoRekiTF.setText(formater.format(daneDoWyliczen.obliczKwoteNaReke1()));
		
			
			sumaGodzWMiesiacuTF.setText(daneDoWyliczen.getIloscGodzin()+"");
		}
		if(opcjaChoice.getSelectedItem() == "DY�URAMI"){
			daneDoWyliczen.setKwotaZaGodz(Double.parseDouble(kwotaZaGodzTF.getText()));
			daneDoWyliczen.setIloscDyzurow12(Integer.parseInt(iloscDyzurow12GodzTF.getText()));
			daneDoWyliczen.setIloscDyzurow24(Integer.parseInt(iloscDyzurow24GodzTF.getText()));
			
			kwotaZaGodzTF.setEnabled(false);
			iloscDyzurow12GodzTF.setEnabled(false);
			iloscDyzurow24GodzTF.setEnabled(false);
			sumaGodzWMiesiacuTF.setEnabled(false);
			
			kwotaNettoNaFaktureTF.setText(formater.format(daneDoWyliczen.obliczKwotaNetto()));
			kwotaBruttoNaFaktureTF.setText(formater.format(daneDoWyliczen.obliczKwoteBrutto()));
			
			zusDoZaplatyTF.setText(formater.format(zus.sumaSkladekZus()));
			podatekPitTF.setText(formater.format(daneDoWyliczen.obliczPit()));
			kwotaDoRekiTF.setText(formater.format(daneDoWyliczen.obliczKwoteNaReke()));
	
			sumaGodzWMiesiacuTF.setText(daneDoWyliczen.sumaGodzWMiesiacu()+"");
		}
	}
	
	private void przyciskWyczyscAction() {
		
		kwotaZaGodzTF.setEnabled(true);
		kwotaZaGodzTF.setText("");
		opcjaChoice.setEnabled(true);
		
		if(opcjaChoice.getSelectedItem() == "GODZINAMI"){
			iloscGodzinTF.setEnabled(true);
			iloscGodzinTF.setText("");
		}
		if(opcjaChoice.getSelectedItem() == "DY�URAMI"){
				
			iloscDyzurow12GodzTF.setEnabled(true);
			iloscDyzurow12GodzTF.setText("");
			
			iloscDyzurow24GodzTF.setEnabled(true);
			iloscDyzurow24GodzTF.setText("");
		}
	}
	
	public JPanel stworzPanelWynikow(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2,5,0));
		
		JLabel sumaGodzWMiesiacuLabel = new JLabel("Suma godzin przepracowanych w miesi�cu:");
		JLabel kwotaNettoNaFaktureLabel = new JLabel("Kwota netto na faktur�:");
		JLabel kwotaBruttoNaFaktureLabel = new JLabel("Kwota brutto na faktur�:");
		JLabel zusDoZaplatyLabel = new JLabel("Suma sk�adek zus do zap�acenia:");
		JLabel podatekPitLabel = new JLabel("Podatek dochodowy do zap�acenia:");
		JLabel kwotaDoRekiLabel = new JLabel("Orientacyjna wysoko�� pensji:");
		
		kwotaBruttoNaFaktureLabel.setHorizontalAlignment(JLabel.RIGHT);
		kwotaNettoNaFaktureLabel.setHorizontalAlignment(JLabel.RIGHT);
		zusDoZaplatyLabel.setHorizontalAlignment(JLabel.RIGHT);
		sumaGodzWMiesiacuLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		sumaGodzWMiesiacuTF = new JTextField();
		kwotaNettoNaFaktureTF = new JTextField();
		kwotaBruttoNaFaktureTF = new JTextField();
		zusDoZaplatyTF = new JTextField();
		podatekPitTF = new JTextField();
		kwotaDoRekiTF = new JTextField();
		
		sumaGodzWMiesiacuTF.setEnabled(false);
		kwotaNettoNaFaktureTF.setEnabled(false);
		kwotaBruttoNaFaktureTF.setEnabled(false);
		zusDoZaplatyTF.setEnabled(false);
		podatekPitTF.setEnabled(false);
		kwotaDoRekiTF.setEnabled(false);
		
		panel.add(sumaGodzWMiesiacuLabel);
		panel.add(sumaGodzWMiesiacuTF);
		panel.add(kwotaNettoNaFaktureLabel);
		panel.add(kwotaNettoNaFaktureTF);
		panel.add(kwotaBruttoNaFaktureLabel);
		panel.add(kwotaBruttoNaFaktureTF);
		panel.add(zusDoZaplatyLabel);
		panel.add(zusDoZaplatyTF);
		
		panel.add(podatekPitLabel);
		panel.add(podatekPitTF);
		panel.add(kwotaDoRekiLabel);
		panel.add(kwotaDoRekiTF);
		
		return panel;
	}
	
	public static void main(String[] args) {
		new Gui();
		
	}
}
