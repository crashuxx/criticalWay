package cx.criticalWay

/**
 */
enum City {
    S("Katowice"),
    K("Kraków"),
    O("Opole"),
    D("Wrocław"),
    T("Kielce"),
    P("Poznań"),
    F("Zielona Góra"),
    Z("Szczecin"),
    E("Łódz"),
    C("Bydgoszcz"),
    W("Warszawa"),
    L("Lubin"),
    B("Białystok"),
    R("Rzeszów"),
    G("Gdańsk"),
    N("Olsztyn");

    private final String value;

    public City(String value) {
        this.value = value
    }

    @Override
    public String toString() {
        return value;
    }
}

