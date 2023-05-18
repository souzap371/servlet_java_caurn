package br.com.cassi.web;
import java.io.BufferedReader;
import java.io.FileInputStream;
//import java.beans.Statement;
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


@WebServlet("/UploadServletDVL")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10, // 10MB
                 maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadServletDVL extends HttpServlet {

  	private static final long serialVersionUID = 1L;

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
    
    // Obtém o arquivo enviado pelo usuário
    Part filePart = request.getPart("fileDVL");
    
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

    // Configuração dos parâmetros de autenticação
    String user = "solus";
    String passwd = "soluscaurn";

    try {
    	
    	FileInputStream fis = new FileInputStream(uploadDir + fileName);
    	InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
    	BufferedReader br = new BufferedReader(isr);
    	
    	
    	//Conectando ao Banco Oracle
    	Class.forName("oracle.jdbc.driver.OracleDriver");
        String url = "jdbc:oracle:thin:@" + server + ":" + port + ":" + database;

        // Abre-se a conexão com o Banco de Dados
        Connection con = DriverManager.getConnection(url, user, passwd);

        // Cria-se Statement com base na conexão con
        
        
        if (con != null) {
            System.out.println("Conexão bem sucedida!");
        }
        
        //Preparar instrução SQL
        String sql = "INSERT INTO AG_CASSI_DEV (CONTEUDO, DATA_DEV, PROC) VALUES (?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        
        //Ler e inserir cada linha do arquivo
        String line;
        while((line = br.readLine()) != null) {
        	String[] data = line.split(";");  //Separa os dados por vírgula
        	pstmt.setString(1, data[0]); //Define o valor do primeiro parâmetro
        	
        	//Inserir data atual no 2 parametro
        	LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String todayString = today.format(formatter);
            pstmt.setString(2, todayString);

        	
        	//Inserir "S" no campo PROC
        	String PROC = "S";
        	pstmt.setString(3, PROC); 
        	
        	// Executa a declaração SQL
        	pstmt.executeUpdate(); 
        	
        }
        
        //Fecha os recursos utilizados
        pstmt.close();
        con.close();
        br.close();
        
        System.out.println("Dados do arquivo .DVL inseridos na tabela com sucesso!");
        
    } catch (ClassNotFoundException e) {
      System.out.println("Não foi possível encontrar o driver JDBC do Oracle.");
      e.printStackTrace();
  } catch (SQLException e) {
      System.out.println("Não foi possível conectar ao banco de dados Oracle.");
      e.printStackTrace();
  }
    
 
    
    // Retorna uma mensagem de sucesso
    response.getWriter().println("Arquivo enviado com sucesso!");
    
  }



}
//
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class PaginaPrincipal extends JFrame implements ActionListener {
//    private JButton botao;
//    
//    public PaginaPrincipal() {
//        setTitle("Página principal");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(300, 200);
//        
//        // Cria o botão
//        botao = new JButton("Abrir página");
//        botao.addActionListener(this);
//        
//        // Adiciona o botão ao painel principal
//        JPanel panel = new JPanel();
//        panel.add(botao);
//        getContentPane().add(panel);
//        
//        setVisible(true);
//    }
//    
//    public void actionPerformed(ActionEvent e) {
//        // Cria e exibe a nova janela (ou página)
//        JFrame novaPagina = new JFrame("Nova página");
//        novaPagina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        novaPagina.setSize(300, 200);
//        
//        JLabel label = new JLabel("Esta é a nova página!");
//        novaPagina.add(label);
//        
//        novaPagina.setVisible(true);
//    }
//    
//    public static void main(String[] args) {
//        new PaginaPrincipal();
//    }
//}



