package br.com.cassi.web;

//import java.io.File;
import java.io.IOException;
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
//import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

//import org.apache.poi.sl.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;



//@WebServlet("/UploadServlet")
//public class UploadServlet extends HttpServlet {
//
//	private static final long serialVersionUID = 1L;
//	
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		System.out.println("Método" + request.getMethod());
//	}
//
//}

@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10, // 10MB
                 maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadServlet extends HttpServlet {

  	private static final long serialVersionUID = 1L;

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    // Obtém o arquivo enviado pelo usuário
    Part filePart = request.getPart("file");
    
    // Obtém o nome do arquivo
    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
    
    // Define o diretório onde o arquivo será salvo
    String uploadDir = "C:/upload/";
    
    // Salva o arquivo no diretório definido
    filePart.write(uploadDir + fileName);
    
    // Retorna uma mensagem de sucesso
    response.getWriter().println("Arquivo enviado com sucesso!");
  }
}
