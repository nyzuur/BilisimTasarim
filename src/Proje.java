
/*
 * Copyright 2011-2017 Niyazi UÐUR

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemColor;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import javax.swing.JPanel;
import javax.swing.JTextArea;




import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class Proje extends JFrame implements MouseListener{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -6292888636174841178L;
	JPanel contentPane;
	JPanel panel;
	boolean deletedAllLinesFlag=false;
	JLabel lblCPU;
	JLabel lblAccA;
	JLabel lblAccB;
	JLabel lblIndexReg;
	JLabel lblProgCount;
	JLabel lblAlu;
	JLabel lblStackPointer;
	JLabel lblControlUnit;
	JLabel lblInstructionRegister;
	private JTextField textIndex;
	private JFormattedTextField textPC;
	private JTextField textSP;
	private JTextField textAccA;
	private JTextField textAccB;
	private JTextField ramSlot1;
	private JTextField ramSlot2;
	private JTextField ramSlot3;
	private JTextField ramSlot4;
	private JTextField ramSlot5;
	private JTextField ramSlot6;
	private JTextField ramSlot7;
	private JTextField ramSlot8;
	private JTextField IC_1;
	private JTextField IC_2;
	private JTextField IC_3;
	private JTextField textIR;
	private List<JTextField> ramSlots;
	private List<JTextField> InstructionCodeFields;
	private HashMap<String, JLabel> conditionCodeRegs;
	
	private JTextArea taCommandArea;
	private int placeOfCode=1;
	
	
	
	//constants
		//instruction widths
	public static final byte iw_1Byte=0x01;
	public static final byte iw_2Byte=0x02;
	public static final byte iw_3Byte=0x03;
		//addressing modes
	public static final byte adModImmediate=0x00;
	public static final byte adModDirect=0x10;
	public static final byte adModIndexed=0x20;
	public static final byte adModExtended=0x30;
	public static final byte adModImplied=0x01;
		//usable accumulator
	public static final byte ICOffsetAccA=0x00;
	public static final byte ICOffsetAccB=0x40;
		//accumulator IDs
	public static final byte ACCA=0x01;
	public static final byte ACCB=0x02;
	private final JTextField tfAddressBus = new JTextField();
	private JTextField tfDataBus;
	private final JTextField tfALU1 = new JTextField();
	private final JTextField tfALU2 = new JTextField();
	private final JTextField tfALUResult = new JTextField();
	private final JLabel lblInput1 = new JLabel("I");
	private final JLabel lblInput2 = new JLabel("I");
	private final JLabel lblOutput;
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proje frame = new Proje();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	
	public Proje() throws ParseException {
		
		
		
		//view generation code
		setTitle("Bili\u015Fim Tasar\u0131m Projesi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 891,600);
		
		getContentPane().setLayout(null);
		
		contentPane = new JPanel(true);
		contentPane.setToolTipText("");
		contentPane.setLayout(null);
		
		setContentPane(contentPane);
		contentPane.setBounds(getBounds());
		Border border = BorderFactory.createLineBorder(Color.black, 2);
		contentPane.setDoubleBuffered(true);
		lblAccA = new JLabel(" ACC-A");
		lblAccA.setBounds(63, 59, 224, 67);
		lblAccA.setOpaque(true);
		lblAccA.setBackground(Color.ORANGE);
		lblAccA.setForeground(Color.BLACK);
		lblAccA.setHorizontalAlignment(SwingConstants.CENTER);

		lblAccB = new JLabel(" ACC-B");
		lblAccB.setBounds(63, 148, 224, 67);
		lblAccB.setOpaque(true);
		lblAccB.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccB.setForeground(Color.BLACK);
		lblAccB.setBackground(Color.ORANGE);

		lblIndexReg = new JLabel("Index Reg.");
		lblIndexReg.setBounds(63, 300, 224, 36);
		lblIndexReg.setOpaque(true);
		lblIndexReg.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndexReg.setBackground(new Color(0, 0, 255));

		lblProgCount = new JLabel("Program Counter");
		lblProgCount.setBounds(63, 353, 224, 36);
		lblProgCount.setOpaque(true);
		lblProgCount.setBackground(new Color(255, 0, 0));
		lblProgCount.setHorizontalAlignment(SwingConstants.CENTER);

		lblAlu = new JLabel("ALU");
		lblAlu.setBounds(63, 232, 320, 47);
		lblAlu.setOpaque(true);
		lblAlu.setBackground(new Color(107, 142, 35));
		lblAlu.setHorizontalAlignment(SwingConstants.CENTER);

		lblStackPointer = new JLabel("Stack Pointer");
		lblStackPointer.setBounds(63, 409, 224, 36);
		lblStackPointer.setOpaque(true);
		lblStackPointer.setBackground(new Color(128, 128, 128));
		lblStackPointer.setHorizontalAlignment(SwingConstants.CENTER);

		lblControlUnit = new JLabel("Control Unit");
		lblControlUnit.setBounds(399, 59, 70, 470);
		lblControlUnit.setOpaque(true);
		lblControlUnit.setBackground(new Color(100, 149, 237));
		lblControlUnit.setHorizontalAlignment(SwingConstants.CENTER);
		
		textIndex = new JTextField();
		textIndex.setBounds(297, 300, 86, 36);
		textIndex.setHorizontalAlignment(SwingConstants.CENTER);
		textIndex.setText("0000");
		textIndex.setColumns(4);
		
		textPC = new JFormattedTextField(new MaskFormatter("HHHH"));
		textPC.setBounds(297, 353, 86, 36);
		textPC.setHorizontalAlignment(SwingConstants.CENTER);
		textPC.setText("0000");
		textPC.setColumns(4);
		
		textSP = new JTextField();
		textSP.setBounds(297, 409, 86, 36);
		textSP.setHorizontalAlignment(SwingConstants.CENTER);
		textSP.setText("0007");
		textSP.setColumns(4);
		
		textAccA = new JTextField();
		textAccA.setBounds(297, 59, 86, 67);
		textAccA.setText("00");
		textAccA.setHorizontalAlignment(SwingConstants.CENTER);
		textAccA.setColumns(2);
		
		textAccB = new JTextField();
		textAccB.setBounds(297, 148, 86, 67);
		textAccB.setHorizontalAlignment(SwingConstants.CENTER);
		textAccB.setText("00");
		textAccB.setColumns(2);
		
		JLabel lblH = new JLabel("H");
		lblH.setBounds(65, 515, 46, 14);
		lblH.setOpaque(true);
		lblH.setBackground(SystemColor.inactiveCaption);
		lblH.setHorizontalAlignment(SwingConstants.CENTER);
		lblH.addMouseListener(this);
		
		JLabel lblC = new JLabel("C");
		lblC.setBounds(345, 515, 46, 14);
		lblC.setOpaque(true);
		lblC.setHorizontalAlignment(SwingConstants.CENTER);
		lblC.setBackground(SystemColor.inactiveCaption);
		lblC.addMouseListener(this);
		
		JLabel lblZ = new JLabel("Z");
		lblZ.setBounds(289, 515, 46, 14);
		lblZ.setOpaque(true);
		lblZ.setHorizontalAlignment(SwingConstants.CENTER);
		lblZ.setBackground(SystemColor.inactiveCaption);
		lblZ.addMouseListener(this);
		
		JLabel lblV = new JLabel("V");
		lblV.setBounds(233, 515, 46, 14);
		lblV.setOpaque(true);
		lblV.setHorizontalAlignment(SwingConstants.CENTER);
		lblV.setBackground(SystemColor.inactiveCaption);
		lblV.addMouseListener(this);
		
		JLabel lblI = new JLabel("I");
		lblI.setBounds(121, 515, 46, 14);
		lblI.setOpaque(true);
		lblI.setHorizontalAlignment(SwingConstants.CENTER);
		lblI.setBackground(SystemColor.inactiveCaption);
		lblI.addMouseListener(this);
		
		JLabel lblN = new JLabel("N");
		lblN.setBounds(177, 515, 46, 14);
		lblN.setOpaque(true);
		lblN.setHorizontalAlignment(SwingConstants.CENTER);
		lblN.setBackground(SystemColor.inactiveCaption);
		lblN.addMouseListener(this);
		
		JLabel lblDataBus = new JLabel("Data Bus");
		lblDataBus.setBounds(502, 310, 331, 16);
		lblDataBus.setForeground(new Color(255, 255, 255));
		lblDataBus.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataBus.setBackground(new Color(0, 0, 0));
		lblDataBus.setOpaque(true);
		lblDataBus.setBorder(null);
		
		JLabel lblAdressBus = new JLabel("Address Bus");
		lblAdressBus.setBounds(502, 59, 331, 16);
		lblAdressBus.setForeground(new Color(255, 255, 255));
		lblAdressBus.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdressBus.setBackground(new Color(0, 0, 0));
		lblAdressBus.setOpaque(true);
		lblAdressBus.setBorder(null);
		
		textIR = new JTextField("00");
		textIR.setHorizontalAlignment(SwingConstants.CENTER);
		
		textIR.setBounds(297, 468, 86, 36);
		textIR.setColumns(10);
		
		lblInstructionRegister = new JLabel("Instruction Register");
		lblInstructionRegister.setBackground(new Color(0, 255, 0));
		lblInstructionRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructionRegister.setBounds(63, 468, 224, 37);
		lblInstructionRegister.setOpaque(true);
		
		JLabel lblCPU = new JLabel("CPU");
		lblCPU.setBounds(55, 30, 437, 507);
		lblCPU.setHorizontalAlignment(SwingConstants.LEFT);
		lblCPU.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblCPU.setForeground(Color.RED);
		lblCPU.setBorder(border);
		lblCPU.setVerticalAlignment(SwingConstants.TOP);
		
		panel = new JPanel();
		panel.setBounds(502, 85, 331, 212);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(8, 1, 0, 0));
		
		ramSlot1 = new JTextField();
		ramSlot1.setToolTipText("Ram Slot 1");
		ramSlot1.setColumns(8);
		ramSlot1.setOpaque(true);
		panel.add(ramSlot1);
		
		ramSlot2 = new JTextField();
		ramSlot2.setToolTipText("Ram Slot 2");
		ramSlot2.setColumns(8);
		ramSlot2.setOpaque(true);
		panel.add(ramSlot2);
		
		
		ramSlot3 = new JTextField();
		ramSlot3.setToolTipText("Ram Slot 3");
		ramSlot3.setColumns(8);
		ramSlot3.setOpaque(true);
		panel.add(ramSlot3);
		
		ramSlot4 = new JTextField();
		ramSlot4.setToolTipText("Ram Slot 4");
		ramSlot4.setColumns(8);
		ramSlot4.setOpaque(true);
		panel.add(ramSlot4);
		
		ramSlot5 = new JTextField();
		ramSlot5.setToolTipText("Ram Slot 5");
		ramSlot5.setColumns(8);
		ramSlot5.setOpaque(true);
		panel.add(ramSlot5);
		
		ramSlot6 = new JTextField();
		ramSlot6.setToolTipText("Ram Slot 6");
		ramSlot6.setColumns(8);
		ramSlot6.setOpaque(true);
		panel.add(ramSlot6);
		
		ramSlot7 = new JTextField();
		ramSlot7.setToolTipText("Ram Slot 7");
		ramSlot7.setColumns(8);
		ramSlot7.setOpaque(true);
		panel.add(ramSlot7);
		
		ramSlot8 = new JTextField();
		ramSlot8.setToolTipText("Ram Slot 8");
		ramSlot8.setColumns(8);
		ramSlot8.setOpaque(true);
		panel.add(ramSlot8);
		
	
		
		taCommandArea = new JTextArea();
		taCommandArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				deletedAllLinesFlag=false;
			}
		});
		taCommandArea.setBounds(622, 339, 257, 120);
		
		JButton btnCommand = new JButton("Komutlar\u0131 g\u00F6nder");
		btnCommand.setBounds(621, 473, 258, 28);
		btnCommand.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(!getCommand()){
					JOptionPane.showMessageDialog(null, "Emir Kodu Alýnamadý","HATA!",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	
		JTextArea textArea = new JTextArea("R\r\nA\r\nM\r\n\r\nS\r\nL\r\nO\r\nT\r\nL\r\nA\r\nR\r\nI");
		textArea.setBounds(843, 81, 30, 212);
		textArea.setBackground(UIManager.getColor("scrollbar"));
		textArea.setFont(new Font("Tahoma", Font.BOLD, 14));
		textArea.setForeground(new Color(51, 51, 102));
		
		textArea.setEditable(false);
		textArea.setEnabled(false);
		IC_3 = new JTextField();
		IC_3.setBounds(582, 343, 30, 28);
		IC_3.setEditable(false);
		IC_3.setEnabled(false);
		IC_3.setColumns(10);
		
		IC_2 = new JTextField();
		IC_2.setBounds(542, 343, 30, 28);
		IC_2.setEnabled(false);
		IC_2.setEditable(false);
		IC_2.setColumns(10);
		
		IC_1 = new JTextField();
		IC_1.setBounds(502, 343, 30, 28);
		IC_1.setEditable(false);
		IC_1.setEnabled(false);
		IC_1.setColumns(10);
		
		InstructionCodeFields = Arrays.asList(IC_1,IC_2,IC_3);
		
		//ramSlots and condition code register arrays;
		ramSlots = Arrays.asList(ramSlot1,ramSlot2,ramSlot3,ramSlot4,ramSlot5,ramSlot6,ramSlot7,ramSlot8);	
		
		conditionCodeRegs= new HashMap<>();
		conditionCodeRegs.put("H", lblH);
		conditionCodeRegs.put("I", lblI);
		conditionCodeRegs.put("N", lblN);
		conditionCodeRegs.put("V", lblV);
		conditionCodeRegs.put("Z", lblZ);
		conditionCodeRegs.put("C", lblC);
		
		
		JButton btnClearRam = new JButton("RESET");
		btnClearRam.setBounds(502, 470, 110, 28);
		btnClearRam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				for(JTextField f: ramSlots){
					f.setText("");
					placeOfCode=1;
					taCommandArea.setText("");
					textPC.setText("0000");
				}
			}
		});
		contentPane.setLayout(null);
		JButton btnRun = new JButton("\u00C7al\u0131\u015Ft\u0131r");
		btnRun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				RunCode();
			}
		});
		btnRun.setBounds(502, 509, 377, 28);
		tfALU1.setBounds(85, 232, 86, 20);
		tfALU1.setColumns(10);
		
		contentPane.add(tfALU1);
		tfALU2.setBounds(85, 259, 86, 20);
		tfALU2.setColumns(10);
		
		contentPane.add(tfALU2);
		tfALUResult.setBounds(289, 245, 86, 20);
		tfALUResult.setColumns(10);
		
		contentPane.add(tfALUResult);
		
		contentPane.add(lblAccA);
		contentPane.add(lblAccB);
		
		lblOutput = new JLabel("Out");
		lblOutput.setHorizontalAlignment(SwingConstants.LEFT);
		lblOutput.setBounds(268, 248, 19, 14);
		contentPane.add(lblOutput);
		lblInput2.setHorizontalAlignment(SwingConstants.LEFT);
		lblInput2.setBounds(73, 262, 11, 14);
		
		contentPane.add(lblInput2);
		lblInput1.setHorizontalAlignment(SwingConstants.LEFT);
		lblInput1.setBounds(73, 235, 11, 14);
		
		contentPane.add(lblInput1);
		contentPane.add(lblIndexReg);
		contentPane.add(lblProgCount);
		contentPane.add(lblAlu);
		contentPane.add(lblStackPointer);
		contentPane.add(lblControlUnit);
		contentPane.add(textIndex);
		contentPane.add(textPC);
		contentPane.add(textSP);
		contentPane.add(textAccA);
		contentPane.add(textAccB);
		
		
		contentPane.add(textIR);
		contentPane.add(lblH);
		contentPane.add(lblC);
		contentPane.add(lblZ);
		contentPane.add(lblV);
		contentPane.add(lblI);
		contentPane.add(lblN);
		tfAddressBus.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfAddressBus.setBackground(Color.WHITE);
		tfAddressBus.setBounds(711, 59, 78, 20);
		tfAddressBus.setOpaque(true);
		tfAddressBus.setColumns(10);
		
		contentPane.add(tfAddressBus);
		
		tfDataBus = new JTextField();
		tfDataBus.setHorizontalAlignment(SwingConstants.CENTER);
		tfDataBus.setBackground(Color.WHITE);
		tfDataBus.setBounds(512, 308, 86, 20);
		tfDataBus.setOpaque(true);
		tfDataBus.setColumns(10);
		contentPane.add(tfDataBus);
		contentPane.add(lblDataBus);
		contentPane.add(lblAdressBus);
		
		
		contentPane.add(lblInstructionRegister);
		contentPane.add(lblCPU);
		contentPane.add(panel);
		contentPane.add(taCommandArea);
		contentPane.add(btnCommand);
		contentPane.add(textArea);
		contentPane.add(IC_3);
		contentPane.add(IC_2);
		contentPane.add(IC_1);
		contentPane.add(btnClearRam);
		contentPane.add(btnRun);
		
		
	}	
	
	public void RunCode() {
		
		while(true){
			int PC = getPCValue();
			if(PC>7){
				int dialogResult = JOptionPane.showConfirmDialog (this, "Program Counter Sýfýrlansýn mý? \n Hayýr derseniz program duracaktýr...","Olmayan bir RAM bölgesine eriþmeye çalýþtýnýz!!",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
				if(dialogResult==JOptionPane.YES_OPTION){ 
					textPC.setText("0000");  
					PC=0;
				}
				
				else if(dialogResult == JOptionPane.NO_OPTION) return;
			}
			
			
			LoadByteToInstructionRegister(getPCValue());
			
			System.out.println(textIR.getText()+":currentirvalue");
			System.out.println(getPCValue() +":currentPCvalue," +ramSlots.get(getPCValue()).getText() + ":pointedramslottext"+ ramSlots.get(getPCValue()).getText().isEmpty()+":isemptyr");
			
			RunInstruction();
			if(ramSlots.get(getPCValue()+1).getText().isEmpty()){
				break;	
			}
			else{
				IncrementPC();
				continue;
			}
		
		}
		this.paintAll(getGraphics());
		return;
	}


	private void RunInstruction() {
		int currentPC= Integer.parseInt(textPC.getText(),16);
		if(currentPC>7){
			int dialogResult = JOptionPane.showConfirmDialog (this, "Program Counter Sýfýrlansýn mý? \n Hayýr derseniz program duracaktýr...","Olmayan bir RAM bölgesine eriþmeye çalýþtýnýz!!",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE);
			if(dialogResult==JOptionPane.YES_OPTION){ 
				textPC.setText("0000");  
				currentPC=0;
			}
			
			else if(dialogResult == JOptionPane.NO_OPTION) return;
		}
		int currentIRValue =Integer.parseInt(textIR.getText(),16);
		if(currentIRValue!=0){
			IncrementPC();
			ParsedInstruction i= ParsedInstruction.parse((byte) currentIRValue);
			if(i.instructionType!=0){
				
				
				switch (i.addressingMode) {
				case adModImmediate:
					LoadByteFromMemory(getPCValue(), false);
					executeAnALUOperation((i.usingAcc==0x01), i.instructionType, textIndex);
					break;
				case adModDirect:
					LoadByteFromMemory(getPCValue(),false);
					LoadByteFromMemory(getIndexValue(), false);
					executeAnALUOperation(i.usingAcc==0x01, i.instructionType, textIndex);
					break;
				case adModIndexed:
					//TODO indexed addressing support will be added
					//LoadByteFromMemory(Integer.parseInt(te, radix), toIndexH);
					break;
				case adModImplied:
					executeAnALUOperation(i.usingAcc==0x01,i.instructionType,null);
					break;
				default:
					break;
				}
			}else{
				textIR.setText("00");
				JOptionPane.showMessageDialog(this, "Instruction Code is invalid");
				return;
			}
			
			
		
		}else {
			return;
		}
		
	}
	
	private void LoadByteToInstructionRegister(int position) {
		//pc'yi al, kontrol buss'a götür.. 
		GoToXY(textPC, lblControlUnit.getBounds());
		//ordan addressbuss'a koy
		GoToXY(lblControlUnit, tfAddressBus.getBounds(), textPC.getText());
		tfAddressBus.setText(textPC.getText());
		JTextField tfRamSlot = ramSlots.get(position);
		//ordan ram bölgesini kýrmýzý yak,
		tfRamSlot.setBackground(new Color (255,99,71));
		tfRamSlot.paint(tfRamSlot.getGraphics());
		JTextField tfFoo= tfRamSlot;
		tfFoo.setLocation(addPoints(panel.getLocation(), tfRamSlot.getLocation()));
		//ordan götür data buss'a 
		GoToXY(tfFoo, tfDataBus.getBounds());
		tfDataBus.setText(tfRamSlot.getText());
		//sonra kontrol buss'a  götür..
		GoToXY(tfDataBus, lblControlUnit.getBounds());
		GoToXY(lblControlUnit, textIR.getBounds(),tfDataBus.getText());
		tfRamSlot.setBackground(Color.WHITE);
		tfRamSlot.paint(tfRamSlot.getGraphics());

		//ordan instruction register'a koy
		textIR.setText(tfDataBus.getText());
		
	}

	private void LoadByteFromMemory(int position,boolean toIndexH) {
		
		
		GoToXY(textPC, lblControlUnit.getBounds());
		//ordan addressbuss'a koy
		GoToXY(lblControlUnit, tfAddressBus.getBounds(), textPC.getText());
		tfAddressBus.setText(textPC.getText());
		JTextField tfRamSlot = ramSlots.get(position);
		//ordan ram bölgesini kýrmýzý yak,
		tfRamSlot.setBackground(new Color (255,99,71));
		tfRamSlot.paint(tfRamSlot.getGraphics());
		JTextField tfFoo= tfRamSlot;
		tfFoo.setLocation(addPoints(panel.getLocation(), tfRamSlot.getLocation()));
		//ordan götür data buss'a 
		GoToXY(tfFoo, tfDataBus.getBounds());
		tfDataBus.setText(tfRamSlot.getText());
		//sonra kontrol buss'a  götür..
		GoToXY(tfDataBus, lblControlUnit.getBounds());
		GoToXY(lblControlUnit, textIndex.getBounds(),tfDataBus.getText());
		
		//ordan index register'a koy
		if(toIndexH){
			textIndex.setText(tfDataBus.getText()+textIndex.getText().substring(2, 3));
		}else{
			textIndex.setText("00"+tfDataBus.getText());
		}
		
		tfRamSlot.setBackground(Color.WHITE);
		tfRamSlot.paint(tfRamSlot.getGraphics());
		
		
		
	}

	public boolean getCommand(){
		if(deletedAllLinesFlag){
			return true;
		}
		if(taCommandArea.getText()==null){
			return false;
		}
		String[] commands;
		try {
			commands = taCommandArea.getText().split("\n");
		} catch (IllegalArgumentException e) {
			commands= new String[]{taCommandArea.getText()};
		}
		
		//each command line
		String command = commands[0];
			String[] commandParts;
			if(command!= null){
				if(command.contains(" "))
					commandParts = command.split(" ");
				else{
					commandParts= new String[]{command};
				}
				
			
			}
			else return false;
			//implied addressing or not
			if(commandParts[0].length()==4){
				//using accA(implied)
				if(commandParts[0].endsWith("A")){
					byte[] instructions = getICAndInstructions(command,true, ICOffsetAccA);
					if(instructions!=null){
						if(placeOfCode+instructions.length>9){
							JOptionPane.showMessageDialog(null, "Bellek Alaný yeterli deðil");
							return true;
						}
						deleteProccessedLine();
						return WriteToRam(instructions) && getCommand();
					}
				}
				//using accB(implied)
				else if(commandParts[0].endsWith("B")){
					byte[] instructions = getICAndInstructions(command,true, ICOffsetAccB);
					if(instructions!=null){
						if(placeOfCode+instructions.length>9){
							JOptionPane.showMessageDialog(null, "Bellek Alaný yeterli deðil");
							return true;
						}
						deleteProccessedLine();
						return WriteToRam(instructions) && getCommand();
					}
				}else 
					return false;	
			//not implied addressing(direct, indexed, extended, immediate)
			}else if(commandParts[0].length()==3){
				byte[] instructions = getICAndInstructions(command,false, (byte) 0);
				if(instructions!=null){
					if(placeOfCode+instructions.length>9){
						JOptionPane.showMessageDialog(null, "Bellek Alaný yeterli deðil");
						return true;
					}
					deleteProccessedLine();
					return WriteToRam(instructions) && getCommand();
				}
			}else{
				return false;
			}
		
		return false;
	}
	
	private boolean WriteToRam(byte[] instructions) {
		int mPlaceOfCode=placeOfCode;
		int i=0;
		for(JTextField tf: InstructionCodeFields) tf.setText("");
		
		for(byte b : instructions){
			String hexValue = Integer.toHexString(b).toUpperCase();
			hexValue = hexValue.substring(hexValue.length()-2);
			JTextField instruction= InstructionCodeFields.get(i);
			JLabel lblIC= new JLabel(hexValue);
			lblIC.setBounds(taCommandArea.getX(), taCommandArea.getY(), 62, 28);
			this.GoToXY(lblIC, instruction.getBounds());
			instruction.setText(hexValue);
			i++;
		}
		for(JTextField tf: InstructionCodeFields){
			if(tf.getText().equals(new String(""))) break;
			JTextField tfRamSlot=ramSlots.get(mPlaceOfCode-1);
			Rectangle r = new Rectangle();
			r = this.addRectangles(panel.getBounds(),tfRamSlot.getBounds());
			this.GoToXY(tf, r);
			tfRamSlot.setBackground(new Color (255,99,71));
			tfRamSlot.paint(tfRamSlot.getGraphics());
			tfRamSlot.setText(tf.getText());
			this.delay(500);
			tfRamSlot.setBackground(Color.WHITE);
			tfRamSlot.paint(tfRamSlot.getGraphics());
			
			mPlaceOfCode++;
			
		}
		placeOfCode+=instructions.length;
		
		return true;
		
		
	}
	private void deleteProccessedLine(){
		int indexOfLine = taCommandArea.getText().indexOf('\n');
		if(indexOfLine<0){
			taCommandArea.setText("");
			deletedAllLinesFlag=true;
		}
		else{
			taCommandArea.setText(taCommandArea.getText().substring(indexOfLine+1));
		}
	}

	private byte[] getICAndInstructions(String instruction, Boolean UsingAcc, byte AccOffset){
		InstructionWidthAndAddressingMode i = getProgramLengthAndAddressingMode(instruction);
		int programLength = i.ProgramLength;
		byte addressingMode = i.AddressingMode;
		if(programLength==0) return null;
		byte[] instructions= new byte[programLength];
		
		
		HashMap<String, Byte> BaseICs = new HashMap<>();
		BaseICs.put("LDA", (byte) 0x86);
		BaseICs.put("SUB", (byte) 0x80);
		BaseICs.put("INC", (byte) 0x4c);
		String[] commandParts= instruction.split(" ");
		String Instruction= commandParts[0];
			if(UsingAcc){
				if(Instruction.startsWith("LDA")){
					instructions[0] = (byte) (BaseICs.get("LDA")+ AccOffset+ addressingMode);
					switch(addressingMode){
						case adModDirect:
							commandParts[1]=commandParts[1].replace("H", "");
							instructions[1]= Byte.parseByte(commandParts[1],16);
							break;
						case adModExtended:
							throw new IllegalArgumentException("Ram Slots Not supporting this addressing mod");
						case adModImmediate:
							commandParts[1]=commandParts[1].replace("H", "");
							commandParts[1]=commandParts[1].replace("#", "");
							instructions[1]= Byte.parseByte(commandParts[1],16);
							break;
						case adModIndexed:
							commandParts[1]=commandParts[1]=commandParts[1].split(",")[0];
							commandParts[1]=commandParts[1].replace("H", "");
							instructions[1]= Byte.parseByte(commandParts[1],16);
							break;
						default: 
							return null;
					}
				}
				else if (Instruction.startsWith("SUB")){
					instructions[0] = (byte) (BaseICs.get("SUB")+ AccOffset+ addressingMode);
					switch(addressingMode){
					case adModDirect:
						commandParts[1]=commandParts[1].replace("H", "");
						instructions[1]= Byte.parseByte(commandParts[1],16);
						break;
					case adModExtended:
						throw new IllegalArgumentException("Ram Slots Not supporting this address");
					case adModImmediate:
						commandParts[1]=commandParts[1].replace("H", "");
						commandParts[1]=commandParts[1].replace("#", "");
						instructions[1]= Byte.parseByte(commandParts[1],16);
						break;
					case adModIndexed:
						commandParts[1]=commandParts[1]=commandParts[1].split(",")[0];
						commandParts[1]=commandParts[1].replace("H", "");
						instructions[1]= Byte.parseByte(commandParts[1],16);
						break;
					default:
						return null;
					}	
				}
				else if (Instruction.startsWith("INC")){
					instructions[0] = (byte) (BaseICs.get("INC")+ AccOffset+ addressingMode);
					switch(addressingMode){
					case adModExtended:
						throw new IllegalArgumentException("Ram Slots Not supporting this address");
					case adModIndexed:
						commandParts[1]=commandParts[1]=commandParts[1].split(",")[0];
						commandParts[1]=commandParts[1].replace("H", "");
						instructions[1]= Byte.parseByte(commandParts[1],16);
						break;
					case adModImplied:
						switch(AccOffset){
						case ICOffsetAccA:
							instructions[0]=BaseICs.get("INC");
							break;
						case ICOffsetAccB:
							instructions[0]=(byte) (BaseICs.get("INC") + 0x10);
							break;
						default:
								return null;
						}
						break;
					default: 
						return null;
					}	
					
					
				}
			}
			//non using accumulator
			else {
				if(Instruction.startsWith("LDA")){
					instructions[0] = (byte) (BaseICs.get("LDA")+ addressingMode);
					switch(addressingMode){
						case adModDirect:
							commandParts[1]=commandParts[1].replace("H", "");
							instructions[1]= Byte.parseByte(commandParts[1],16);
							break;
						case adModExtended:
							throw new IllegalArgumentException("Ram Slots Not supporting this address");
						case adModImmediate:
							commandParts[1]=commandParts[1].replace("H", "");
							commandParts[1]=commandParts[1].replace("#", "");
							instructions[1]= Byte.parseByte(commandParts[1],16);
							break;
						case adModIndexed:
							commandParts[1]=commandParts[1]=commandParts[1].split(",")[0];
							commandParts[1]=commandParts[1].replace("H", "");
							instructions[1]= Byte.parseByte(commandParts[1],16);
							break;
						default: 
							return null;
					}
				}
				else if (Instruction.startsWith("SUB")){
					instructions[0] = (byte) (BaseICs.get("SUB")+ addressingMode);
					switch(addressingMode){
					case adModDirect:
						commandParts[1]=commandParts[1].replace("H", "");
						instructions[1]= Byte.parseByte(commandParts[1],16);
						break;
					case adModExtended:
						throw new IllegalArgumentException("Ram Slots Not supporting this address");
					case adModImmediate:
						commandParts[1]=commandParts[1].replace("H", "");
						commandParts[1]=commandParts[1].replace("#", "");
						instructions[1]= Byte.parseByte(commandParts[1],16);
						break;
					case adModIndexed:
						commandParts[1]=commandParts[1]=commandParts[1].split(",")[0];
						commandParts[1]=commandParts[1].replace("H", "");
						instructions[1]= Byte.parseByte(commandParts[1],16);
						break;
					default:
						return null;
					}	
				}
				else if (Instruction.startsWith("INC")){
					instructions[0] = (byte) (BaseICs.get("INC")+ addressingMode);
					switch(addressingMode){
					case adModExtended:
						throw new IllegalArgumentException("Ram Slots Not supporting this address");
					case adModIndexed:
						commandParts[1]=commandParts[1]=commandParts[1].split(",")[0];
						commandParts[1]=commandParts[1].replace("H", "");
						instructions[1]= Byte.parseByte(commandParts[1],16);
						break;
					default:
						return null;
					}	
					
					
				}
				
				
			}
		
		return instructions;
		
	}

	private InstructionWidthAndAddressingMode getProgramLengthAndAddressingMode(String instruction) {
		instruction=instruction.trim();
		if(instruction.startsWith("LDA")){
			//immediate addressing
			if(instruction.contains("#")){
				return new InstructionWidthAndAddressingMode(iw_2Byte, adModImmediate);
			//direct addressing
			}else if(instruction.split(" ")[1].length()==3){
				return new InstructionWidthAndAddressingMode(iw_2Byte, adModDirect);
			//extended addressing
			}else if(instruction.split(" ")[1].length()==5){
				return new InstructionWidthAndAddressingMode(iw_3Byte, adModExtended);
			//indexed addressing
			}else if(instruction.contains(",")){
				return new InstructionWidthAndAddressingMode(iw_2Byte, adModIndexed);
			}else return new InstructionWidthAndAddressingMode((byte)0,(byte)0);
			
		}
		else if(instruction.startsWith("INC")){
			//implied addressing
			if(instruction.length()==4){
				return new InstructionWidthAndAddressingMode(iw_1Byte, adModImplied);
			//indexed addressing
			}else if(instruction.contains(",")){
				return new InstructionWidthAndAddressingMode(iw_2Byte, adModIndexed);
			//extended addressing
			}else if(instruction.split(" ")[1].length()==5){
				return new InstructionWidthAndAddressingMode(iw_3Byte, adModExtended);
			
			}else 
				return new InstructionWidthAndAddressingMode((byte)0,(byte)0);
		}	
		
		else if(instruction.startsWith("SUB")){
			//immediate addressing
			if(instruction.contains("#")){
				return new InstructionWidthAndAddressingMode(iw_2Byte, adModImmediate);
			//direct addressing
			}else if(instruction.split(" ")[1].length()==3){
				return new InstructionWidthAndAddressingMode(iw_2Byte, adModDirect);
			//extended addressing
			}else if(instruction.split(" ")[1].length()==5){
				return new InstructionWidthAndAddressingMode(iw_2Byte, adModExtended);
			//indexed addressing
			}else if(instruction.contains(",")){
				return new InstructionWidthAndAddressingMode(iw_3Byte, adModIndexed);
			}else 
				return new InstructionWidthAndAddressingMode((byte)0,(byte)0);	
		}
		return  new InstructionWidthAndAddressingMode((byte)0,(byte)0);	
	
	}
	public void GoToXY(JComponent c, int x, int y,String txtForlabels){
		boolean isTextField = c instanceof JTextField;
		boolean isLabel = c instanceof JLabel;
		JTextField tf = null;
		JLabel lbl = null;
		int currentX;
		int currentY;
		boolean toLeft;
		boolean toUp ;
		int width = c.getWidth();
		int height = c.getHeight();
		String s;
			 if (isLabel) {
				 lbl=(JLabel) c;
					currentX=lbl.getX();
					currentY=lbl.getY();
					toLeft = currentX>x;
					toUp = currentY>y;
					s = txtForlabels;
				 
			 }	else if(isTextField){
				 tf=(JTextField) c;
					currentX=tf.getX();
					currentY=tf.getY();
					toLeft = currentX>x;
					toUp = currentY>y;
					s = tf.getText();
			 }else 
				 return;
			JLabel animationLbl= new JLabel(s);
			animationLbl.setSize(animationLbl.getPreferredSize());
			animationLbl.setHorizontalAlignment(SwingConstants.CENTER);
			animationLbl.setOpaque(true);
			animationLbl.setBackground(new Color(255, 99, 71));
			animationLbl.setVisible(true);
			contentPane.add(animationLbl,0);
			
			int i,j;
			for(i = currentX ; (toLeft) ? i>x : i<x;){
				//animation begins
				animationLbl.setLocation(i, currentY);
				contentPane.paintImmediately(i-2, currentY-2, width+10, height+10);
				delay(1);
				if(toLeft) i--; else i++;
			}
			delay(10);
			for(j = currentY ; (toUp)? j>y: j<y;){
				//animation begins
				animationLbl.setLocation(x, j);
				contentPane.paintImmediately(x-2, j-2, width+10, height+10);	
				delay(1);
				if(toUp) j--; else j++;
			}
			
			
			animationLbl.setVisible(false);
			contentPane.remove(animationLbl);
			paintAll(getGraphics());
		
		
		
	}
	public void GoToXY(JComponent c , Rectangle r,String txt){
		GoToXY(c, r.x, r.y,txt);
		
	}
	public void GoToXY(JComponent c, Rectangle r){
		GoToXY(c, r,null);
		
		
	}
	public void delay(long milisec){
		try {
		
			Thread.sleep(milisec*6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	public Rectangle addRectangles(Rectangle r1,Rectangle r2) {
		return new Rectangle(r1.x+r2.x,
				r1.y+r2.y,
				r1.width+r2.width,
				r1.height+r2.height);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if(conditionCodeRegs.containsValue(o)){
			JLabel lbl= (JLabel) o;
			if(lbl.getBackground() == SystemColor.inactiveCaption){
				lbl.setBackground(SystemColor.activeCaption);
			}else{
				lbl.setBackground(SystemColor.inactiveCaption);
				
			}
		} 
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private int getPCValue(){
		
		return Integer.parseInt(textPC.getText(), 16);
		
	}
	private int getIndexValue(){
		
		return Integer.parseInt(textIndex.getText(), 16);
		
	}
	
	private void IncrementPC() {
		Integer currentValue=Integer.parseInt(textPC.getText(), 16);
		if(currentValue==7){
			JOptionPane.showMessageDialog(this, "Hafýza'nýn sonuna gelindi", "!!!!!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else{
			
			textPC.setText(String.format("%04d", ++currentValue));
			
			
		}
		
		
	}
	private Point addPoints(Point one, Point two){
		return new Point(one.x+two.x,
						one.y+two.y);
		
	}
	private int executeAnALUOperation(boolean isACCA, int instructionType, JTextField tf){
		if(isACCA){

			if(instructionType==1){
				GoToXY(textAccA, tfALU1.getBounds());
				tfALU1.setText(textAccA.getText());
				
				//tf'den veriyi aluya getir..
				GoToXY(tf, tfALU2.getBounds());
				tfALU2.setText(tf.getText());
				
				int input1=Integer.parseInt(tfALU1.getText(),16);
				int input2=Integer.parseInt(tfALU2.getText(),16);
				tfALUResult.setText(String.format("%02x",input1-input2));
				GoToXY(tfALUResult, textAccA.getBounds());
				String s = tfALUResult.getText();
				s=s.substring(s.length()-2, s.length());
				textAccA.setText(s);
				this.paintAll(getGraphics());
				return input1-input2;
				
				
				
			}else if(instructionType==3){
				//A register'inden aluya veriyi al gel
				GoToXY(textAccA, tfALU1.getBounds());
				tfALU1.setText(textAccA.getText());
				
				int input1 =Integer.parseInt( tfALU1.getText(), 16);
				tfALUResult.setText(Integer.toHexString(input1+1));
				String s = tfALUResult.getText();
				s=s.substring(s.length()-2, s.length());
				GoToXY(tfALUResult, textAccA.getBounds());
				textAccA.setText(s);
				
				this.paintAll(getGraphics());
				return ++input1;
				
			}else if(instructionType==2){
				GoToXY(tf, textAccA.getBounds());
				String s = tf.getText();
				s=s.substring(s.length()-2, s.length());
				textAccA.setText(s);
				return Integer.parseInt(s, 16);
				
			}
			
			/*//A register'inden aluya veriyi al gel
			GoToXY(textAccA, tfALU1.getBounds());
			tfALU1.setText(textAccA.getText());
			
			//tf'den veriyi aluya getir..
			GoToXY(tf, tfALU2.getBounds());
			tfALU2.setText(tf.getText());
			
			//iþlemin yapýlýþýný göster
			//sonucu döndür
			*/
		}else {
			if(instructionType==1){
				GoToXY(textAccB, tfALU1.getBounds());
				tfALU1.setText(textAccA.getText());
				
				//tf'den veriyi aluya getir..
				GoToXY(tf, tfALU2.getBounds());
				tfALU2.setText(tf.getText());
				
				int input1=Integer.parseInt(tfALU1.getText(),16);
				int input2=Integer.parseInt(tfALU2.getText(),16);
				tfALUResult.setText(String.format("%02s",Integer.toHexString(input1-input2)));
				GoToXY(tfALUResult, textAccB.getBounds());
				String s = tfALUResult.getText();
				s=s.substring(s.length()-2, s.length());
				textAccB.setText(s);
				this.paintAll(getGraphics());
				return input1-input2;
				
				
				
			}else if(instructionType==3){
				
				GoToXY(textAccA, tfALU1.getBounds());
				tfALU1.setText(textAccA.getText());
				
				int input1 =Integer.parseInt( tfALU1.getText(), 16);
				tfALUResult.setText(Integer.toHexString(input1+1));
				String s = tfALUResult.getText();
				s=s.substring(s.length()-2, s.length());
				GoToXY(tfALUResult, textAccB.getBounds());
				textAccB.setText(s);
				
				
				this.paintAll(getGraphics());
				return ++input1;
				
				
			}
			else if(instructionType==2){
				GoToXY(tf, textAccB.getBounds());
				String s = tf.getText();
				s=s.substring(s.length()-2, s.length());
				textAccA.setText(s);
				return Integer.parseInt(s, 16);
				
			}
			
		}
		return (Integer) null;
		
		
	}
}



	
	
