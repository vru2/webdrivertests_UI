package testScriptsHQOthers;

/*import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
*/
public class Connecting_Deploymentserver {

	public static void main(String[] args) {/*
		// TODO Auto-generated method stub
		String host="172.17.14.25";
		 String user="root";
		 String password="password";
		 String command1="ls -ltr\t";

		 try{

		    java.util.Properties config = new java.util.Properties(); 
		    config.put("StrictHostKeyChecking", "no");
		    JSch jsch = new JSch();
		    Session session=jsch.getSession(user, host,22);
		    session.setPassword(password);
		   session.setConfig(config);
		    session.connect();
		    System.out.println("Connected");

		    ChannelExec channel=(ChannelExec) session.openChannel("exec");
		    BufferedReader in=new BufferedReader(new InputStreamReader(channel.getInputStream()));

		  channel.setCommand(command);
		    channel.connect();
		           String msg=null;
		           while((msg=in.readLine())!=null){
		            System.out.println(msg);
		           }                  
		                channel.disconnect();
		                session.disconnect();
		                System.out.println("DONE");
		            }catch(Exception e){
		                e.printStackTrace();
		            }

		        */}
	}


