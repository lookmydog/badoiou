package Main;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import HelpFunc.G_main;

public class ReceiveFromClient extends Thread{

	private DataInputStream dis = null;
	private boolean isRun = true;
	private Socket client = null;
	
	public ReceiveFromClient(Socket client){
		try {
			this.client = client;
			dis = new DataInputStream(client.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			G_main.logger.error(e.getMessage());
		}
	}
	
	public String receive() throws IOException{
		String msg = dis.readUTF();
		return msg;
	}
	
	public void run(){
		while(isRun){
			try {
				G_main.logger.info(receive());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				G_main.logger.error(e.getMessage());
				if(this.client!=null){
					try {
						this.dis.close();
						this.client.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						G_main.logger.error(e1.getMessage());
					}
				}
				break;
			}
		}
	}
}
