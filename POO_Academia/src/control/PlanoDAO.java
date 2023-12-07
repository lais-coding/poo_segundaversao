package control;

import java.util.ArrayList;
import java.util.List;

import model.Plano;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;*/

public class PlanoDAO implements InterfacePlanoDAO {
	
	
	private static final String JDBC_URL =
			"jdbc:mariadb://localhost:3306/plano";
	private static final String JDBC_USER = "root";
	
	private Connection con;
	
	
	public void InterfaceAlunoDAO() { 
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
	public void salvar(Plano plano) {
		
		String sql = "INSERT INTO plano (tituloPlano, valorMensal, valorAnual, beneficiosPlano) values (?, ?, ?, ?)";
	
		
				try {
					
					//1.4.2 Preparar uma PreparedStatement para executar uma consulta
					
					PreparedStatement pstm = con.prepareStatement(sql);
					
					//1.4.3 Atribuição dos valores aos espaços reservados
					
					pstm.setString(1, plano.getTituloPlano());
					pstm.setDouble(2, plano.getValorMensal());
					pstm.setDouble(3, plano.getValorAnual());
					pstm.setNString(4, plano.getBeneficios());
					
					//1.4.5 Execução da query (Como se fosse uma confirmação)
					
					pstm.executeUpdate();
					
					System.out.println("Registro salvo com sucesso.");
					
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
	}

	
	
	
	@Override
	public List<Plano> lerTodos() {
		return pesquisarPlano("");
	}

	
	@Override
	public List<Plano> pesquisarPlano(String tituloPlano) {
	
		    List<Plano> listaPlanos = new ArrayList<>();
		    String sql = "SELECT * from plano WHERE tituloPlano LIKE ?";
		   
		    
		    try {
		      
		    	PreparedStatement  pstm = con.prepareStatement(sql);
		        pstm.setString(1, "%" + tituloPlano + "%");
		        ResultSet rs = pstm.executeQuery();
		        
		        while (rs.next()) {
		            Plano plano = new Plano();
		            plano.setTituloPlano(rs.getString("tituloPlano"));
		            plano.setValorMensal(rs.getDouble("valorMensal"));
		            plano.setValorAnual(rs.getDouble("valorAnual"));
		            plano.setBeneficios(rs.getString("beneficiosPlano"));
		           
		            listaPlanos.add(plano);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		       
		    } 
		    
		    return listaPlanos;
		}

}




