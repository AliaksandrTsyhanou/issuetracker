package by.epam.lab.issuetracker.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import by.epam.lab.issuetracker.service.FileManager;



@Controller
public class FilesController {
	private static final Logger logger = LoggerFactory.getLogger(FilesController.class);

	/* Size of a byte buffer to read/write file */
    private static final int BUFFER_SIZE = 4096;
	
	@Autowired
	private FileManager fileManager;
	
	@RequestMapping(value="/files", method = RequestMethod.GET) 
	public String showdfiles(){
		return "files";
	}
	
	
	/**
	 * Upload single file using Spring Controller
	 * @throws IOException 
	 */
	@RequestMapping(value = "/files", method = RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("file") MultipartFile file) throws IOException {

		String filename = file.getOriginalFilename();
		if (!file.isEmpty()) {
//			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + filename);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="	+ serverFile.getAbsolutePath());

//			} catch (Exception e) {
//				logger.error("You failed to upload " + filename + " => " + e.getMessage());
//			}
		} 
		return "files";
	}
	
	/**
     * Method for handling file download request from client
     */
	@RequestMapping(value = "/files/{fileName}", method = RequestMethod.GET)
    public void doDownload(@PathVariable String fileName,
    		HttpServletRequest request,
            HttpServletResponse response) throws IOException {
 
		fileName = fileName + ".txt"; 
		// Creating the directory to store file
		String rootPath = System.getProperty("catalina.home");
		File dir = new File(rootPath + File.separator + "tmpFiles");
		
        System.out.println("dir = " + dir);
 
        // construct the complete absolute path of the file
        String fullPath = dir + File.separator + fileName;      
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        // get MIME type of the file
        String mimeType = new MimetypesFileTypeMap().getContentType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
 
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();
 
    }
}