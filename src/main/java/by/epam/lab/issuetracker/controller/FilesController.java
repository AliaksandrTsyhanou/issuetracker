package by.epam.lab.issuetracker.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import by.epam.lab.issuetracker.entity.FileInfo;
import by.epam.lab.issuetracker.exceptions.DAOException;
import by.epam.lab.issuetracker.exceptions.NotExistException;
import by.epam.lab.issuetracker.service.FileInfoManager;
import by.epam.lab.issuetracker.service.IssueManager;

@Controller
public class FilesController {
	private static final Logger logger = LoggerFactory.getLogger(FilesController.class);

	static final String ROOT_PATH = "D:" + File.separator + "tmpFiles";
	/* Size of a byte buffer to read/write file */
    private static final int BUFFER_SIZE = 4096;
	
	@Autowired
	private FileInfoManager fileInfoManager;
	@Autowired
	private IssueManager issueManager;
	
	
	/**
     * Method for show all  upload files
	 * @throws DAOException 
     */	
	@RequestMapping(value="/files", method = RequestMethod.GET) 
	public String showfiles(Model model) throws DAOException{
		List<FileInfo> fileList = fileInfoManager.getAll();
		model.addAttribute("files", fileList);
		return "files";
	}
	
	
	/**
     * Method for handling file download request from client
	 * @throws DAOException 
     */
	@RequestMapping(value = "/files/{fileId}", method = RequestMethod.GET)
    public void doDownload(@PathVariable long fileId,
    		HttpServletRequest request,
            HttpServletResponse response) throws IOException, DAOException {
 
		FileInfo fileInfo = fileInfoManager.get(fileId);
		if (fileInfo == null){
			throw new NotExistException();
		}
		String fileName = fileInfo.getName();

		// Creating the directory to store file
//		String rootPath = System.getProperty("catalina.home");
		File dir = new File(ROOT_PATH + File.separator + fileInfo.getIdissue());
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
	
	
	/**
     * Method for handling file download request from client by isuueId
	 * @throws DAOException 
     */
	@RequestMapping(value="/files/issue/{issueId}", method = RequestMethod.GET) 
	public String showfilesByIssue(@PathVariable long issueId, Model model) throws DAOException{
		if (issueManager.get(issueId) == null){
			throw new NotExistException();
		}
		List<FileInfo> fileList = fileInfoManager.getAll(issueId);
		System.out.println("fileList =" + fileList);
		model.addAttribute("files", fileList);
		return "files";
	}
	
	
	/**
	 * Upload single file using Spring Controller
	 * @throws IOException 
	 * @throws DAOException 
	 */
	@RequestMapping(value="/files/issue/{issueId}", method = RequestMethod.POST) 
	public String uploadFileHandler(
			@PathVariable long issueId,
			@RequestParam("file") MultipartFile file, 
			@RequestParam("description") String description,
			Model model) throws IOException, DAOException {
		System.out.println("@RequestMapping(value=/files/issue/{issueId}, method = RequestMethod.POST)");
		System.out.println("issueId=" + issueId);
		String filename = file.getOriginalFilename();
		File dir = new File(ROOT_PATH  + File.separator + issueId);
		if (uploadfile(file, dir)){
			FileInfo fileInfo = new FileInfo();
			fileInfo.setName(filename);
			fileInfo.setDescription(description);
			fileInfo.setSize(file.getSize());
			fileInfo.setIdissue(issueId);
			fileInfoManager.add(fileInfo);
		}		
//		return showfilesByIssue(issueId, model);
//		return issueController.getById(issueId, model);
		return ("redirect:/issues/" + issueId);

	}
	
	
	private boolean uploadfile(MultipartFile file, File dir) throws IOException{
		if (!file.isEmpty()) {
			String filename = file.getOriginalFilename();
			byte[] bytes = file.getBytes();
			// Creating the directory to store file
			// String rootPath = System.getProperty("catalina.home");
			if (!dir.exists())
				System.out.println("dir.mkdirs() = " + dir.mkdirs());
	
			// Create the file on server
			File serverFile = new File(dir.getAbsolutePath() + File.separator + filename);
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
			logger.info("Server File Location="	+ serverFile.getAbsolutePath());
			return true;
		} 
		return false;
	}	
	
}