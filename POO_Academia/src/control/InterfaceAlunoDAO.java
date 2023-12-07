package control;

import java.util.ArrayList;
import java.util.List;

import model.Aluno;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;*/

public class InterfaceAlunoDAO implements AlunoDAO {
	
	
	private static final String JDBC_URL =
			"jdbc:mariadb://localhost:3306/aluno";
	private static final String JDBC_USER = "root";
	
	private Connection con;
	
	
	public InterfaceAlunoDAO() { 
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(
					JDBC_URL, JDBC_USER, null);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	
	
	
	
	
	
	
	

	@Override
	public void salvar(Aluno aluno) {
		
		String sql = "INSERT INTO aluno (nomeAluno, rgAluno, nascimentoAluno, entradaAluno, emailAluno, foneAluno) values (?, ?, ?, ?, ?, ?)";
	
		
				try {
					
					//1.4.2 Preparar uma PreparedStatement para executar uma consulta
					
					PreparedStatement pstm = con.prepareStatement(sql);
					
					//1.4.3 Atribuição dos valores aos espaços reservados
					
					pstm.setString(1, aluno.getNomeAluno());
					pstm.setString(2, aluno.getRgAluno());
					pstm.setString(3, aluno.getdtNascAluno());
					pstm.setString(4, aluno.getDtEntrada());
					pstm.setString(5, aluno.getEmailAluno());
					pstm.setString(6, aluno.getFoneAluno());
					
					
					//1.4.5 Execução da query (Como se fosse uma confirmação)
					
					pstm.executeUpdate();
					
					System.out.println("Registro salvo com sucesso.");
					
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
	}

	
	
	
	@Override
	public List<Aluno> lerTodos() throws Exception {
		return pesquisarAluno("");
	}

	
	@Override
	public List<Aluno> pesquisarAluno(String nomeAluno) throws Exception {
	
		    List<Aluno> listaAlunos = new ArrayList<>();
		    String sql = "SELECT * from aluno WHERE nomeAluno LIKE ?";
		   
		    
		    try {
		      
		    	PreparedStatement  pstm = con.prepareStatement(sql);
		        pstm.setString(1, "%" + nomeAluno + "%");
		        ResultSet rs = pstm.executeQuery();
		        
		        while (rs.next()) {
		            Aluno aluno = new Aluno();
		            aluno.setNomeAluno(rs.getString("nomeAluno"));
		            aluno.setRgAluno(rs.getString("rgAluno"));
		            aluno.setDtNascimento(rs.getString("nascimentoAluno"));
		            aluno.setDtEntrada(rs.getString("entradaAluno"));
		            aluno.setEmailAluno(rs.getString("emailAluno"));
		            aluno.setFoneAluno(rs.getString("foneAluno"));
		            listaAlunos.add(aluno);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw new Exception("Erro ao pesquisar alunos: " + e.getMessage());
		    } 
		    
		    return listaAlunos;
		}

}




