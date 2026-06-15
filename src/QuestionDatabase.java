//Stores and manages the 73 trivia questions and answers about Bursa.

import java.util.Random;

public class QuestionDatabase {
    private final int numberQuestions = 73;
    private final String[] qChoice = new String[numberQuestions];
    private final String[] aChoice = new String[numberQuestions];
    private final Random myRandom = new Random();

    public QuestionDatabase() {
        initializeQuestions();
    }

    public int getRandomQuestionIndex() {
        return myRandom.nextInt(numberQuestions);
    }

    public String getQuestion(int index) {
        return qChoice[index];
    }

    public String getAnswer(int index) {
        return aChoice[index];
    }

    private void initializeQuestions() {
        qChoice[0] = "BURSA'NIN EN ÜNLÜ DAĞININ ADI NEDIR?";
        aChoice[0] = "ULUDAĞ";
        qChoice[1] = "BURSA'NIN OSMANLI DÖNEMINDEKI ILK BAŞKENT OLDUĞU YIL HANGISIDIR?";
        aChoice[1] = "1326";
        qChoice[2] = "BURSA'DA ÜNLÜ YEŞIL TÜRBEYI KIM YAPTIRMIŞTIR?";
        aChoice[2] = "ÇELEBİ MEHMET";
        qChoice[3] = "BURSA'NIN EN MEŞHUR YEMEĞI NEDIR?";
        aChoice[3] = "İSKENDER";
        qChoice[4] = "BURSA'NIN HANGI ÜRÜNÜYLE ÜNLÜDÜR?";
        aChoice[4] = "HAVLU";
        qChoice[5] = "BURSA'DA BULUNAN TARİHİ HANLARIN EN BÜYÜĞÜ HANGİSİDİR?";
        aChoice[5] = "KOZAHAN";
        qChoice[6] = "BURSA'NIN HANGI ILÇESI TERMALLERIYLE ÜNLÜDÜR?";
        aChoice[6] = "ÇEKİRGE";
        qChoice[7] = "BURSA'NIN OSMANLI KURULUŞUNDAKI ILK CAMISININ ADI NEDIR?";
        aChoice[7] = "ORHAN CAMİ";
        qChoice[8] = "BURSA'NIN HANGI NEHRI ŞEHRI BÖLER?";
        aChoice[8] = "GÖKDERE";
        qChoice[9] = "BURSA'DA ÜRETILEN ÜNLÜ KUMAŞIN ADI NEDIR?";
        aChoice[9] = "IPEK";
        qChoice[10] = "BURSA'NIN HANGI ILÇESI ZEYTIN ÜRETIMINDE ÖNEMLIDIR?";
        aChoice[10] = "MUDANYA";
        qChoice[11] = "BURSA'NIN EN ESKI KÖYÜ HANGISIDIR?";
        aChoice[11] = "CUMALIKIZIK";
        qChoice[12] = "BURSA'DA HANGI SULTANIN TÜRBESI BULUNUR?";
        aChoice[12] = "MURAT";
        qChoice[13] = "BURSA'NIN HANGI SEMTI TARİHİ EVLERIYLE ÜNLÜDÜR?";
        aChoice[13] = "TOPHANE";
        qChoice[14] = "BURSA'NIN UNESCO DÜNYA MIRASI LISTESINE GIREN KÖYÜ HANGISIDIR?";
        aChoice[14] = "CUMALIKIZIK";
        qChoice[15] = "BURSA'NIN HANGI GÖLÜ DOĞAL GÜZELLIĞIYLE ÜNLÜDÜR?";
        aChoice[15] = "ULUABAT";
        qChoice[16] = "BURSA'DA ÜRETILEN ÜNLÜ TATLI HANGISIDIR?";
        aChoice[16] = "KEMALPAŞA";
        qChoice[17] = "BURSA'NIN HANGI ILÇESI KAYAK MERKEZIYLE TANINIR?";
        aChoice[17] = "ULUDAĞ";
        qChoice[18] = "BURSA'NIN OSMANLI DÖNEMINDEKI ADI NEDIR?";
        aChoice[18] = "HÜDAVENDİGAR";
        qChoice[19] = "BURSA'DA HANGI TARİHİ KALE BULUNUR?";
        aChoice[19] = "BURSA KALESİ";
        qChoice[20] = "BURSA'NIN HANGI PARKI ŞEHIR MERKEZINDE YER ALIR?";
        aChoice[20] = "KÜLTÜRPARK";
        qChoice[21] = "BURSA'NIN HANGI ILÇESI SANAYIYLE ÖN PLANDADIR?";
        aChoice[21] = "NİLÜFER";
        qChoice[22] = "BURSA'DA HANGI CAMININ 20 KUBBESI VARDIR?";
        aChoice[22] = "ULU CAMİ";
        qChoice[23] = "BURSA'NIN HANGI MEYDANI TARİHİ SAAT KULESIYLE ÜNLÜDÜR?";
        aChoice[23] = "TOPHANE";
        qChoice[24] = "BURSA'DA HANGI FESTIVAL İNCİRİ KUTLAR?";
        aChoice[24] = "İNCİR FESTİVALİ";
        qChoice[25] = "BURSA'NIN HANGI ILÇESI DENIZ KENARINDA YER ALIR?";
        aChoice[25] = "MUDANYA";
        qChoice[26] = "BURSA'DA HANGI TARİHİ ÇARŞI ALIŞVERIŞ IÇIN ÜNLÜDÜR?";
        aChoice[26] = "KAPALI ÇARŞI";
        qChoice[27] = "BURSA'NIN HANGI ILÇESI ÜZÜM BAĞLARIYLA TANINIR?";
        aChoice[27] = "GEMLİK";
        qChoice[28] = "BURSA'DA HANGI KÖPRÜ OSMANLI DÖNEMINDEN KALMADIR?";
        aChoice[28] = "IRGANDI";
        qChoice[29] = "BURSA'NIN HANGI SEMTI YEŞIL RENGIYLE ÜNLÜDÜR?";
        aChoice[29] = "YEŞIL";
        qChoice[30] = "BURSA'NIN HANGI DAĞINDA KAYAK YAPILIR?";
        aChoice[30] = "ULUDAĞ";
        qChoice[31] = "BURSA'DA HANGI TARİHİ HAMAM HALA KULLANILMAKTADIR?";
        aChoice[31] = "ÇEKİRGE HAMAMI";
        qChoice[32] = "BURSA'NIN HANGI ILÇESI ZEYTINYAĞIYLA ÜNLÜDÜR?";
        aChoice[32] = "GEMLIK";
        qChoice[33] = "BURSA'DA HANGI OSMANLI PADİŞAHININ TÜRBESI VARDIR?";
        aChoice[33] = "OSMAN GAZİ";
        qChoice[34] = "BURSA'NIN HANGI ILÇESI KESTANESIYLE ÜNLÜDÜR?";
        aChoice[34] = "İNEGÖL";
        qChoice[35] = "BURSA'DA HANGI MEYDAN ŞEHIR MERKEZINDE BULUNUR?";
        aChoice[35] = "HEYKEL";
        qChoice[36] = "BURSA'NIN HANGI ILÇESI MOBILYASIYLA TANINIR?";
        aChoice[36] = "INEGÖL";
        qChoice[37] = "BURSA'DA HANGI TARİHİ AĞAÇ ASIRLARDIR AYAKTADIR?";
        aChoice[37] = "ÇINAR";
        qChoice[38] = "BURSA'NIN HANGI ILÇESI KÖFTESIYLE ÜNLÜDÜR?";
        aChoice[38] = "İNEGÖL";
        qChoice[39] = "BURSA'DA HANGI CAMI OSMANLI'NIN ILK BÜYÜK CAMISIDIR?";
        aChoice[39] = "ULU CAMİ";
        qChoice[40] = "BURSA'NIN HANGI GÖLÜ KUŞ CENNETI OLARAK BILINIR?";
        aChoice[40] = "ULUABAT";
        qChoice[41] = "BURSA'DA HANGI TARİHİ YAPI ZINDAN OLARAK KULLANILMIŞTIR?";
        aChoice[41] = "BURSA KALESI";
        qChoice[42] = "BURSA'NIN HANGI ILÇESI TARİHİ EVLERIYLE TANINIR?";
        aChoice[42] = "OSMANGAZİ";
        qChoice[43] = "BURSA'DA HANGI FESTIVAL IPEK ÜRETIMINI KUTLAR?";
        aChoice[43] = "IPEK FESTIVALI";
        qChoice[44] = "BURSA'NIN HANGI SEMTI OSMANLI MEZARLIĞIYLA ÜNLÜDÜR?";
        aChoice[44] = "MURADIYE";
        qChoice[45] = "BURSA'DA HANGI NEHIR ÜZERINDE BARAJ VARDIR?";
        aChoice[45] = "NİLÜFER";
        qChoice[46] = "BURSA'NIN HANGI ILÇESI SANAYI BÖLGESIYLE BILINIR?";
        aChoice[46] = "OSMANGAZİ";
        qChoice[47] = "BURSA'DA HANGI TARİHİ KÖŞK ZIYARET EDILEBILIR?";
        aChoice[47] = "HÜNKAR KÖŞKÜ";
        qChoice[48] = "BURSA'NIN HANGI ILÇESI BALIKÇILIĞIYLA ÜNLÜDÜR?";
        aChoice[48] = "MUDANYA";
        qChoice[49] = "BURSA'DA HANGI OSMANLI PADİŞAHI DOĞMUŞTUR?";
        aChoice[49] = "MURAT";
        qChoice[50] = "BURSA'NIN HANGI SEMTI TARİHİ ÇARŞILARIYLA BILINIR?";
        aChoice[50] = "YILDIRIM";
        qChoice[51] = "BURSA'DA HANGI DAĞIN ETEĞINDE ŞEHIR KURULMUŞTUR?";
        aChoice[51] = "ULUDAĞ";
        qChoice[52] = "BURSA'NIN HANGI ILÇESI TERMALLERIYLE TANINIR?";
        aChoice[52] = "KESTEL";
        qChoice[53] = "BURSA'DA HANGI TARİHİ KÖPRÜ HALA AYAKTADIR?";
        aChoice[53] = "IRGANDI";
        qChoice[54] = "BURSA'NIN HANGI ILÇESI ÜZÜMÜYLE ÜNLÜDÜR?";
        aChoice[54] = "GÜRSU";
        qChoice[55] = "BURSA'DA HANGI CAMININ MIMARI HACI IVAZ PAŞA’DIR?";
        aChoice[55] = "YEŞİL CAMİ";
        qChoice[56] = "BURSA'NIN HANGI ILÇESI OTOMOTIV SANAYISIYLE BILINIR?";
        aChoice[56] = "NILÜFER";
        qChoice[57] = "BURSA'DA HANGI TARİHİ HAN IPEK TICARETI IÇIN KULLANILMIŞTIR?";
        aChoice[57] = "KOZAHAN";
        qChoice[58] = "BURSA'NIN HANGI SEMTI OSMANLI SARAY MUTFAĞIYLA ÜNLÜDÜR?";
        aChoice[58] = "MURADİYE";
        qChoice[59] = "BURSA'DA HANGI GÖL BALIKÇILIK IÇIN ÖNEMLIDIR?";
        aChoice[59] = "IPEKCIK";
        qChoice[60] = "BURSA'NIN HANGI ILÇESI OSMANLI DÖNEMI EVLERIYLE TANINIR?";
        aChoice[60] = "YILDIRIM";
        qChoice[61] = "BURSA'DA HANGI TARİHİ YAPI SULTANLARIN DINLENME YERIYDI?";
        aChoice[61] = "HÜNKAR KÖŞKÜ";
        qChoice[62] = "BURSA'NIN HANGI ILÇESI KAPLICALARIYLA ÜNLÜDÜR?";
        aChoice[62] = "OYLAT";
        qChoice[63] = "BURSA'DA HANGI MEYDAN MODERN ALIŞVERIŞ MERKEZIYLE BILINIR?";
        aChoice[63] = "KENT MEYDANI";
        qChoice[64] = "BURSA'NIN HANGI ILÇESI TARIM ÜRÜNLERİYLE TANINIR?";
        aChoice[64] = "MUSTAFAKEMALPAŞA";
        qChoice[65] = "BURSA'DA HANGI TARİHİ ÇEŞME HALA KULLANILMAKTADIR?";
        aChoice[65] = "KAYHAN ÇEŞMESİ";
        qChoice[66] = "BURSA'NIN HANGI ILÇESI KÖY PAZARIYLA ÜNLÜDÜR?";
        aChoice[66] = "KESTEL";
        qChoice[67] = "BURSA'DA HANGI OSMANLI PADİŞAHININ BABASI BURSA'DA DOĞMUŞTUR?";
        aChoice[67] = "ORHAN GAZİ";
        qChoice[68] = "BURSA'NIN HANGI SEMTI YEŞIL ALANLARIYLA BILINIR?";
        aChoice[68] = "SOĞANLI";
        qChoice[69] = "BURSA'DA HANGI TARİHİ YAPI OSMANLI’NIN ILK SARAYIDIR?";
        aChoice[69] = "BEY SARAYI";
        qChoice[70] = "BURSA'NIN HANGI ILÇESI FINDIĞIYLA ÜNLÜDÜR?";
        aChoice[70] = "ORHANGAZİ";
        qChoice[71] = "BURSA'DA HANGI NEHIR ŞEHRE HAYAT VERIR?";
        aChoice[71] = "NİLÜFER";
        qChoice[72] = "BURSA'NIN HANGI ILÇESI KAPLICA SULARIYLA TANINIR?";
        aChoice[72] = "ÇEKİRGE";
    }
}
