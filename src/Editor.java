import javax.swing.*;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;


public class Editor extends JPanel implements ActionListener{
	File file;
	JButton save= new JButton("Save");
	JButton savec = new JButton("Save and Close");
	JTextArea text = new JTextArea(20,40);
	public Editor(String s){
		file = new File(s);
		save.addActionListener(this);
		savec.addActionListener(this);
		if(file.exists()) {
			try {
				BufferedReader input = new BufferedReader(new FileReader(file));
				String line = input.readLine();
				while(line != null) {
					text.append(line+"\n");
					line = input.readLine();
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		add(save);
		add(savec);
		add(text);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		FileWriter out;
		try {
			out = new FileWriter(file);
			out.write(text.getText());
			out.close();
			if(e.getSource() == savec) {
				Login login = (Login) getParent();
				login.cl.show(login, "fb");
				
			}
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
}
