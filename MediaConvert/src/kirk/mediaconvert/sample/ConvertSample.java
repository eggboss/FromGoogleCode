package kirk.mediaconvert.sample;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class ConvertSample {
	private final static String INPUT_PATH = "d:\\test\\少女時代-Gee.rmvb";
	private final static String OUTPUT_PATH = "d:\\test\\output\\";
	private final static String CODEC_PATH = "D:\\resource\\MediaConvert\\necessary\\";
	private static ProcessBuilder builder = new ProcessBuilder();
	
	public static void main(String[] args) {
		if (!checkfile(INPUT_PATH)) {
			System.out.println(INPUT_PATH + " is not file");
			return;
		}
		if (process()) {
			System.out.println("ok");
		}
	}

	private static boolean process() {
		int type = checkContentType();
		boolean status = false;
		if (type == 0) {
			status = processFLV(INPUT_PATH);// 直接將文件轉為flv文件
		} else if (type == 1) {
			String avifilepath = processAVI(type);
			if (avifilepath == null)
				return false;// avi文件沒有得到
//			status = processFLV(avifilepath);// 將avi轉為flv
		}
		return status;
	}
	
	private static String getFileName(){
		String fileName = INPUT_PATH.substring(INPUT_PATH.lastIndexOf("\\") + 1, INPUT_PATH.lastIndexOf("."));
		return fileName;
	}

	private static int checkContentType() {
		String type = INPUT_PATH.substring(INPUT_PATH.lastIndexOf(".") + 1, INPUT_PATH.length()).toLowerCase();
		
		// ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
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
		// 對ffmpeg無法解析的文件格式(wmv9，rm，rmvb等),
		// 可以先用別的工具（mencoder）轉換為avi(ffmpeg能解析的)格式.
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

	// 對ffmpeg無法解析的文件格式(wmv9，rm，rmvb等), 可以先用別的工具（mencoder）轉換為avi(ffmpeg能解析的)格式.
	private static String processAVI(int type) {
		List<String> commend = new java.util.ArrayList<String>();
		commend.add(CODEC_PATH + "mencoder");
		commend.add(INPUT_PATH);
		
		// convert to flv
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
		
		// convert to avi 
/*
		commend.add("-oac");
		commend.add("mp3lame"); // lavc
		commend.add("-lavcopts");
		commend.add("acodec=libmp3lame:abitrate=64"); // acodec=mp3:abitrate=64
		commend.add("-ovc");
		commend.add("xvid");
		commend.add("-xvidencopts");
		commend.add("bitrate=600");
		commend.add("-of");
		commend.add("avi");
		commend.add("-o");
		commend.add(OUTPUT_PATH + getFileName() + ".avi");
*/
		
		// mencoder d:\test\少女時代-Gee.rmvb -oac lavc -lavcopts acodec=libmp3lame:abitrate=64 -ovc xvid -xvidencopts bitrate=600 -of avi -o d:\test\output\少女時代-Gee.avi

		try {
//			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			System.out.println("Start convert input file to AVI...");
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
			
//			return "d:\\home\\a.avi";
			System.out.println("end convert input file to AVI.");
			return OUTPUT_PATH + getFileName() + ".avi";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
	private static boolean processFLV(String oldfilepath) {

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
//			ProcessBuilder builder = new ProcessBuilder();
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
			
			System.out.println("end convert input file to FLV.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
