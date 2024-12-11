package controller;

import java.time.LocalDate;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import personel.Personel;

public class guncellemeController {
	@FXML
	private Button onaylaButon;
	@FXML
	private TextField isimTextField;
	@FXML
	private TextField soyisimTextField;
	@FXML
	private TextField maasTextField;
	@FXML
	private TextField pozisyonTextField;
	@FXML
	private TextField yasTextField;
	
	private Personel seciliPersonel;
	
	public void seciliPersonelMetod(Personel personel) {
		seciliPersonel=personel;
	}
	
	@FXML
	public void onaylaButonKod() {
		// TextField'dan alınan değerleri uygun türlere dönüştür
	    long maas = Long.parseLong(maasTextField.getText());
	    String isim = isimTextField.getText().toUpperCase();
	    String soyisim = soyisimTextField.getText().toUpperCase();
	    String pozisyon = pozisyonTextField.getText().toUpperCase();
	    int yas = Integer.parseInt(yasTextField.getText());
	    
	    
	    Main.bL.guncelleme(seciliPersonel,maas,isim, soyisim,pozisyon,yas);
	    Main.bL.listeYazdir();
	    
	    seciliPersonel.setIsim(isim);
	    seciliPersonel.setMaas(maas);
	    seciliPersonel.setSoyisim(soyisim);
	 // if bloğunun amacı pozisyon değişmemişse terfi bilgilerini güncellememek
	    if (!seciliPersonel.getPozisyon().equals(pozisyon)) {
	    	seciliPersonel.setTerfiBilgileri(seciliPersonel.getTerfiBilgileri()+"\n "+pozisyon+": "+LocalDate.now());
	    }
	    seciliPersonel.setPozisyon(pozisyon);
	    seciliPersonel.setYas(yas);
	    
	    // Personel nesnesi oluşturuluyor
	    //Personel yeniPersonel = new Personel(personelNo, isim, soyisim, pozisyon, yas);
	    // observable liste yeni personeli ekle
	    //Controller.personelList.add(yeniPersonel);
	    //Controller controller=new Controller();
	    //controller.tableView.setItems(Controller.personelList);
	    
		// onaylaButon'un bulunduğu stage'i kapat
		Stage kapastage = (Stage) onaylaButon.getScene().getWindow(); // onaylaButon'un bulunduğu stage'i bir nesneye atama
		kapastage.close(); // alınan stage'i kapatma
		
		
	}
}
