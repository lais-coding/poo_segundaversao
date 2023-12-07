package control;

import java.util.List;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Plano;

public class PlanoControl  {

	private PlanoDAO dao = new PlanoDAO();
	
	
	
	//Há conceitos do padrão de projeto Observer
	
		//Alterações são notificadas por qualquer lugar onde as propriedades podem ser observadas
		
		//StringProperty - Interface observável do tipo String (Permite visualizar a alteração nesse tipo de variável)
		private StringProperty tituloPlano = new SimpleStringProperty("");
		private SimpleDoubleProperty valorMensal = new SimpleDoubleProperty();
		private SimpleDoubleProperty valorAnual = new SimpleDoubleProperty();
		private StringProperty beneficiosPlano = new SimpleStringProperty("");
		
		
		//ObservableList - Lista
		
		private ObservableList<Plano> listaPlanos = FXCollections.observableArrayList();

		
		
		public StringProperty tituloProperty() {
			return this.tituloPlano;
		}
		
		
		public SimpleDoubleProperty vlMensalProperty() {
			return this.valorMensal;
		}
		
		public SimpleDoubleProperty vlAnualProperty() {
			return this.valorAnual;
		}
		
		public StringProperty beneficiosProperty() {
			return this.beneficiosPlano;
		}
	
		
		
		//Métodos
		
		
		public void cadastrarPlano() throws Exception {
			Plano plano = new Plano();
			plano.setTituloPlano(tituloPlano.get());
			plano.setValorMensal(valorMensal.get());
			plano.setValorMensal(valorAnual.get());
			plano.setBeneficios(beneficiosPlano.get());
			
			//Salvar no banco através do DAO
			
			dao.salvar(plano);
			lerTodos();
			
		}
		
		
		void pesquisarPlano() throws Exception {
			List<Plano> listaPlanos = dao.pesquisarPlano(tituloPlano.get());
			listaPlanos.clear();
			listaPlanos.addAll(listaPlanos);
		} 
		
		


		public void lerTodos() throws Exception {
			List<Plano> listaAlunos = dao.lerTodos();
			listaPlanos.clear();
			listaPlanos.addAll(listaPlanos);
		}
		
		
		
		
		public ObservableList<Plano> getListaPlanos() {
			return listaPlanos;
		}


		public void setListaPlanos(ObservableList<Plano> listaPlanos) {
			this.listaPlanos = listaPlanos;
		}
		
		
		
		
		
	
	
}
