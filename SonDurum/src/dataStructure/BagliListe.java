package dataStructure;

import personel.Personel;

public class BagliListe {
		Personel bas;
		Personel son;

		
		public BagliListe(){
			bas=null;
			son=null;
		}
		
		public void Sil(Personel silPersonel) {
			if (bas == null) {
				System.out.println("kardes zaten bos bu");
			}else {
				Personel temp = bas;
				
				 while(temp!= null) {
	
					 if(temp.getMaas() == silPersonel.getMaas()) {
						temp.onceki.sonraki=temp.sonraki;
						temp.sonraki.onceki=temp.onceki;
				 }
					 temp=temp.sonraki;
				 
				 }
			}
			
		}
		
		public void Ekle(Personel yeni) {
			
			if(bas==null) {//liste boşsa
				bas=yeni;
				son=yeni;
				return;
			}
			//liste boş değil
			
			son.sonraki=yeni;
			son.onceki=son;
			son=yeni;
			 
		}
		
		public void guncelleme(Personel guncellePersonel,long maas,String isim,String soyisim,String pozisyon,int yas) {
			Personel temp = bas;
			while(temp !=null) {
				if(guncellePersonel.getMaas() == temp.getMaas()) {
					temp.setMaas(maas);
					temp.setIsim(isim);
					temp.setSoyisim(soyisim);
					temp.setPozisyon(pozisyon);
					temp.setYas(yas);
				}
				temp=temp.sonraki;
			}
		}
		
		
		
		
		
		public void listeYazdir() {
			
			Personel temp =bas;
			while(temp!=null) {
				
				System.out.println(temp.getMaas()+" "+temp.getIsim()+" "+temp.getSoyisim()+" "+temp.getPozisyon()+" "+temp.getYas());
				temp = temp.sonraki;
				
			}
			System.out.println("--------------------");
		}
		 

}