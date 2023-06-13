# Symulacja podboju kosmosu - Programowanie Obiektowe 2023

Projekt został stworzony przez dwóch super ambitnych studentów, Krzysztofa i Piotra, w ramach ćwiczeń z kursu programowania obiektowego.

Podstawą projektu jest dwuwymiarowa mapa zawierająca w sobie różne ciała niebieskie:
    - planety - posiadające losową ilośc zasobów początkowych z danego przedziału, z których cywilizacje pobierają zasoby, jeśli planeta jest w ich posiadaniu  
    - czarne dziury - wciąga statki, które pojawiają się w jej zasięgu
    - gwiazdy - wzmacniają parametry dla cywilizacji pacyfistycznych mnożąc ich zdolnosc wydobywania zasobów w ciągu iteracji symulacji

Planety mogą być w posiadaniu jednego z dwóch rodzajów cywilizacji:
    - pacyfistycznej - ich parametry mogą zostać wzmocnione przez gwiazdy; nie są w stanie przejąć zajętej już planety; zielone elementy w wizualizacji  
    - agresywnej - ich statki są w stanie przejmować zajęte już planety; czerwone elementy w wizualizacji

Podstawową cechą charakterystyczną dla cywilizacji w danym momencie jest ilość wydobytych przez nią zasobów. Dzięki tym zasobą jest w stanie ona się rozwijać i wysyłać statki zasiedlające nowe planety. 

Przykładowymi mierzonymi parametrami jest rozwój cywilizacji rozumiany np. przez to kto zajął więcej planet,
kto wydobył więcej zasobów, kto wysłał ile statków.

//Po wydobyciu wszystkich zasobów z danej planety, wysyła ona ostatni statek i umiera.

Każda z planet, aktualnie zajęta przez daną cywilizację oraz posiadająca wymaganą ilośc zasobów, wysyła do przestrzeni statek, który po wleceniu w obszar innej planety:
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
