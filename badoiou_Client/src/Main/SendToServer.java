package Main;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import HelpFunc.G_main;

public class SendToServer extends Thread {
	private DataOutputStream dos = null;
	private boolean isRun = true;
	private Socket server = null;

	public SendToServer(Socket server) {
		try {
			this.server = server;
			dos = new DataOutputStream(server.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			G_main.logger.error(e.getMessage());
			isRun = false;
		}
	}

	public void send() throws IOException {
		String msg = "hello test word";
		this.dos.writeUTF(msg);
		this.dos.flush();
	}

	public void run() {
		while (isRun) {
			try {
				send();

				try {
					Thread.currentThread().sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					G_main.logger.error(e.getMessage());
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				G_main.logger.error(e.getMessage());
				
				if(this.server!=null)
					try {
						this.dos.close();
						this.server.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						G_main.logger.error(e1.getMessage());
					}
				break;
			}

		}
	}
}
