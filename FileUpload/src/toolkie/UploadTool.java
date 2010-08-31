package toolkie;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadTool {
	private int buffersize = 4096;
	private int SizeMax = 1024 * 1024;// 1Mbyte�̤j�ɮפj�p
	private File tempfile = null;
	private String def_upload_dir = null;

	// �ΨӦsparameter
	private Map map = null;
	private Map uploadlist = null;

	// �B�l�Ʈɧ⵹request��Ҧ����Ȩ��X,�s�Jmap
	public UploadTool(HttpServletRequest request) throws FileUploadException, UnsupportedEncodingException {

		map = new HashMap();
		uploadlist = new HashMap();

		// �إߤ@�ӥHdisk-base���ɮת���
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// ��l�Ƥ��e
		// �ǰe�ҥΪ�buffer�Ŷ�
		factory.setSizeThreshold(buffersize);
		// The directory in which temporary files will be located.

		factory.setRepository(tempfile);

		// �إߤ@���ɮפW�Ǫ�����
		ServletFileUpload upload = new ServletFileUpload(factory);

		// �̤j�ɮפj�p
		upload.setSizeMax(SizeMax * 10);

		// �C�@��Fileitem�N��@��form�W�Ǫ����󤺮eex input type="text"
		List items = null; // �|���� FileUploadException
		// ���Ʊqrequest���X
		items = upload.parseRequest(request); // Parse the request

		Iterator iter = items.iterator();

		while (iter.hasNext()) {// ����Ҧ��Ѽƨ��o�Ӥ���write to file
			FileItem item = (FileItem) iter.next();
			// �@���r���
			if (item.isFormField()) {
				map.put(item.getFieldName(), item.getString("Big5"));
				System.out.println("�W���ɮת��䥦�Ѽ�:" + item.getFieldName() + "="
						+ item.getString("Big5"));
			} else {// �W���ɮ����
				// or it's a file upload request

				if (item.getSize() > 0) {
					uploadlist.put(item.getFieldName(), item);
					System.out.println("�W���ɮ�:" + item.getFieldName());
				}
			}
		}
	}

	// �]�w�ɮפW�ǫ�s�񪺦a��
	public void setUploadDir(String upload_dir) {
		this.def_upload_dir = upload_dir;
	}

	// ���o�Ҧ����,�]�t�@�����ΤW�Ǫ����
	public Map getAllParameter() {
		Map rvalue = new HashMap();
		rvalue.putAll(map);
		rvalue.putAll(uploadlist);
		return rvalue;
	}

	// ���o�Y�@��쪺��,�@�����
	public String getParameter(String FieldName) {
		if (map.containsKey(FieldName))
			return String.valueOf(map.get(FieldName));
		else
			return null;
	}

	// ���o�Y�@��쪺��,�W�����
	public FileItem getUploadParameter(String FieldName) {
		if (uploadlist.containsKey(FieldName))
			return (FileItem) uploadlist.get(FieldName);
		else
			return null;
	}

	// �ˬd�W�Ǹ�ƬO�_���T
	public String checkUpload() {
		Iterator iter = uploadlist.keySet().iterator();
		while (iter.hasNext()) {
			Object Name = iter.next();
			FileItem item = (FileItem) uploadlist.get(Name);
			String itename = item.getName();
			System.out.println("�W�Ǫ��ɮ׬�:" + itename);
			if (item.getSize() > SizeMax)
				return "�ɮפӤj!";
		}
		return "";
	}

	// �}�l�W��
	public String doUpload(FileItem item, String fileName) {
		String str = "";
		long sizeInBytes = item.getSize();
		// �P�{�W�Ǹ�ƬO�_���~
		if (sizeInBytes > SizeMax)
			return "�ɮפӤj!";

		if (sizeInBytes > 0) {

			int index = -1;
			String itename = null;
			if ((index = item.getName().lastIndexOf("\\")) != -1)
				itename = item.getName().substring(index, item.getName().length());
			else
				itename = item.getName();
			// ���ɦW
			String formatName = itename.substring(itename.length() - 4, itename.length());

			fileName = (fileName + formatName).toLowerCase();

			System.out.println("�W���ɮ��ɮצW��:" + fileName);

			File uploadedFile = new File(def_upload_dir + fileName);
			// �|���� Exception
			try {
				item.write(uploadedFile);

			} catch (Exception e) {
				System.out.println("�W�ǥ���!" + e.toString());
				str = "�W�ǥ���!";
			}
			// �|���� Exception

		}
		return str;
	}

	// �O�_�s�b���W�������

	public boolean isExtUpload(String fileName) {
		return uploadlist.containsKey(fileName);
	}
}