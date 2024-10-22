package cz.fei.upce.cv05.evidence.chatek;

import java.util.Scanner;

public class EvidenceChatekApp {

    static Scanner scanner = new Scanner(System.in);

    // Konstanty pro definovani jednotlivych operaci (pouze pro cisty kod)
    static final int KONEC_PROGRAMU = 0;
    static final int VYPIS_CHATEK = 1;
    static final int VYPIS_KONKRETNI_CHATKU = 2;
    static final int PRIDANI_NAVSTEVNIKU = 3;
    static final int ODEBRANI_NAVSTEVNIKU = 4;
    static final int CELKOVA_OBSAZENOST = 5;
    static final int VYPIS_PRAZDNE_CHATKY = 6;

    static final int VELIKOST_KEMPU = 5;
    static final int MAX_VELIKOST_CHATKY = 10;

    public static void main(String[] args) {

        // Definovani pole podle velikosti kempu (poctu chatek)
        int[] chatky = new int[VELIKOST_KEMPU];
        int operace;

        do {
            vypisMenu();

            // Ziskani operace od uzivatele
            System.out.print("Zadej volbu: ");
            operace = scanner.nextInt();

            switch (operace) {
                case VYPIS_CHATEK ->
                    vypisChatek(chatky);

                case VYPIS_KONKRETNI_CHATKU ->
                    vypisKonkretniChatky(chatky);

                case PRIDANI_NAVSTEVNIKU ->
                    pridaniNavstevniku(chatky);

                case ODEBRANI_NAVSTEVNIKU ->
                    odebraniNavstevniku(chatky);

                case CELKOVA_OBSAZENOST ->
                    celkovaObsazenost(chatky);

                case VYPIS_PRAZDNE_CHATKY ->
                    vypisPrazdneChatky(chatky);

                case KONEC_PROGRAMU -> {
                    System.out.println("Konec programu");
                }

                default -> {
                    System.err.println("Neplatna volba");
                }
            }
        } while (operace != 0);
    }

    private static void vypisMenu() {
        System.out.println("""
                    MENU:
                                        
                    1 - vypsani vsech chatek
                    2 - vypsani konkretni chatky
                    3 - Pridani navstevniku
                    4 - Odebrani navstevniku
                    5 - Celkova obsazenost kempu
                    6 - Vypis prazdne chatky
                    0 - Konec programu
                    """);
    }

    // Metoda pro vypis vsech chatek
    private static void vypisChatek(int[] chatky) {
        for (int i = 0; i <= chatky.length;) {
            System.out.print("Chatka [" + (i + 1) + "] = " + chatky[i]);
        }
    }

    // Metoda pro vypis jedne konkretni chatky
    private static void vypisKonkretniChatky(int[] chatky) {
        System.out.print("Zadej cislo chatky: ");
        // Odecteni 1 protoze uzivatel cisluje chatky od 1, ale program od 0
        int cisloChatky = scanner.nextInt() - 1;
        // Zaporne nebo cislo vetsi nez je pocet chatek je nevalidni vstup
        if (cisloChatky < 0 || cisloChatky >= chatky.length) {
            System.err.println("Tato chatka neexistuje");
        } else {
            System.out.println("Chatka [" + (cisloChatky + 1) + "] = " + chatky[cisloChatky]);
        }
    }

    private static void pridaniNavstevniku(int[] chatky) {
        System.out.print("Zadej cislo chatky: ");
        int cisloChatky = scanner.nextInt() - 1;

        if (cisloChatky < 0 || cisloChatky >= chatky.length) {
            System.err.println("Tato chatka neexistuje");
            return;
        }

        // Ziskani poctu navstevniku, kteri se chteji v chatce ubytovat
        System.out.print("Zadej pocet navstevniku: ");
        int pocetNavstevniku = scanner.nextInt();

        // Zaporne cislo nebo prilis velky nevalidni vstup
        if (pocetNavstevniku <= 0 || pocetNavstevniku > MAX_VELIKOST_CHATKY) {
            System.err.println("Neplatna hodnota pro pocet navstevniku");
            return;
        }

        // Pokud je pocet uz ubytovanych plus ty co se chteji ubytovat vetsi nez kapacita chatky je to nevalidni vstup
        if ((chatky[cisloChatky] + pocetNavstevniku) > MAX_VELIKOST_CHATKY) {
            System.err.println("Prekrocen maximalni pocet navstevniku chatky");
            return;
        }

        // Pridej nove ubytovane do chatky k tem co uz tam jsou
        chatky[cisloChatky] = pocetNavstevniku + chatky[cisloChatky];
        System.out.println("Navstevnici uspesne pridani");
    }

    private static void odebraniNavstevniku(int[] chatky) {
        System.out.print("Zadej cislo chatky: ");
        int cisloChatky = scanner.nextInt() - 1;

        if (cisloChatky < 0 || cisloChatky >= chatky.length) {
            System.err.println("Tato chatka neexistuje");
            return;
        }

        if (chatky[cisloChatky] <= 0) {
            System.err.println("V této chatce nejsou žádní návštěvníci");
            return;
        }

        System.out.print("Zadej pocet navstevniku: ");
        int pocetNavstevniku = scanner.nextInt();

        if (pocetNavstevniku <= 0) {
            System.err.println("Pocet k odebrani musi byt kladne cislo");
            return;
        }

        if (chatky[cisloChatky] < pocetNavstevniku) {
            System.err.println("Nelze odebrat vice navstevniku, nez je v chatce");
        }

        chatky[cisloChatky] -= pocetNavstevniku;
        System.out.println("Navstevnici uspesne odebrani");
    }

    private static void celkovaObsazenost(int[] chatky) {
        int celkovaObsazenost = 0;

        for (int i = 0; i <= chatky.length; i++) {
            celkovaObsazenost += chatky[i];
        }

        System.out.println("Celkova obsazenost chatek v kempu je: " + celkovaObsazenost);
    }

    private static void vypisPrazdneChatky(int[] chatky) {
        int prazdneChatky = 0;

        for (int i = 0; i <= chatky.length; i++) {
            if (chatky[i] == 0) {
                prazdneChatky++;
            }
        }

        System.out.println("Pocet prazdnch chatek je: " + prazdneChatky);
    }
}
