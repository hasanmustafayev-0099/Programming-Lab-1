
package busroute;

import java.util.Scanner;

public class BusRoute {

   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Lütfen Durak Sayısını Giriniz: ");
        int durakSayisi = scanner.nextInt();

        System.out.print("Yolcu Kapasitesini Giriniz: ");
        int kapasite = scanner.nextInt();

        String[] duraklar = new String[durakSayisi];
        int[] binenYolcu = new int[durakSayisi];
        int[] inenYolcu = new int[durakSayisi];
        int[] yolcuSayisi = new int[durakSayisi];

        int count = 0; 
        int sum = 0;   
        int toplamYolcu = 0; 

        System.out.println("\n--- Veri Girişi ---");
        for (int i = 0; i < durakSayisi; i++) {
            System.out.print((i + 1) + ". Durak adını giriniz: ");
            duraklar[i] = scanner.next();

            System.out.print("Binen Yolcu Sayısını Giriniz: ");
            binenYolcu[i] = scanner.nextInt();

            System.out.print("İnen Yolcu Sayısını Giriniz: ");
            inenYolcu[i] = scanner.nextInt();

            
            toplamYolcu = toplamYolcu + binenYolcu[i] - inenYolcu[i];

            
            if (toplamYolcu < 0) {
                System.out.println("Data error at " + duraklar[i] + ": cannot have more passengers alighting than are currently on the bus. Occupancy set to 0.");
                toplamYolcu = 0; // Yolcuyu 0'a eşitliyoruz
            }

            yolcuSayisi[i] = toplamYolcu; 
            sum += toplamYolcu;           

            
            System.out.println(duraklar[i] + " durağından sonra otobüsteki yolcu: " + yolcuSayisi[i]);

            
            if (yolcuSayisi[i] > kapasite) {
                System.out.println("Warning: Bus is over capacity at " + duraklar[i] + "!");
                count++;
            }
            System.out.println("-----------------------------------");
        }

        
        System.out.println("\n--- Tüm Durak Bilgileri ---");
        for (int i = 0; i < durakSayisi; i++) {
            System.out.println("Durak Adı: " + duraklar[i] + 
                               " | Binen: " + binenYolcu[i] + 
                               " | İnen: " + inenYolcu[i] + 
                               " | Mevcut Yolcu: " + yolcuSayisi[i]);
        }

        
        System.out.println("\n--- İstatistikler ---");

        
        int maxBinen = binenYolcu[0];
        String enYogunDurak = duraklar[0];
        
        for (int i = 1; i < durakSayisi; i++) {
            if (binenYolcu[i] > maxBinen) {
                maxBinen = binenYolcu[i];
                enYogunDurak = duraklar[i];
            }
        }
        System.out.println("En yoğun durak (En çok binen): " + enYogunDurak + " (" + maxBinen + " yolcu)");

       
        double average = (double) sum / durakSayisi;
        System.out.println("Duraklar Arası Ortalama Yoğunluk: " + average);

       
        System.out.println("Kapasitenin Aşıldığı Durak Sayısı: " + count);

        int sonDurakYolcu = yolcuSayisi[durakSayisi - 1];
        if (sonDurakYolcu != 0) {
            System.out.println("Warning: " + sonDurakYolcu + " passengers still on the bus after the final stop - please check your data.");
        }
    }
    
}
