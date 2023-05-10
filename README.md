# symulacja_2023
Symulacja agentowa w postaci symulacji podboju kosmosu.

Podstawą projektu będzie dwuwymiarowa mapa zawierająca w sobie ciała niebieskie.
Ogólny zamysł jest taki:
Będziemy mierzyć rozwój i parametry cywilizacji (np. kto zajął więcej planet,
kto wydobył więcej zasobów itp.).

Cywilizacje będą wydobywać zasoby z planet, które mają ich limit.
Po wydobyciu wszystkich zasobów z danej planety, wysyła ona ostatni statek i umiera.
Każda z planet, aktualnie zajęta przez daną cywilizację będzie
wysyłać do przestrzeni statek (częstość zależy od aktualnego rozwoju cywilizacji), który po wleceniu w
odpowiedni obszar innej planety:
1) zajmie ją gdy jest niczyja.
2) zignoruje ją i poleci dalej gdy natrafi na zajętą planetę pacyfistyczną.
3) zostanie zestrzelony gdy zbliży się do planety agresywnej.
Myślimy, że statki powinny mieć jakiś parametr typu paliwo i po pewnym czasie dezintegrują się w
pustce zostawiając za sobą jedynie cień dawnej świetności [*]
(Może nawet zrobilibyśmy różne typy statków dla różnych cywilizacji: różna prędkość, zasięg)

Zaimplementujemy różne cywilizacje, rywalizujące ze sobą o zasoby:
Typy cywilizacji:
    Agresywne - mogą przejmować inne planety.
    Pacyfistyczne - są zdecydowanie szybsze w rozbudowie i ekstrakcji dóbr.

Oprócz typu cywilizacji, do każdej z nich dopięte będą różne perki:
    Zdolność obrony przed atakami.
    Szybkość wykorzystywania zasobów planety.
    Szybkość rozwoju.

Chcieliśmy zaimplementować różne ciała niebieskie:
    Zwykłe, zamieszkiwalne planety
    Meteory - gdy wystarczająco duży walnie w planetę to ją niszczy (wraz z zawartością).
    Gwiazdy - boostujące różne perki gdy planety znajdą się w ich obszarze
        (uwaga, gwiazdy mają tendencję do implodowania).
    Wormhole - gdy statek do niego wleci, zostaje przeniesiony do losowej pozycji na mapie.
    Czarne dziury - wciągają statki z danej odległości
