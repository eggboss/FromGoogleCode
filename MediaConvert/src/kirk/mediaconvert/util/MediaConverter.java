package kirk.mediaconvert.util;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class MediaConverter {
	private String INPUT_PATH;
	private String OUTPUT_PATH;
	private String CODEC_PATH;

	{
		OUTPUT_PATH = ResourceBundleAdapter.getResource("outputPath");
//		CODEC_PATH = ResourceBundleAdapter.getResource("codecPath");
		CODEC_PATH = getCodecPath();
	}
	
	public MediaConverter(String inputFilePath) {
		INPUT_PATH = inputFilePath;
	}
	
	private String getCodecPath(){
		String classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		classPath = classPath.substring(1,classPath.length());
		return classPath + "/codec/";
	}

	private boolean process() {
		int type = checkContentType();
		boolean status = false;
		if (type == 0) {
			status = ffmpegProcessFLV(INPUT_PATH);// �����N����ରflv���
		} else if (type == 1) {
			status = mencoderProcessFLV(INPUT_PATH);
		}
		return status;
	}
	
	private String getFileName(){
		String fileName = INPUT_PATH.substring(INPUT_PATH.lastIndexOf("\\") + 1, INPUT_PATH.lastIndexOf("."));
		return fileName;
	}

	private int checkContentType() {
		String type = INPUT_PATH.substring(INPUT_PATH.lastIndexOf(".") + 1, INPUT_PATH.length()).toLowerCase();
		
		// ffmpeg��ѪR���榡�G�]asx�Aasf�Ampg�Awmv�A3gp�Amp4�Amov�Aavi�Aflv���^
		if (type.equals("avi")) {
			return 0;
		} else if (type.equals("mpg")) {
			return 0;
		} else if (type.equals("wmv")) {
			return 0;
		} else if (type.equals("3gp")) {
			return 0;
		} else if (type.equals("mov")) {
			return 0;
		} else if (type.equals("mp4")) {
			return 0;
		} else if (type.equals("asf")) {
			return 0;
		} else if (type.equals("asx")) {
			return 0;
		} else if (type.equals("flv")) {
			return 0;
		}
		// ��ffmpeg�L�k�ѪR�����榡(wmv9�Arm�Armvb��),
		// �i�H���ΧO���u��]mencoder�^�ഫ��avi(ffmpeg��ѪR��)�榡.
		else if (type.equals("wmv9")) {
			return 1;
		} else if (type.equals("rm")) {
			return 1;
		} else if (type.equals("rmvb")) {
			return 1;
		}
		return 9;
	}

	private static boolean checkfile(String path) {
		File file = new File(path);
		if (!file.isFile()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 */
	private boolean mencoderProcessFLV(String oldfilepath) {
		if (!checkfile(INPUT_PATH)) {
			System.out.println(oldfilepath + " is not file");
			return false;
		}
		
		List<String> commend = new java.util.ArrayList<String>();
		commend.add(CODEC_PATH + "mencoder");
		commend.add(oldfilepath);
		commend.add("-oac");
		commend.add("mp3lame");
//		commend.add("-lavfopts");
//		commend.add("i_certify_that_my_video_stream_does_not_use_b_frames");
		commend.add("-ovc");
		commend.add("lavc");
		commend.add("-lameopts");
		commend.add("abr:br=56");
		commend.add("-srate");
		commend.add("22050");
		commend.add("-of");
		commend.add("lavf");
		commend.add("-ofps");
		commend.add("12");
		commend.add("-vf");
		commend.add("scale=512:-3");
		commend.add("-lavcopts");
		commend.add("vcodec=flv:vbitrate=300:mbd=2:mv0:trell:v4mv:cbp:last_pred=3:dia=4:cmp=6:vb_strategy=1");
		commend.add("-o");
		commend.add(OUTPUT_PATH + getFileName() + ".flv");
		
		// mencoder d:\test\�֤k�ɥN-Gee.rmvb -oac lavc -lavcopts acodec=libmp3lame:abitrate=64 -ovc xvid -xvidencopts bitrate=600 -of avi -o d:\test\output\�֤k�ɥN-Gee.avi

		try {
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			System.out.println("Start convert input file to FLV use mencoder...");
			builder.redirectErrorStream(true);
			Process process = builder.start();
			
//			InputStream in=process.getInputStream();
//			String result="";
//			byte[] re = new byte[1024];
//			while (in.read(re) != -1) {
//				System.out.println(new String(re));
//				result = result + new String(re);
//			}
//			in.close();
			
			System.out.println("end convert input file to FLV.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// ffmpeg��ѪR���榡�G�]asx�Aasf�Ampg�Awmv�A3gp�Amp4�Amov�Aavi�Aflv���^
	private boolean ffmpegProcessFLV(String oldfilepath) {

		if (!checkfile(INPUT_PATH)) {
			System.out.println(oldfilepath + " is not file");
			return false;
		}

		List<String> commend = new java.util.ArrayList<String>();
		commend.add(CODEC_PATH + "ffmpeg");
		commend.add("-i");
		commend.add(oldfilepath);
		commend.add("-ab");
		commend.add("64");
		commend.add("-acodec");
//		commend.add("mp3"); // Unknown encoder 'mp3', Need to indtall lame
		commend.add("libmp3lame");
		commend.add("-ac");
		commend.add("2");
		commend.add("-ar");
		commend.add("22050");
		commend.add("-b");
		commend.add("230");
		commend.add("-r");
		commend.add("24");
		commend.add("-y");
//		commend.add("d:\\home\\a.flv");
		commend.add(OUTPUT_PATH + getFileName() + ".flv");
		
		// ffmpeg -i d:\test\a.mpg -ab 64 -acodec libmp3lame -ac 2 -ar 22050 -b 230 -r 24 -y d:\test\output\a.flv
		
		try {
			System.out.println("Start convert input file to FLV...");
			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			
			builder.redirectErrorStream(true);
			
			Process process = builder.start();
			
			InputStream in=process.getInputStream();
			String result="";
			byte[] re = new byte[1024];
			while (in.read(re) != -1) {
				System.out.println(new String(re));
				result = result + new String(re);
			}
			in.close();
			
			System.out.println("end convert input file to FLV use ffmpeg.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		String inputPath = "d:\\test\\�֤k�ɥN-Gee.rmvb";
		if (!checkfile(inputPath)) {
			System.out.println(inputPath + " is not file");
			return;
		}
		if (new MediaConverter(inputPath).process()) {
			System.out.println("ok");
		}
	}
}
