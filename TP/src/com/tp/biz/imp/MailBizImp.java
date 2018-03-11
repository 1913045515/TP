package com.tp.biz.imp;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.tp.biz.MailBiz;
import com.tp.tools.Constant;
import com.tp.tools.SmtpAuth;
public class MailBizImp implements MailBiz {
	private String sender;    
	private String password;  
	private String smtpServer; 
	private Properties proper;
    private MimeMessage mimeMessage = null;   
    private String saveAttachPath = ""; 
    private StringBuffer bodytext = new StringBuffer();
    private String dateformat = "yy-MM-dd HH:mm"; 
    private Properties props=null;
    private Session session=null;
    private URLName urln=null;
    private Store store=null;
    private Folder folder=null;
	public MailBizImp(){
		proper = new Properties();
		sender = Constant.sender;  
		password = Constant.password;  
		smtpServer = Constant.smtpServer; 
		props = System.getProperties();  
        props.put("mail.smtp.host", Constant.smtpServer);  
        props.put("mail.smtp.auth", Constant.auth);  
        session = Session.getDefaultInstance(props, null);  
        urln = new URLName(Constant.storeProtocol, Constant.smtpServer, 110, null,  
        		Constant.sender, Constant.password);  
        try {
        	store = session.getStore(urln);  
            store.connect();
            folder = store.getFolder("INBOX");  
			folder.open(Folder.READ_ONLY);
		} catch (MessagingException e) {		
			e.printStackTrace();
		}  
		init();
	}
	public void init(){
		proper.put("mail.smtp.host", smtpServer); 
	    proper.put("mail.smtp.auth", Constant.auth); 
	    proper.put("mail.smtp.port", Constant.port); 
	    proper.put("mail.transport.protocol", Constant.transportProtocol); 
	    proper.put("mail.store.protocol",Constant.storeProtocol);   
	}
	@Override
	public boolean sendMail(String usersId,String themes,String content) {		
	        String fileAttachment = "";   	       
	        InternetAddress[] receiveAddress = new InternetAddress[1];      
	        try {      
	            receiveAddress[0] = new InternetAddress(usersId);      
	        } catch (AddressException e) {          
	            e.printStackTrace();      
	        }      
	        SmtpAuth sa = new SmtpAuth();      
	        sa.setUserinfo(sender, password);      
	        Session session = Session.getInstance(proper, sa);      
	        session.setPasswordAuthentication(new URLName(smtpServer), sa      
	                .getPasswordAuthentication());      
	        MimeMessage sendMess = new MimeMessage(session);      
	        MimeBodyPart mbp = new MimeBodyPart();      
	        MimeMultipart mmp = new MimeMultipart();      
	        try {      
	            mbp.setContent(content, "text/html; charset=UTF-8");      
	            mmp.addBodyPart(mbp);      
	            if(fileAttachment!=null&&fileAttachment!=""){      
	                DataSource source = new FileDataSource(fileAttachment);      
	                String name = source.getName();      
	                mbp = new MimeBodyPart();      
	                mbp.setDataHandler(new DataHandler(source));      
	                mbp.setFileName(name);      
	                mmp.addBodyPart(mbp);      
	            }      
	            sendMess.setSubject(themes);      
	            sendMess.setContent(mmp);    
	            String address=Constant.sender+"@"+Constant.smtpServer;
	            sendMess.setSender(new InternetAddress(address));
	            sendMess.setRecipients(Message.RecipientType.TO, receiveAddress);
	            Transport.send(sendMess);   
	            return true;
	        } catch (MessagingException ex) {      
	            ex.printStackTrace();    
	            return false;
	        }      
	}

	@Override
	public boolean sendMail(String[] usersId, String themes, String content) {
		boolean flag=true;
		for(int i=0;i<usersId.length;i++){
			flag=sendMail(usersId[i],themes,content);
			if(flag==false){
				return false;
			}
		}		
		return true;
	}
	@Override
	public List<Map<String, Object>> getMail() throws Exception {
		List<Map<String, Object>>result=new ArrayList<Map<String,Object>>();
        Message message[] = folder.getMessages();  
        for (int i = 0; i < message.length; i++) {  
        	Map<String,Object>map=new HashMap<String, Object>();
        	MailBizImp pmm = new MailBizImp((MimeMessage) message[i]);  
            pmm.getMailContent((Part) message[i]);
            pmm.setDateFormat("yy-MM-dd HH:mm");  
            map.put("subject", pmm.getSubject());
            map.put("from", pmm.getFrom());
            map.put("to",pmm.getMailAddress("to"));
            map.put("senddate", pmm.getSentDate());       
            map.put("content", pmm.getBodyText()); 
            result.add(map);
        }
		return result;
	}	
	
	public MailBizImp(MimeMessage mimeMessage) {   
        this.mimeMessage = mimeMessage;   
    }   
  
    public void setMimeMessage(MimeMessage mimeMessage) {   
        this.mimeMessage = mimeMessage;   
    }   
  
    public String getFrom() throws Exception {   
        InternetAddress address[] = (InternetAddress[]) mimeMessage.getFrom();   
        String from = address[0].getAddress();   
        if (from == null)   
            from = "";   
        String personal = address[0].getPersonal();   
        if (personal == null)   
            personal = "";   
        String fromaddr = personal + "<" + from + ">";   
        return fromaddr;   
    }   
  
    public String getMailAddress(String type) throws Exception {   
        String mailaddr = "";   
        String addtype = type.toUpperCase();   
        InternetAddress[] address = null;   
        if (addtype.equals("TO") || addtype.equals("CC")|| addtype.equals("BCC")) {   
            if (addtype.equals("TO")) {   
                address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.TO);   
            } else if (addtype.equals("CC")) {   
                address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.CC);   
            } else {   
                address = (InternetAddress[]) mimeMessage.getRecipients(Message.RecipientType.BCC);   
            }   
            if (address != null) {   
                for (int i = 0; i < address.length; i++) {   
                    String email = address[i].getAddress();   
                    if (email == null)   
                        email = "";   
                    else {   
                        email = MimeUtility.decodeText(email);   
                    }   
                    String personal = address[i].getPersonal();   
                    if (personal == null)   
                        personal = "";   
                    else {   
                        personal = MimeUtility.decodeText(personal);   
                    }   
                    String compositeto = personal + "<" + email + ">";   
                    mailaddr += "," + compositeto;   
                }   
                mailaddr = mailaddr.substring(1);   
            }   
        } else {   
            throw new Exception("Error emailaddr type!");   
        }   
        return mailaddr;   
    }   
  
    public String getSubject() throws MessagingException {   
        String subject = "";   
        try {   
            subject = MimeUtility.decodeText(mimeMessage.getSubject());   
            if (subject == null)   
                subject = "";   
        } catch (Exception exce) {}   
        return subject;   
    }   
  
    public String getSentDate() throws Exception {   
        Date sentdate = mimeMessage.getSentDate();   
        SimpleDateFormat format = new SimpleDateFormat(dateformat);   
        return format.format(sentdate);   
    }   
  
    public String getBodyText() {   
        return bodytext.toString();   
    }   
  
    public void getMailContent(Part part) throws Exception {   
        String contenttype = part.getContentType();   
        int nameindex = contenttype.indexOf("name");   
        boolean conname = false;   
        if (nameindex != -1)   
            conname = true;      
        if (part.isMimeType("text/plain") && !conname) {   
            bodytext.append((String) part.getContent());   
        } else if (part.isMimeType("text/html") && !conname) {   
        } else if (part.isMimeType("multipart/*")) {   
            Multipart multipart = (Multipart) part.getContent();   
            int counts = multipart.getCount();   
            for (int i = 0; i < counts; i++) {   
                getMailContent(multipart.getBodyPart(i));   
            }   
        } else if (part.isMimeType("message/rfc822")) {   
            getMailContent((Part) part.getContent());   
        } else {}   
    }   
   
    public boolean getReplySign() throws MessagingException {   
        boolean replysign = false;   
        String needreply[] = mimeMessage   
                .getHeader("Disposition-Notification-To");   
        if (needreply != null) {   
            replysign = true;   
        }   
        return replysign;   
    }   
  
    public String getMessageId() throws MessagingException {   
        return mimeMessage.getMessageID();   
    }   
  
    public boolean isNew() throws MessagingException {   
        boolean isnew = false;   
        Flags flags = ((Message) mimeMessage).getFlags();   
        Flags.Flag[] flag = flags.getSystemFlags();    
        for (int i = 0; i < flag.length; i++) {   
            if (flag[i] == Flags.Flag.SEEN) {   
                isnew = true;   
                break;   
            }   
        }   
        return isnew;   
    }   
  
    public boolean isContainAttach(Part part) throws Exception {   
        boolean attachflag = false;   
        String contentType = part.getContentType();   
        if (part.isMimeType("multipart/*")) {   
            Multipart mp = (Multipart) part.getContent();   
            for (int i = 0; i < mp.getCount(); i++) {   
                BodyPart mpart = mp.getBodyPart(i);   
                String disposition = mpart.getDisposition();   
                if ((disposition != null)  
                        && ((disposition.equals(Part.ATTACHMENT)) || (disposition   
                                .equals(Part.INLINE))))   
                    attachflag = true;   
                else if (mpart.isMimeType("multipart/*")) {   
                    attachflag = isContainAttach((Part) mpart);   
                } else {   
                    String contype = mpart.getContentType();   
                    if (contype.toLowerCase().indexOf("application") != -1)   
                        attachflag = true;   
                    if (contype.toLowerCase().indexOf("name") != -1)   
                        attachflag = true;   
                }   
            }   
        } else if (part.isMimeType("message/rfc822")) {   
            attachflag = isContainAttach((Part) part.getContent());   
        }   
        return attachflag;   
    }   
  
  
    public void saveAttachMent(Part part) throws Exception {  
        String fileName = "";   
        if (part.isMimeType("multipart/*")) {   
            Multipart mp = (Multipart) part.getContent();   
            for (int i = 0; i < mp.getCount(); i++) {   
                BodyPart mpart = mp.getBodyPart(i);   
                String disposition = mpart.getDisposition();   
                if ((disposition != null)   
                        && ((disposition.equals(Part.ATTACHMENT)) || (disposition   
                                .equals(Part.INLINE)))) {   
                    fileName = mpart.getFileName();   
                    if (fileName.toLowerCase().indexOf("gb2312") != -1) {   
                        fileName = MimeUtility.decodeText(fileName);   
                    }   
                    saveFile(fileName, mpart.getInputStream());   
                } else if (mpart.isMimeType("multipart/*")) {   
                    saveAttachMent(mpart);   
                } else {   
                    fileName = mpart.getFileName();   
                    if ((fileName != null)   
                            && (fileName.toLowerCase().indexOf("GB2312") != -1)) {   
                        fileName = MimeUtility.decodeText(fileName);   
                        saveFile(fileName, mpart.getInputStream());   
                    }   
                }   
            }   
        } else if (part.isMimeType("message/rfc822")) {   
            saveAttachMent((Part) part.getContent());   
        }   
    }   
  
    public void setAttachPath(String attachpath) {   
        this.saveAttachPath = attachpath;   
    }   
  
    public void setDateFormat(String format) throws Exception {   
        this.dateformat = format;   
    }   

    public String getAttachPath() {   
        return saveAttachPath;   
    }   
  
    private void saveFile(String fileName, InputStream in) throws Exception {   
        String osName = System.getProperty("os.name");   
        String storedir = getAttachPath();   
        String separator = "";   
        if (osName == null)   
            osName = "";   
        if (osName.toLowerCase().indexOf("win") != -1) {   
            separator = "\\";  
            if (storedir == null || storedir.equals(""))  
                storedir = "c:\\tmp";  
        } else {  
            separator = "/";  
            storedir = "/tmp";  
        }  
        File storefile = new File(storedir + separator + fileName);  
        BufferedOutputStream bos = null;  
        BufferedInputStream bis = null;  
        try {  
            bos = new BufferedOutputStream(new FileOutputStream(storefile));  
            bis = new BufferedInputStream(in);  
            int c;  
            while ((c = bis.read()) != -1) {  
                bos.write(c);  
                bos.flush();  
            }  
            System.out.println("成功");
        } catch (Exception exception) {  
            exception.printStackTrace();  
            throw new Exception("错误");  
        } finally {  
            bos.close();  
            bis.close();  
        }  
    }
}
