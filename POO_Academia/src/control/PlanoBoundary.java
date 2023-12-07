package control;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Plano;

public class PlanoBoundary extends Application {
	 
	/**************** CONFIGURAÇÕES DA TELA ***************/
	 
	 
	 //Stage - Janela da aplicação
	 	private Stage stage;
	 
 
	 //TableView = Tabela de exibição dos alunos
	    TableView<Plano> tabelaPlano = new TableView<>();
	    
	    
	 //TextField - Campos para inserir dados
	    TextField txtTituloPlano = new TextField();
	    TextField txtValorMensal = new TextField();
	    TextField txtValorAnual = new TextField();
	    TextField beneficiosPlano = new TextField();
	    
	   
	  //Instância para exercitar a lógica
	    PlanoControl controle = new PlanoControl();
	    
	    
	 
	 /**************** EXECUÇÃO ***************/
		 
	@Override
	public void start(Stage stage) throws Exception {
		
		//Pane - classe base para componentes de layout
		Pane panel = new Pane();
		
		//Scene - Conterá os elementos gráficos
		Scene scene = new Scene(panel, 900, 500);
		
		stage.setTitle("MANUTENÇÃO DE PLANOS");
		
		
		//BorderPane - auxilia no posicionamento
		BorderPane root = new BorderPane();
		
		/*
		
		//Organizar abas
		TabPane tabPane = new TabPane();
		
		//Tab - Abas
		Tab tabAlunos = new Tab("Alunos");
		
				*/
		
		//GridPane - Layout para organizar em linhas e colunas
		GridPane gridPlanos = new GridPane();
		
		
		//Ajustar espaçamento entre os TextFields
		gridPlanos.setHgap(10); //Espaçamento horizontal
		gridPlanos.setVgap(15); //Espaçamento vertical
		
		
		//Sintaxe: O label que desejo adicionar, índice da coluna, índice da linha
		
		gridPlanos.add(new Label("Manutenção de Planos"), 0, 0);		 
		gridPlanos.add(new Label("Título do plano: "), 0, 1);
		gridPlanos.add(txtTituloPlano, 1, 1);
		gridPlanos.add(new Label("Valor mensal: "), 0, 2);
		gridPlanos.add(txtValorMensal, 1, 2);
		gridPlanos.add(new Label("Valor anual: "), 0, 3);
		gridPlanos.add(txtValorAnual, 1, 3);
		gridPlanos.add(new Label("Benefícios do plano: "), 0, 4);
		gridPlanos.add(beneficiosPlano, 1, 4);
		gridPlanos.add(tabelaPlano, 1,10);
		
		//Configurações dos botões
		
		Button btnSalvar = new Button("Salvar");
		Button btnPesquisar = new Button("Pesquisar");
		
		//Width (Largura) e Height (altura)
		 btnPesquisar.setMaxHeight(650);
		 btnSalvar.setMaxHeight(650);
		 btnSalvar.setMaxWidth(200);
		 btnPesquisar.setMaxWidth(500);


		//Posicionamento dos botões
		gridPlanos.add(btnPesquisar, 2, 8);
		gridPlanos.add(btnSalvar, 1, 8);
		
		//Formatação dos botões
		btnPesquisar.setStyle("-fx-base: orange");
		btnSalvar.setStyle("-fx-base: green");
		
			//Evento do botão Salvar
				btnSalvar.setOnAction(e -> {
					try {
						controle.cadastrarPlano();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
				
		
			//Evento do botão Pesquisar
				btnPesquisar.setOnAction(e -> {
					try {
						controle.pesquisarPlano();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
	
		
		//Chamada do método para ligar os componentes da interface com o control
		bindingPlanos();
		createColumnsPlanos();
		
		
		
		/*
		//Definindo o conteúdo da aba
		tabAlunos.setContent(gridAlunos);
	
		*/
		scene.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());
		
		//Exibir conteúdo
		panel.getChildren().add(gridPlanos);
		stage.setScene(scene);
		stage.show();
	}


	
	
	//Método para adicionar colunas na tabela
	@SuppressWarnings("unchecked")
	public void createColumnsPlanos() {
		
		tabelaPlano.setItems(controle.getListaPlanos());
		
		TableColumn<Plano, String> colTituloPlano = new TableColumn<>("Título do Plano");
		colTituloPlano.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getTituloPlano()));
		TableColumn<Plano, Double> colValorMensal = new TableColumn<>("VALOR MENSAL");
	/*	colValorMensal.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getValorMensal()));
		TableColumn<Plano, Double> colValorAnual = new TableColumn<>("VALOR ANUAL:");
		colValorAnual.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getValorAnual())); */
		TableColumn<Plano, String> colBeneficios = new TableColumn<>("Benefícios do Plano: ");
		colBeneficios.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getTituloPlano()));
		
		
		colTituloPlano.setPrefWidth(150);
		colBeneficios.setPrefWidth(300);
		
		tabelaPlano.getColumns().addAll(colTituloPlano, colBeneficios);
	
	}



		//Método onde faz a ligação entre os componentes da interface gráfica com o Control
		public void bindingPlanos() {
			Bindings.bindBidirectional(txtTituloPlano.textProperty(), controle.tituloProperty());
		/*	Bindings.bindBidirectional(txtValorMensal.textProperty(), controle.vlMensalProperty());
			Bindings.bindBidirectional(txtValorAnual.textProperty(), controle.vlAnualProperty()); */
			Bindings.bindBidirectional(beneficiosPlano.textProperty(), controle.beneficiosProperty());
						
		}
	
		
		
		
		
	
	
		public static void main(String[] args) {
		 	launch(args);
	    }
}
