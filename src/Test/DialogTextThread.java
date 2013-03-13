package Test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DialogTextThread extends Thread {

	private JDialog dialog;
	private JLabel promptLabel;
	private JLabel progressLabel;
	private JButton cancelBtn;

	private JFrame parentFrame;
	private String prompt;
	private String title;

	public static void main(String[] args) {
		final DialogTextThread dialogTextThread = new DialogTextThread(null,
				"", "Create Progress");
		Thread thread = new Thread() {
			public void run() {
				for (int i = 0; i < 10000; i++) {
					System.out.println(i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (i % 100 == 0) {
						dialogTextThread.updatePrompt(String.valueOf(i));
						dialogTextThread.updateProgress(String.valueOf(i));
					}
				}
				// dialogTextThread.dispose();
			}
		};

		dialogTextThread.start();
		thread.start();
	}

	public DialogTextThread(JFrame parentFrame, String prompt, String title) {
		this.parentFrame = parentFrame;
		this.prompt = prompt;
		this.title = title;
		initDialog();
	}

	public void initDialog() {
		promptLabel = new JLabel("   进程 : " + prompt);
		promptLabel.setPreferredSize(new Dimension(255, 22));
		progressLabel = new JLabel(" ");
		progressLabel.setPreferredSize(new Dimension(255, 22));

		JPanel labelPanel = new JPanel();
		labelPanel.setBounds(100, 100, 300, 200);
		labelPanel.setPreferredSize(new Dimension(260, 67));
		labelPanel.setLayout(new BorderLayout());
		labelPanel.add(promptLabel, BorderLayout.CENTER);
		labelPanel.add(progressLabel, BorderLayout.SOUTH);

		JLabel tempLabel = new JLabel();
		tempLabel.setPreferredSize(new Dimension(90, 2));
		cancelBtn = new JButton("Cancel");
		cancelBtn.setEnabled(false);
		cancelBtn.addActionListener(new CancelAction());

		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(90, 67));
		buttonPanel.add(tempLabel);
		buttonPanel.add(cancelBtn);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(370, 116));
		panel.add(labelPanel);
		panel.add(buttonPanel);

		dialog = new JDialog(parentFrame, title, true);
		dialog.getContentPane().add(panel);
	}

	public void run() {
		dialog.setVisible(true);
	}

	public void dispose() {
		dialog.setVisible(false);
	}

	public void updateButtonStatus(boolean flag) {
		cancelBtn.setEnabled(flag);
	}

	public void updatePrompt(String prompt) {
		promptLabel.setText("   进程 : " + prompt);
	}

	public void updateProgress(String progress) {
		progressLabel.setText("   " + progress);
	}

	public class CancelAction implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			dialog.setVisible(false);
		}
	}
}