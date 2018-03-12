package jeevesh;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class NotepadEncryptor extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotepadEncryptor frame = new NotepadEncryptor();
					frame.setVisible(true);
					frame.setTitle("Notepad Encryptor-Ver 2.1");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NotepadEncryptor()
	{
		File file=new File("C://EncrptorData");
		if(!file.exists())
		{
			file.mkdirs();
		}
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mnHelp.add(mntmHelp);
		
		JMenuItem mntmAboutUs = new JMenuItem("About Us");
		mnAbout.add(mntmAboutUs);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JEditorPane editorPane = new JEditorPane();
		contentPane.add(editorPane, BorderLayout.CENTER);
		
		mntmAboutUs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame about=new JFrame("About The Application");
				about.setLayout(null);
				about.setSize(350, 230);
				
				JLabel s=new JLabel("<html>Have a secret?<br>This application would help you keep them!<br><br><i>Custom Message</i><br>This is version 2.1, crafted for demonstration.<br>The secret files are saved in <i><b>.jeevesh</b></i> format inside<br>your C Drive.<br>The only way to read your hidden files is<br>this application.<br><br><i>Developed by Jeevesh</i></html>");
				JScrollPane lab=new JScrollPane(s);
				
				lab.setSize(330,180);
				lab.setLocation(5, 5);
				about.add(lab);
				about.setResizable(false);
				about.setVisible(true);
			}
		});
		
		mntmHelp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame about=new JFrame("Help");
				about.setLayout(null);
				about.setSize(500, 220);
				
				JLabel s=new JLabel("<html><h2>Steps to get started:</h2><ol><li>Just type in your text like you type in notepad.</li><li>Click on <i>Save</i> under File tab.</li><li>Choose a name to give to your file.</li><li>Remember this name. You have saved your File.</li><li>You can close the application and now try to open your saved file.</li><li>To <i>Open</i> a file click Open and enter the file name.</li></ol></html>");
				JScrollPane lab=new JScrollPane(s);
				
				lab.setSize(480,180);
				lab.setLocation(5, 5);
				about.add(lab);
				about.setResizable(false);
				about.setVisible(true);
		
			}
		});
		
		mntmSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFrame save=new JFrame("Give your file a name");
				save.setLayout(null);//no predefined layout
				save.setSize(300, 100);//size on screen
				
				JTextField t= new JTextField();
				t.setSize(200,30);
				t.setLocation(10, 10);
				save.add(t);
				
				JButton b=new JButton("Save");
				b.setSize(70,20);
				b.setLocation(10, 45);
				save.add(b);
				
				b.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e1) {
						try {
							int count=0;
							String S=editorPane.getText();
							char[] encrypted_a=S.toCharArray(),encrypted_b=S.toCharArray();
							
							int z,max=encrypted_a.length;
							
							while(count<max)
								{
								z=((int)encrypted_a[count]);
								z+=31;
									if(z>127)
										z=z-127;
								char q=(char)z;
								encrypted_b[count]=q;								
								count++;
								}
							
							
							String encrypted=new String(encrypted_b);
							
							String p="C://EncrptorData//";
							String p1=p.concat(t.getText());
							String path=p1.concat(".jeevesh");
							
							FileOutputStream fo=new FileOutputStream(path);
							byte[] x=encrypted.getBytes();
							fo.write(x);
							fo.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						finally {
							save.setVisible(false);
						}
					}
				});
				
				save.setResizable(false);//resizable
				save.setVisible(true);// create window
				
			}
		});
		
		mntmOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e1) {
				JFrame open=new JFrame("Enter The Name of File");
				open.setLayout(null);//no predefined layout
				open.setSize(300, 100);//size on screen
				
				JTextField t= new JTextField();
				t.setSize(200,30);
				t.setLocation(10, 10);
				open.add(t);
				
				JButton b=new JButton("Open");
				b.setSize(70,20);
				b.setLocation(10, 45);
				open.add(b);
				
				b.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							
							
							StringBuilder builder = new StringBuilder();
							int ch;
							String p="C://EncrptorData//";
							String p1=p.concat(t.getText());
							String path=p1.concat(".jeevesh");
							FileInputStream fi=new FileInputStream(path);
									while((ch = fi.read()) != -1)
									{
										ch-=31;
										if(ch<=0)
										ch+=127;
									builder.append((char)ch);
									}
							String txt=builder.toString();
							editorPane.setText(txt);
							fi.close();
						} catch (IOException e2) {
							System.out.println("File not found");
						}
						finally {
							open.setVisible(false);
						}	
					}
				});
				
				open.setResizable(false);//resizable
				open.setVisible(true);// create window
			}
		});
	
		mntmNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			NotepadEncryptor frame = new NotepadEncryptor();
			frame.setVisible(true);
				
			}
		});
	}
	

}