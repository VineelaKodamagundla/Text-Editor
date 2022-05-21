import javax.swing.*;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class FileBrowser extends JPanel implements ActionListener{
	JLabel label = new JLabel("File List: ");
	JButton newFile = new JButton("New File");
	JButton open = new JButton("Open");
	JTextField newFileTF = new JTextField(10);
	ButtonGroup bg;
	File directory;
	public FileBrowser(String dir) {
		directory = new File(dir);
		directory.mkdir();
		JPanel FileList = new JPanel(new GridLayout(directory.listFiles().length+3,1));
		FileList.add(label);
		bg = new ButtonGroup();
		for(File file : directory.listFiles()) {
			JRadioButton radio = new JRadioButton(file.getName());
			radio.setActionCommand(file.getName());
			bg.add(radio);
			FileList.add(radio);
		}
		JPanel newP = new JPanel();
		newP.add(newFileTF);
		newP.add(newFile);
		newFile.addActionListener(this);
		open.addActionListener(this);
		FileList.add(open);
		FileList.add(newP);
		add(FileList);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Login login = (Login) getParent();
		if(e.getSource() == open) {
			login.add(new Editor(directory.getName()+"\\"+bg.getSelection().getActionCommand()),"editor");
			login.cl.show(login, "editor");
		}
		if(e.getSource() == newFile) {
			String file = directory.getName()+"\\"+newFileTF.getText()+".txt";
			if(newFile.getText().length()>0 && !(new File(file).exists())) {
				login.add(new Editor (file), "editor");
				login.cl.show(login, "editor");
			}
		}
	}
}
