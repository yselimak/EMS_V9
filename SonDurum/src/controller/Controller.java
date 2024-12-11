package controller;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import routing.Routing;
import personel.Personel;
//import org.kordamp.ikonli.javafx.FontIcon;
import application.Main;
import dataStructure.BagliListe;

//import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;


public class Controller {
	@FXML
	public static Button ekleButon;
	@FXML
	public static Button kaydetButton;
	@FXML
	private TextField aramaTextField;
	@FXML
	public TableView<Personel> tableView;
	@FXML
	private TableColumn<Personel,Double> maasColumn;
	@FXML
	private TableColumn<Personel, String> isimColumn;
	@FXML
	private TableColumn<Personel, String> soyisimColumn;
	@FXML
	private TableColumn<Personel, String> pozisyonColumn;

	@FXML
	private TableColumn<Personel, Integer> yasColumn = new TableColumn<>();
	@FXML
	private TableColumn<Personel, Void> actionColumn;
	@FXML
	public static ObservableList<Personel> personelList = FXCollections.observableArrayList();
	
	
	@FXML
	public void initialize() {
	maasColumn.setCellValueFactory(new PropertyValueFactory<Personel,Double>("maas"));
    isimColumn.setCellValueFactory(new PropertyValueFactory<Personel,String>("isim"));
    soyisimColumn.setCellValueFactory(new PropertyValueFactory<Personel,String>("soyisim"));
    pozisyonColumn.setCellValueFactory(new PropertyValueFactory<Personel,String>("pozisyon"));
    yasColumn.setCellValueFactory(new PropertyValueFactory<Personel,Integer>("yas"));
    
    tabloyaButonEkle();
  
    tableView.setItems(personelList);//tableView a personelleri ekleme
    Main.readingData("eyyup.mydb");
    
	}
	
	@FXML
	public void veriCikar() {
		Main.clearFile("eyyup.mydb");
		int a = Controller.personelList.size();
		int i = 0;
		
		while(a>i) {
			Personel aktifPersonel= Controller.personelList.get(i);
			String text=aktifPersonel.getIsim()+" " + aktifPersonel.getSoyisim()
			+ " " + aktifPersonel.getPozisyon() + " " + aktifPersonel.getYas()
			+ " " + aktifPersonel.getMaas();
			text = text + "\n";
			text+=aktifPersonel.getTerfiListesi() + "\n";
			Main.saveData("eyyup.mydb",text);
			i+=1;
		}
	}
	
		/*
		int a = Controller.personelList.size();
		int i = 0;
		String text;
		while(a>i) {
			Personel aktifPersonel= Controller.personelList.get(i);
			String text="/n" + aktifPersonel.getIsim()+" " + aktifPersonel.getSoyisim()
			+ " " + aktifPersonel.getPozisyon() + " " + aktifPersonel.getYas()
			+ " " + aktifPersonel.getPersonelNo();
			Controller.veriKaydet("eyyup.mydb",text);
			i+=1;
		}
		Controller.veriKaydet("eyyup.mydb",text);
		*/
	
	
	/*
	public static void veriKaydet(String fileName, String data) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
		writer.write(data);
		System.out.println("Veriler başarıyla kaydedildi.");
	}
*/
	
	private void tabloyaButonEkle() {
	    Callback<TableColumn<Personel, Void>, TableCell<Personel, Void>> cellFactory = new Callback<>() {
	        @Override
	        public TableCell<Personel, Void> call(final TableColumn<Personel, Void> param) {
	            return new TableCell<>() {
	            	
	            	
	            	
	            	FontIcon silIcon = new FontIcon(FontAwesomeSolid.TRASH);
	                {silIcon.setIconSize(10); // İkon boyutunu ayarlayın
	                silIcon.setFill(Color.RED);}
	            	
	                FontIcon infoIcon = new FontIcon(FontAwesomeSolid.INFO);
	                {silIcon.setIconSize(10); // İkon boyutunu ayarlayın
	                silIcon.setFill(Color.RED);}
	                
	                FontIcon editIcon = new FontIcon(FontAwesomeSolid.PENCIL_ALT);
	                {silIcon.setIconSize(10); // İkon boyutunu ayarlayın
	                silIcon.setFill(Color.RED);}
	            	
	            
	            	
	                private final Button deleteButton = new Button("");
	                private final Button editButton = new Button("");
	                private final Button infoButton = new Button("");
	                private final HBox hbox = new HBox(10, deleteButton, editButton,infoButton); // Butonları yatay hizala

	                {
	                	
	                	deleteButton.setGraphic(silIcon);
	                	deleteButton.setStyle("-fx-background-color: transparent;");
	                	
	                	infoButton.setGraphic(infoIcon);
	                	infoButton.setStyle("-fx-background-color: transparent;");
	                	
	                	editButton.setGraphic(editIcon);
	                	editButton.setStyle("-fx-background-color: transparent;");
	                	
	                    // Sil butonu işlemleri
	                    deleteButton.setOnAction(event -> {
	                        Personel personel = getTableView().getItems().get(getIndex());
	                        getTableView().getItems().remove(personel);
	                        Main.clearFile("eyyup.mydb");
	                        veriCikar();
	                        BagliListe bagliListe = new BagliListe();
	                        bagliListe.Sil(personel);
	                        
	                        
	                        System.out.println("Personel silindi: " + personel.getIsim());
	                    });
	                    
	                    // Düzenle butonu işlemleri
	                    editButton.setOnAction(event -> {
	                        //Personel personel = getTableView().getItems().get(getIndex());
	                        //try {
								//Routing.sayfaAc("guncellemeSayfasi", "PERSONEL BİLGİLERİNİ GÜNCELLEYİNİZ", false);
							//} catch (IOException e) {
								//e.printStackTrace();
							//}
	                    	
	                    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("/views/guncellemeSayfasi.fxml"));
	                    	Parent root = null;
							try {
								root = loader.load();
							} catch (IOException e) {
								
								e.printStackTrace();
							}
	       
	                        Personel seciliPersonel = getTableView().getItems().get(getIndex());
	                        guncellemeController guncCont= loader.getController();
	                        guncCont.seciliPersonelMetod(seciliPersonel);
	                        Stage stage=new Stage();
	                        stage.setScene(new Scene(root));
	                        stage.setTitle("PERSONEL BİLGİLERİNİ GÜNCELLEYİNİZ");
	                        stage.show();
	                        tableView.refresh();
	                        
	                    });
	                    
	                    // info butonu işlemleri
	                    infoButton.setOnAction(event ->  {
	                    	Personel personel = getTableView().getItems().get(getIndex());// personel bilgilerini alma
	                        infoController.ınfoPersonelList.clear();
	                        infoController.ınfoPersonelList.add(personel);
	                        
	                        try {
								Routing.sayfaAc("infoSayfasi", "PERSONEL BİLGİLERİ", true);
							} catch (IOException e) {
								e.printStackTrace();
							}
	                        System.out.println("Düzenlenecek personel: " + personel.getIsim());
	                        // Burada düzenleme işlemini gerçekleştirin
	                    });
	                }

	                @Override
	                protected void updateItem(Void item, boolean empty) {
	                    super.updateItem(item, empty);
	                    if (empty) {
	                        setGraphic(null);
	                    } else {
	                        setGraphic(hbox); // Hücreye HBox (butonlar) ekle
	                    }
	                }
	            };
	        }
	    };

	    actionColumn.setCellFactory(cellFactory);
	}
	
	@FXML
	public  void ekleButonKod() throws IOException {
		Routing.sayfaAc("ekleSayfasi", "Yeni Personel Ekleme", false);
	}
	
	@FXML
	public void aramaTextFieldKod() {
		//sorgulama işlemi yapılacak
	}


}
