package application;
	
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import personel.Personel;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import controller.Controller;
import dataStructure.BagliListe;

public class Main extends Application {
	
	public static BagliListe bL = new BagliListe();
	
	private static Scene scene;
	private static Object personelList;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		scene= new Scene(loadFXML("Giris"));
		primaryStage.setScene(scene);
		primaryStage.setTitle("MAAŞ TAKİP SİSTEMİ");
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	// parametre olarak gönderilen fxml dosyasını yükleyen metod
	public static Parent loadFXML(String FXML) throws IOException {
		
		FXMLLoader fxmlLoader= new FXMLLoader(Main.class.getResource("/views/"+FXML+".fxml"));
		Parent parent=fxmlLoader.load();
		
		return parent;
	
	}
	
	@Override
	public void stop() throws IOException {
		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		launch(args);
			
	}
	
	
	public static void saveData(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
        	writer.write(data);
            //writer.newLine();
            System.out.println("Veriler başarıyla kaydedildi.");
        } catch (IOException e) {
            System.out.println("Dosya yazma hatası: " + e.getMessage());
        }
    }
	
	public static void readingData(String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	String[] data = line.split(" "); // Eğer veriler virgülle ayrılıyorsa
            	if (data.length == 5) {
                	String name = data[0].toUpperCase();
                    String surname = data[1].toUpperCase();
                    String position = data[2].toUpperCase();
                    int age = Integer.parseInt(data[3]);
                    Long personelNo = Long.parseLong(data[4]);
                   
                    // Personel nesnesi oluşturun ve listeye ekleyin
                    Personel personel = new Personel(name,surname,position,age,personelNo);
                    
                    bL.Ekle(personel);
                    
                    if (!Controller.personelList.contains(personel)) {
                    	Controller.personelList.add(personel);
                    }else {
                    	System.out.println("veri yükleme hatası");
                    }
                 }
        }}catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }}
	
	public static void clearFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Hiçbir şey yazmıyoruz, dosyayı sıfırlıyoruz
            System.out.println("Dosya başarıyla temizlendi.");
        } catch (IOException e) {
            System.out.println("Dosya temizleme hatası: " + e.getMessage());
        }
    }

	private static Long Long(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private static int Integer(String string) {
		// TODO Auto-generated method stub
		return 0;
	}}
