package br.com.cassi.web;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


@WebServlet("/testUpload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10, // 10MB
                 maxRequestSize = 1024 * 1024 * 50) // 50MB

public class testUpload extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	//public static void main(String[] args) throws IOException, SQLException {
		
		// Obtém o arquivo enviado pelo usuário
	    Part filePart = request.getPart("file");
	    
	    // Obtém o nome do arquivo
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
	    
	    // Define o diretório onde o arquivo será salvo
	    String uploadDir = "C:/upload/";
	    
	    // Salva o arquivo no diretório definido
	    filePart.write(uploadDir + fileName);
       
    	
     // Configuração dos parâmetros de conexão
        // String sql;
         String server = "192.168.10.50";
         String port = "1521";      
         String database = "solus";
         
         
         
         try {
        	 
        	 Class.forName("oracle.jdbc.driver.OracleDriver");
             String url = "jdbc:oracle:thin:@" + server + ":" + port + ":" + database;

         // Configuração dos parâmetros de autenticação
         String user = "solus";
         String passwd = "soluscaurn";
        Connection connection = DriverManager.getConnection(url, user, passwd);
        
        // Leitura do arquivo .DVL
        
        FileInputStream fis = new FileInputStream(uploadDir + fileName);
    	InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
    	BufferedReader br = new BufferedReader(isr);
        //BufferedReader reader = new BufferedReader(new FileReader("fileName"));
        String line;
        while ((line = br.readLine()) != null) {
            // Separação dos dados
            String[] data = line.split(" ");
            String field1 = data[0];
            
            
            // Inserção dos dados na tabela do banco de dados Oracle
            String sql = "INSERT INTO AG_CASSI_ERR (CONTEUDO, DATA_ERR) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, field1);
            
            
            // Definição da data atual
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String todayString = today.format(formatter);
            statement.setString(2, todayString);
            
            statement.executeUpdate();
        }
        
        br.close();
        connection.close();
         }catch (SQLException e) {
             System.out.println("Não foi possível conectar ao banco de dados Oracle.");
             e.printStackTrace();
         } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
//}



