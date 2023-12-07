package control;

import java.util.List;


import model.Plano;

public interface InterfacePlanoDAO {

	
	public void salvar(Plano plano);
	List<Plano> pesquisarPlano(String tituloPlano);
	List<Plano> lerTodos();
	
	
	
}
