package com.pop.uitils;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author Pop
 * @date 2019/4/3 21:19
 * 下载工具
 */
public class DownFileUtils  {

    private static final String WEB_CONTENT_DISPOSITION_KEY="Content-Disposition";
    private static final String WEB_CONTENT_DISPOSITION_VALUE="attachment;filename=";
    private static final String WEB_CONTENT_TYPE_UTF8="text/html;charset=utf8";

    private static final String WEB_ERR_MESSAGE_UNEXISTS="fileName was undefine!";

    /**
     * 文件的下载
     * @param response
     * @param fileName
     * @throws Exception
     */
    public static void downFileForWeb(HttpServletResponse response, String fileName) throws Exception{
        File file = new File(fileName);
        if(file.exists()){downFileForWeb(response,file);}else{throw new RuntimeException(WEB_ERR_MESSAGE_UNEXISTS);}
    }

    /**
     * 文件的下载
     * @param response
     * @param file
     */
    public static void downFileForWeb(HttpServletResponse response, File file){
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            response.setContentType(WEB_CONTENT_TYPE_UTF8);
            response.setHeader(WEB_CONTENT_DISPOSITION_KEY,WEB_CONTENT_DISPOSITION_VALUE+file.getName());
            BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
            int len=0;
            while((len=bis.read())!=-1){
                bos.write(len);
            }
        }catch (Exception e){e.printStackTrace();}finally{
           close(bis,null);
        }

    }

    public static void uploadFileForWeb(HttpServletRequest request, String filePath){
        File file = new File(filePath);
        uploadFileForWeb(request,file);
    }
    /**
     * 上传文件
     * @param request
     * @param file
     */
    public static void uploadFileForWeb(HttpServletRequest request, File file){
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        try {
            bis = new BufferedInputStream(request.getInputStream());
            fos = new FileOutputStream(file);
            int len =0;
            byte[] buffer = new byte[1024];
            while((len=bis.read(buffer))!=-1){
                fos.write(buffer,0,len);
            }
            fos.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            close(bis,fos);
        }
    };

    public static void close(InputStream is,OutputStream os){
        try {
            if(null!=is){
                is.close();
            }
            if(null!=os){
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
